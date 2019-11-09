package app;

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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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
     */
    private Mouse mouse;
    private Offset offset;
    private Theme theme;

    // The background of the app
    @FXML
    AnchorPane anchor;
    // The title bar of the app
    @FXML
    HBox titleBar;

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
        titleBar.setStyle("-fx-background-color: " + theme.getTitleBarMain() + ";");
        // TODO

        // Assigns images for title bar image views with recoloring
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
        // Updates mouse data
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();

        // Updates offset data
        offset.x = anchor.getScene().getWindow().getX() - mouse.x;
        offset.y = anchor.getScene().getWindow().getY() - mouse.y;
    }

    /**
     * Moves the program window upon the title bar's drag
     * @param mouseEvent - a MouseEvent that holds the properties of the calling event
     */
    @FXML
    public void onTitleBarDragged(MouseEvent mouseEvent) {
        // Checks if cursor was not aimed at a title bar button
        EventTarget target = mouseEvent.getTarget();

        // Updates mouse data
        mouse.x = mouseEvent.getScreenX();
        mouse.y = mouseEvent.getScreenY();

        // Updates program window location based on mouse and offset data
        anchor.getScene().getWindow().setX(mouse.x + offset.x);
        anchor.getScene().getWindow().setY(mouse.y + offset.y);
    }

}

