package app.calendar;

import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Schedule.java - a kalendai class to handle schedules
 */
public class Schedule {

    /*
     * schedule - an ArrayList of TimeSlots to hold schedule
     * min - an int that represents the absolute minimum duration
     * max - an int that represents the absolute maximum duration
     */
    private ArrayList<TimeSlot> schedule;
    private int min;
    private int max;

    /**
     * Initializes a schedule as a list
     */
    public Schedule() {
        schedule = new ArrayList<>();
    }

    /**
     * Inserts a TimeSlot, keeping a decreasing TimeSlot duration order
     * @param timeSlot - a TimeSlot which is inserted into the schedule;
     */
    public void insertTimeSlot(TimeSlot timeSlot) {
        int timeSlotDuration = timeSlot.getDuration().getAbsoluteMin();
        if(schedule.size() == 0) {
            schedule.add(timeSlot);
            min = timeSlotDuration;
            max = timeSlotDuration;
            return;
        }
        for(int i = 0; i < schedule.size(); i++) {
            if(timeSlot.getDuration().greaterThan(schedule.get(i).getDuration())) {
                schedule.add(i, timeSlot);
                if(i == 0) min = timeSlotDuration;
                return;
            }
        }
        schedule.add(timeSlot);
        max = timeSlotDuration;
    }

    /**
     * Floats a TimeSlot with a given id to the top of the drawing pool
     * @param id - an int which represents the id of the TimeSlot to float
     * @return boolean - true if successful float, false if id does not exist
     */
    public boolean floatTimeSlot(int id) {
        for(int i = 0; i < schedule.size(); i++) {
            if(id == schedule.get(i).id) {
                int floatTo = i + 1;
                while(floatTo < schedule.size() &&
                        !schedule.get(i).getDuration().greaterThan(schedule.get(floatTo).getDuration())) {
                    floatTo++;
                }
                if(floatTo != i + 1) {
                    schedule.add(floatTo - 1, schedule.remove(i));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a TimeSlot from schedule given an index
     * @param index - an int which will act as the iterator from calling point
     * @return TimeSlot - the TimeSlot retrieved from schedule
     */
    public TimeSlot getTimeSlotAtIndex(int index) {
        return schedule.get(index);
    }

    /**
     * Retrieves the number of events in schedule
     * @return int - the number of events in schedule
     */
    public int getScheduleSize() {
        return schedule.size();
    }

    /**
     * Gets the minimum duration in absolute minutes
     * @return int - the minimum duration in absolute minutes
     */
    public int getMinDuration() {
        return min;
    }

    /**
     * Gets the maximum duration in absolute minutes
     * @return int - the maximum duration in absolute minutes
     */
    public int getMaxDuration() {
        return max;
    }


}
