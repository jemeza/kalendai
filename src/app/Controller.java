package app;

import app.calendar.Priority;
import app.calendar.Schedule;
import app.calendar.TimeSlot;
import app.graphics.Theme;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

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
     * canvas - a Canvas to draw stuff on
     * tick - an AnimationTimer to handle time ticks
     * mouse - a Mouse for storing cursor data
     * offset - an Offset for storing offset data
     * theme - a Theme for handling app coloring schemes
     * confirmCloseOpen - a boolean for storing if the confirm close dialog is open
     * schedule - a Schedule which represents shown data
     * timeSlotColors - an ArrayList for holding the colors of the schedule
     */
    private Canvas canvas;
    private AnimationTimer tick;

    private Mouse mouse;
    private Offset offset;

    private Theme theme;
    private boolean confirmCloseOpen;

    private Schedule schedule;
    private ArrayList<Color> timeSlotColors;

    /**
     * Initialize tick timer
     */
    private AnimationTimer initTimer() {
        tick = new AnimationTimer() {

            // Nanosecond time of last update
            long last = 0;

            // Handles ticks
            @Override
            public void handle(long now) {

                // Redraw the canvas when a certain amount of time passes
                if(now - last > 15000000) {
                    last = now;
                    redraw();
                }
            }
        };
        return tick;
    }

    /**
     * Assign colors to the schedule
     */
    private void assignColors() {
        timeSlotColors = new ArrayList<>();
        for(int i = 0; i < schedule.getScheduleSize(); i++) {
            Color color = Color.BLACK;
            switch(schedule.getTimeSlotAtIndex(i).getImportance()) {
                case Urgent:
                    color = Color.rgb(256 - (int)(Math.random() * 64),
                                    (int)(Math.random() * 64),
                                    (int)(Math.random() * 64));
                    break;
                case Important:
                    color = Color.rgb(256 - (int)(Math.random() * 64),
                            256 - (int)(Math.random() * 64),
                            (int)(Math.random() * 64));
                    break;
                case Normal:
                    color = Color.rgb((int)(Math.random() * 64),
                            256 - (int)(Math.random() * 64),
                            (int)(Math.random() * 64));
                    break;
                case Lax:
                    color = Color.rgb((int)(Math.random() * 64),
                            (int)(Math.random() * 64),
                            256 - (int)(Math.random() * 64));
                    break;
            }
            timeSlotColors.add(color);
        }
    }

    /**
     * Redraw canvas components
     */
    private void redraw() {

        // Get canvas dimensions
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        // Set default sizes
        double radius = height / 2;

        // Use canvas GraphicsContext to draw
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.ROYALBLUE);
        for(int i = 0; i < schedule.getScheduleSize(); i++) {
            TimeSlot timeSlot = schedule.getTimeSlotAtIndex(i);
            int timeSlotDuration = timeSlot.getDuration().getAbsoluteMin();
            drawTimeSlot(radius * (timeSlotDuration - schedule.getMinDuration() + 1) /
                            (schedule.getMaxDuration() - schedule.getMinDuration() + 1) / 2 + radius / 2,
                    timeSlot.getStart().getAbsoluteMin(),
                    timeSlot.getDuration().getAbsoluteMin(),
                    timeSlotColors.get(i));
        }
        // TODO
    }

    /**
     * Draw a centered and proportioned TimeSlot onto the canvas
     * @param radius - a double which represents the radius of the object
     * @param startAngle - a double which represents the starting angle of the object
     * @param angleSpan - a double which represents the span of the angle of the object
     */
    private void drawTimeSlot(double radius, double startAngle, double angleSpan, Color color) {
        drawTimeSlot(radius * 2, radius * 2, startAngle, angleSpan, color);
    }

    /**
     * Draw a centered TimeSlot onto the canvas
     * @param width - a double which represents the width of the object
     * @param height - a double which represents the height of the object
     * @param startAngle - a double which represents the starting angle of the object
     * @param angleSpan - a double which represents the span of the angle of the object
     */
    private void drawTimeSlot(double width, double height,
                              double startAngle, double angleSpan,
                              Color color) {
        drawTimeSlot(canvas.getWidth() / 2 - width / 2,
                     canvas.getHeight() / 2 - height / 2,
                        width,
                        height,
                        startAngle,
                        angleSpan,
                        color);
    }

    /**
     * Draw a TimeSlot onto the canvas
     * @param x - a double which represents the x-coordinate
     * @param y - a double which represents the y-coordinate
     * @param width - a double which represents the width of the object
     * @param height - a double which represents the height of the object
     * @param startAngle - a double which represents the starting angle of the object
     * @param angleSpan - a double which represents the span of the angle of the object
     */
    private void drawTimeSlot(double x, double y,
                              double width, double height,
                              double startAngle, double angleSpan,
                              Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Paint paint;
        gc.setFill(color);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.fillArc(x, y, width, height, startAngle, angleSpan, ArcType.ROUND);
        gc.strokeArc(x, y, width, height, startAngle, angleSpan, ArcType.ROUND);
    }

    // The background of the app
    @FXML
    VBox anchor;
    // The title bar of the app
    @FXML
    HBox titleBar;
    // The canvas holder
    @FXML
    AnchorPane canvasHolder;
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

        // Add a canvas to draw
        canvas = new Canvas();
        canvas.widthProperty().bind(canvasHolder.widthProperty());
        canvas.heightProperty().bind(canvasHolder.heightProperty());
        canvasHolder.getChildren().add(canvas);

        // Initialize schedule
        schedule = new Schedule();
        // TODO
        // TODO REMOVE
        schedule.insertTimeSlot(new TimeSlot("1:00",
                "7:00",
                "Sleep Time",
                "6:00",
                "It is time to sleep!",
                Priority.Urgent));
        schedule.insertTimeSlot(new TimeSlot("7:30",
                "8:30",
                "Shower Time",
                "1:00",
                "It is time to scrub!",
                Priority.Lax));
        schedule.insertTimeSlot(new TimeSlot("9:00",
                "10:30",
                "Breakfast Time",
                "1:30",
                "It is time to break the fast!",
                Priority.Normal));
        schedule.insertTimeSlot(new TimeSlot("10:00",
                "11:30",
                "Joke Class",
                "1:30",
                "You might want to attend your joke class...",
                Priority.Lax));
        schedule.insertTimeSlot(new TimeSlot("10:00",
                "15:30",
                "School",
                "5:30",
                "School Time!",
                Priority.Important));
        schedule.insertTimeSlot(new TimeSlot("12:00",
                "13:30",
                "Lunch Time!",
                "1:30",
                "Time for lunch!",
                Priority.Normal));
        schedule.insertTimeSlot(new TimeSlot("13:00",
                "14:30",
                "Calculus",
                "1:30",
                "Time for Calculus!",
                Priority.Urgent));
        schedule.insertTimeSlot(new TimeSlot("14:30",
                "15:30",
                "Computer Science",
                "1:00",
                "Time for Computer Science!",
                Priority.Important));
        assignColors();
        // TODO END REMOVE

        // Add frame ticker
        initTimer().start();

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

