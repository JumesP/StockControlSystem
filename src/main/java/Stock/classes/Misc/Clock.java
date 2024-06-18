package Stock.classes.Misc;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Clock {

    private static String Day;
    private static String Month;
    private static String Year;

    public static String Hour;
    public static String Minute;
    public static String Second;

    public Clock() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        Day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        Month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        Year = String.valueOf(calendar.get(Calendar.YEAR));

        if (Day.length() == 1) { Day = "0" + Day; }
        if (Month.length() == 1) { Month = "0" + Month; }

        Hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        Minute = String.valueOf(calendar.get(Calendar.MINUTE));
        Second = String.valueOf(calendar.get(Calendar.SECOND));

        if (Hour.length() == 1) { Hour = "0" + Hour; }
        if (Minute.length() == 1) { Minute = "0" + Minute; }
        if (Second.length() == 1) { Second = "0" + Second; }
    }

    public int getDay() {
        return Integer.parseInt(Day);
    }

    public int getMonth() {
        return Integer.parseInt(Month);
    }

    public String getYear() {
        return Year;
    }

    public String getDate() {
        return Day + "/" + Month + "/" + Year;
    }

    public String getTime() {
        return Hour + ":" + Minute + ":" + Second;
    }

    public String getCurrentDate(){
        // Returns the current date in the format "DD-mm-YYYY"
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    public int sortableDate(){
        // Converts a date in the format "DD/mm/YYYY" to a sortable integer being "YYYYmmDD"
        String date = this.getDate();
        String[] dateArray = date.split("/");
        String sortableDate = dateArray[2] + dateArray[1] + dateArray[0];
        System.out.println("Sortable Date: " + sortableDate);
        return Integer.parseInt(sortableDate);
    }

    public String stringSortableDate(){
        // Converts a date in the format "DD/mm/YYYY" to a sortable integer being "YYYYmmDD"
        String date = getDate();
        String[] dateArray = date.split("/");
        return dateArray[2] + dateArray[1] + dateArray[0];
    }

    public int date() {
        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();

        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) { day = "0" + day; }
        String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
        if (month.length() == 1) { month = "0" + month; }
        String currentDateTime = currentDate.toString();
        String year = Integer.toString(calendar.get(Calendar.YEAR));

        int sortableDate = Integer.parseInt(year + month + day);

        System.out.println("Day: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
        System.out.println(currentDateTime);
        System.out.println("Sortable Date: " + sortableDate);

        return sortableDate;
    }

    public static String formatDateForUser(int Date) {
        // changes the date from "YYYYmmDD" to "DD-mm-YYYY"
        String DateString = String.valueOf(Date);
        String Year = DateString.substring(0, 4);
        String Month = DateString.substring(4, 6);
        String Day = DateString.substring(6, 8);
        return Day + "-" + Month + "-" + Year;
    }
}
