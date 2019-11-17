package app.calendar;

/*
* Duration.java - a kalendai class that sets the duration characteristic of a TimeSlot
*/
public class Duration {
    String durationT;
    int hours;
    int min;

    public Duration(String timeStr) {
        timeStr=timeStr.toLowerCase();
        if(timeStr.contains(".")){
            hours=Integer.parseInt(timeStr.substring(0, timeStr.indexOf(".")));
            try{
                int minDec= Integer.parseInt(timeStr.substring(timeStr.indexOf(".")+1));
                min=60*((minDec*10)/100);
            } catch(NumberFormatException e){
                min=0;
            }
        }
        if(timeStr.contains(":")){
            try{
                hours=Integer.parseInt(timeStr.substring(0, timeStr.indexOf(":")));

            }catch(NumberFormatException e){
                hours=1;
            }
            try{
                min=Integer.parseInt(timeStr.substring(timeStr.indexOf(":")+1));
            }catch(NumberFormatException e){
                min=0;
            }
        }
        //String "1hr 30 min" or "1hour30min" or "1h30m" or "1:30" or "1.5 hours"
    }

    public Duration(Time start, Time end) {
        hours = end.getMilitaryHour()-start.getMilitaryHour() ;
        min = end.getMin()-start.getMin() ;
        while (min < 0) {
            hours--;
            min = 60 - min;
        }
    }
    public int getMin(){
        return min;
    }
    public int getHours(){
        return hours;
    }
    public String getDurationString(){
        return String.format("%02d", hours)+":"+ String.format("%02d", min);
    }

    public boolean greaterThan(Duration duration) {
        int thisMinutes = getAbsoluteMin();
        int compMinutes = duration.getAbsoluteMin();
        return thisMinutes > compMinutes;
    }
    public int getAbsoluteMin() {
        return hours * 60 + min;
    }
}
