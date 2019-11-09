
package app;

import java.util.concurrent.atomic.AtomicBoolean;

import app.graphics.Theme;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * CloseConfirmController.java - a kalendai class to handle the close-confirm.fxml file
 */
public class CloseConfirmController {

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
     */
    private Mouse mouse;
    private Offset offset;
    private Theme theme;

    // The background of the dialog box
    @FXML
    VBox confirm;
    // The ex button of the dialog box
    @FXML
    ImageView ex;
    // The check button of the dialog box
    @FXML
    ImageView check;
    // The confirmation text of the dialog box
    @FXML
    Label confirmationText;

    /**
     * Called after all FXML components have been initialized
     */
    @FXML
    public void initialize() {

        // Initialize mouse, offset instance variables
        mouse = new Mouse();
        offset = new Offset();

        // Retrieve the theme from a settings file
        theme = new Theme();
        // TODO

        // Apply the app theme
        confirm.setStyle("-fx-background-color: " + theme.getConfirmDialog() + ";");
        confirmationText.setTextFill(Color.web(theme.getConfirmationText()));
        // TODO

        // Assigns images for confirmation dialog image views with recoloring
        Image exImage = new Image(getClass().getResource("resources/ex.png").toExternalForm());
        Image checkImage = new Image(getClass().getResource("resources/check.png").toExternalForm());
        ex.setImage((new app.graphics.RecoloredImage(exImage, Color.web("#9F9F9F"),
                Color.web(theme.getConfirmDialogEx()))).getImage());
        check.setImage((new app.graphics.RecoloredImage(checkImage, Color.web("#9F9F9F"),
                Color.web(theme.getConfirmDialogCheck()))).getImage());
    }

    /**
     * Stores mouse and offset data upon cursor press on the confirmation dialog
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onConfirmPressed(MouseEvent mouseEvent) {
        // Updates mouse data
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();

        // Updates offset data
        offset.x = confirm.getScene().getWindow().getX() - mouse.x;
        offset.y = confirm.getScene().getWindow().getY() - mouse.y;
    }

    /**
     * Moves the confirmation dialog upon its drag
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onConfirmDrag(MouseEvent mouseEvent) {
        // Checks if cursor was not aimed at a title bar button
        EventTarget target = mouseEvent.getTarget();
        if (target != ex && target != check) {

            // Updates mouse data
            mouse.x = mouseEvent.getScreenX();
            mouse.y = mouseEvent.getScreenY();

            // Updates confirmation dialog location based on mouse and offset data
            confirm.getScene().getWindow().setX(mouse.x + offset.x);
            confirm.getScene().getWindow().setY(mouse.y + offset.y);
        }

    }

    /**
     * Closes the confirmation dialog
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onExClicked(MouseEvent mouseEvent) {
        ((Stage)confirm.getScene().getWindow()).close();
    }

    /**
     * Exits the program
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onCheckClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

}
