package app.calendar;

/*
 * duration.java-a kalendai class that is a characteristic of timeSlot
 */
public class timeSlot {
    public time startT;
    public time endT;
    public String title="Title";
    public String description="Description";
    public duration duration;
    public int importance;
     String color="blue";

    public timeSlot(String start){
        startT = new time(start);
        endT = new time(start);
        endT.hour++;
        duration = new duration(startT, endT);
        title="Title";
        importance=5;
    }

    public timeSlot(String start, String ending){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        importance=5;
    }

    public timeSlot(String start, String ending, String newTitle){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        importance=5;
    }

    public timeSlot(String start, String ending, String newTitle, String newDuration){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        importance=5;
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
        importance=5;
        duration= new duration(newDuration);
        description=newDescription;
        importance=newImportance;
    }
    public timeSlot(String start, String ending, String newTitle, String newDuration, String newDescription, int newImportance, String newColor){
        startT = new time(start);
        endT = new time(ending);
        duration = new duration(startT, endT);
        title=newTitle;
        importance=5;
        duration= new duration(newDuration);
        description=newDescription;
        importance=newImportance;
        color=newColor;
    }
    public duration getDuration(){
        return duration;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}
