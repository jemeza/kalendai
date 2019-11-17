package app.calendar;

/*
 * TimeSlot.java - a kalendai class that is a characteristic of TimeSlot
 */
public class TimeSlot {
    public int id;
    private static int idOn = 0;
    private Time startT;
    private Time endT;
    private String title="Title";
    private String description="Description";
    private Duration duration;
    private Priority importance;

    /*
     * test overload
     */
    public TimeSlot(String start){
        this(start, start, "", null, "", Priority.Normal);
    }

    /*
    public timeSlot(String start, String ending){
        this(start, ending, "Title");
    }

    public timeSlot(String start, String ending, String newTitle){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        importance=Priority.Normal;
    }

    public timeSlot(String start, String ending, String newTitle, String newDuration){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        importance=Priority.Normal;
        duration= new duration(newDuration);
    }
    public timeSlot(String start, String ending, String newTitle, String newDuration, String newDescription){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        importance=5;
        duration= new duration(newDuration);
        description=newDescription;
    }

    public timeSlot(String start, String ending, String newTitle, String newDuration, String newDescription, int newImportance){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        duration= new duration(newDuration);
        description=newDescription;
        importance=newImportance;
    }
    public timeSlot(String start, String ending, String newTitle, String newDuration, String newDescription, int newImportance, String newColor){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        duration= new duration(newDuration);
        description=newDescription;
        importance=newImportance;
        color=newColor;
    }
    */

    /**
     * timeSlot object which handles creating a time slot
     * @param start - start time
     * @param ending - end time
     * @param newTitle - title
     * @param newDuration - duration
     * @param newDescription - description
     * @param newImportance - importance
     */
    public TimeSlot(String start, String ending, String newTitle, String newDuration, String newDescription, Priority newImportance){
        editTimeSlot(start, ending, newTitle, newDuration, newDescription, newImportance);
        id = idOn;
        idOn++;
    }

    public void editTimeSlot(String start, String ending, String newTitle, String newDuration, String newDescription, Priority newImportance){
        startT = new Time(start);
        endT = new Time(ending);
        title=newTitle;
        duration= new Duration(newDuration);
        description=newDescription;
        importance=newImportance;
    }

    public Duration getDuration(){
        return duration;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public Time getStart(){
        return startT;
    }
    public Time getEndT(){
        return endT;
    }
    public Priority getImportance() {
        return importance;
    }
}
