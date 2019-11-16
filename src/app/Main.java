package app;

import app.calendar.time;
import app.calendar.timeSlot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
    //time asdf=new time("11:00am");

    public static void main(String[] args) {
        //new App().run(args);
        timeSlot timeSlot1=new timeSlot("11:00");
        System.out.println(timeSlot1.startT.getMilitaryHour());
        System.out.println(timeSlot1.endT.getMilitaryHour());
        System.out.println(timeSlot1.getDuration().getDurationString());

//        System.out.println(timeSlot1.startT.getStandardHour());
//        System.out.println(timeSlot1.startT.getIsAM());
//        System.out.println(timeSlot1.startT.getMin());
//        System.out.println(timeSlot1.startT.getMilitaryTimeString());
//        System.out.println(timeSlot1.startT.getStandardHourString());


    }
}
