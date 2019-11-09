package app;

import app.graphics.Theme;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller.java - a kalendai class to handle the main.fxml file
 */
public class Controller {

    /**
     * Mouse - a class to track cursor data
     */
    class Mouse {
        /*
         * x, y - doubles that store mouse location
         */
        double x, y;
    }

    /**
     * Offset - a class to track cursor offset from top left of confirm box
     */
    class Offset {
        /*
         * x, y - doubles that store offset location
         */
        double x, y;
    }

    /*
     * mouse - a Mouse for storing cursor data
     * offset - an Offset for storing offset data
     * theme - a Theme for handling app coloring schemes
     * confirmCloseOpen - a boolean for storing if the confirm close dialog is open
     */
    private Mouse mouse;
    private Offset offset;
    private Theme theme;
    private boolean confirmCloseOpen;

    // The background of the app
    @FXML
    AnchorPane anchor;
    // The title bar of the app
    @FXML
    HBox titleBar;
    // The minimize image holder
    @FXML
    ImageView minimize;
    // The maximize image holder
    @FXML
    ImageView maximize;
    // The close image holder
    @FXML
    ImageView close;

    /**
     * Called after all FXML components have been initialized
     */
    @FXML
    public void initialize() {

        // Initialize mouse, offset, confirmCloseOpen instance variables
        mouse = new Mouse();
        offset = new Offset();
        confirmCloseOpen = false;

        // Setup fx content layout
        titleBar.prefWidthProperty().bind(anchor.widthProperty());

        // Retrieve the theme from a settings file
        theme = new Theme();
        // TODO

        // Apply the app theme
        titleBar.setStyle("-fx-background-color: " + theme.getTitleBarMain() + ";");
        // TODO

        // Assigns images for title bar image views with recoloring
        Image closeImage = new Image(getClass().getResource("resources/close.png").toExternalForm());
        Image maximizeImage = new Image(getClass().getResource("resources/maximize.png").toExternalForm());
        Image minimizeImage = new Image(getClass().getResource("resources/minimize.png").toExternalForm());
        close.setImage(closeImage);
        maximize.setImage(maximizeImage);
        minimize.setImage(minimizeImage);
        // TODO

        // Initiates an animation for app start
        // TODO
    }

    /**
     * Stores mouse and offset data upon cursor press on the title bar
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onTitleBarPressed(MouseEvent mouseEvent) {
        // Checks if the confirm close dialog is not open
        if (!confirmCloseOpen) {

            // Updates mouse data
            mouse.x = mouseEvent.getScreenX();
            mouse.y = mouseEvent.getScreenY();

            // Updates offset data
            offset.x = anchor.getScene().getWindow().getX() - mouse.x;
            offset.y = anchor.getScene().getWindow().getY() - mouse.y;
        }
    }

    /**
     * Moves the program window upon the title bar's drag
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onTitleBarDragged(MouseEvent mouseEvent) {
        // Checks if the confirm close dialog is not open
        if (!confirmCloseOpen) {

            // Checks if cursor was not aimed at a title bar button
            EventTarget target = mouseEvent.getTarget();
            if (!(target == minimize || target == maximize || target == close)) {

                // Updates mouse data
                mouse.x = mouseEvent.getScreenX();
                mouse.y = mouseEvent.getScreenY();

                // Updates program window location based on mouse and offset data
                anchor.getScene().getWindow().setX(mouse.x + offset.x);
                anchor.getScene().getWindow().setY(mouse.y + offset.y);
            }
        }
    }


    /**
     * Minimizes the program window upon the minimize button being clicked
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onMinimizeClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Maximizes the program window upon the maximize button being clicked
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onMaximizeClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setMaximized(true);
    }

    /**
     * Calls the close confirmation dialog box to open upon the close button being clicked
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onCloseClicked(MouseEvent mouseEvent) {
        //Checks if the close confirmation dialog box is already open
        if(!confirmCloseOpen)
            try {
                // Loads the fxml file
                Stage primaryStage = (Stage) anchor.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("close-confirm.fxml"));
                Stage stage = new Stage();

                // Beautifies the program with a transparent background
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);

                // Finishes stage setup which sets confirmCloseOpen to true now and to false upon the stage's exit
                stage.setScene(scene);
                stage.initOwner(primaryStage);
                stage.setOnHidden(eventHandler -> confirmCloseOpen = false);
                confirmCloseOpen = true;

                // Shows the confirmation dialog box
                stage.show();
            } catch(Exception e) {
                // Print the stack trace of the exception and exit
                e.printStackTrace();
                System.exit(0);
            }
    }


}

