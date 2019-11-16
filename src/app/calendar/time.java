
package app.calendar;

/*
 * time.java-a kalendai class that organizes that sets the time characteristic of time slot
 */
public class time {
    //String standardTime;
    //String militaryTime;
    int hour;
    int min;
    boolean isAM=true;

    /*
     *a constructor for class time
     *
     * @param String time is a string that contains the time
     */
    public time(String time){
        timeHelper(time);
    }

    /*
    *
    */
    void timeHelper(String timeStr){
        try {
            hour += Integer.parseInt(timeStr.substring(0, timeStr.indexOf(':')));
        } catch (NumberFormatException e) {
//        hour = 12;
       }
        timeStr=timeStr.toLowerCase();
        if((timeStr.contains("am")||timeStr.contains("pm")) && hour<=12){
            if(timeStr.contains("am")) {
                isAM = true;
                timeStr = timeStr.replace("am","");
            } else if(timeStr.contains("pm")){
                if(hour<12) {
                    isAM=false;
                    timeStr = timeStr.replace("pm","");
                    hour += 12;
                }
                isAM=false;
            }
        } else if(Integer.parseInt(timeStr.substring(0, timeStr.indexOf(':')))>=12){
            isAM=false;
        }
        try {
            min = Integer.parseInt(timeStr.substring(timeStr.indexOf(':') + 1)); //, timeStr.indexOf(':') + 4));
        } catch (NumberFormatException e) {
            min = 0;
        }
        keepWithinRange();
    }
    void keepWithinRange(){
        if(min>=60){
            hour++;
            min%=60;
        }
        if(hour>=24){
            hour%=24;
        }
    }
    public int getMin(){
        keepWithinRange();
        return min;
    }

    public int getMilitaryHour(){
        keepWithinRange();
        return hour;
    }

    public int getStandardHour() {
        keepWithinRange();
        if(hour==0){
            hour=12;
            isAM=true;
        } else if(hour>12){
            hour%=12;
        }else if(hour>=12){
            isAM=false;
        }
        return hour;
    }

    public boolean getIsAM(){
        return isAM;
    }
    public String getMilitaryTimeString(){
        return Integer.toString(getMilitaryHour())+':'+Integer.toString(getMin());
    }
    public String getStandardHourString(){
        String standardTime = "";
        standardTime += Integer.toString(getStandardHour());
        standardTime += ':';
        standardTime += Integer.toString(getMin());
        if(isAM) {
            standardTime += "am";
        }else{
            standardTime += "pm";
        }
        return standardTime;
    }
}
