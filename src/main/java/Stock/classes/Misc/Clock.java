package Stock.classes.Misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    public static int sortableDateHyphen(String date){
        // Converts a date in the format "DD-mm-YYYY" to a sortable integer being "YYYYmmDD"
        String[] dateArray = date.split("-");
        String sortableDate = dateArray[2] + dateArray[1] + dateArray[0];
        System.out.println("Sortable Date: " + sortableDate);
        return Integer.parseInt(sortableDate);
    }

    public static int getSortableDateInAWeek(int Date) {
//        change from "YYYYmmDD" to "YYYYmmDD" + 7 days
        String DateString = String.valueOf(Date);
        String Year = DateString.substring(0, 4);
        String Month = DateString.substring(4, 6);
        String Day = DateString.substring(6, 8);

        int day = Integer.parseInt(Day);
        int month = Integer.parseInt(Month)-1;
        int year = Integer.parseInt(Year);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        calendar.add(Calendar.DAY_OF_YEAR, 7);


        Day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        Month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        Year = String.valueOf(calendar.get(Calendar.YEAR));


        if (Day.length() == 1) { Day = "0" + Day; }
        if (Month.length() == 1) { Month = "0" + Month; }


        return Integer.parseInt(Year + Month + Day);
    }

    public static int getSortableDateInAdjust(int Date, int adjust) {
//        change from "YYYYmmDD" to "YYYYmmDD" + adjust days
        String DateString = String.valueOf(Date);
        String Year = DateString.substring(0, 4);
        String Month = DateString.substring(4, 6);
        String Day = DateString.substring(6, 8);

        int day = Integer.parseInt(Day);
        int month = Integer.parseInt(Month)-1;
        int year = Integer.parseInt(Year);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        calendar.add(Calendar.DAY_OF_YEAR, adjust);


        Day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        Month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        Year = String.valueOf(calendar.get(Calendar.YEAR));


        if (Day.length() == 1) { Day = "0" + Day; }
        if (Month.length() == 1) { Month = "0" + Month; }


        return Integer.parseInt(Year + Month + Day);
    }


    public static List<String> splitDates(String pair) {
        // changes
        String[] datePair = pair.split(" - ");

        String Week_Start = datePair[0];
        String Week_End = datePair[1];

        return List.of(Week_Start, Week_End);
    }

    public static String firstDayOfTheWeekToWeekPair(int date) {
        // change date from YYYYmmDD to "DD-mm-YYYY - DD-mm-YYYY" where the first date is the first day of the week
        String firstDay = formatDateForUser(date);
        String lastDay = formatDateForUser(getSortableDateInAdjust(date, 6));

        return firstDay + " - " + lastDay;
    }

    public static String lastDayOfTheWeekToWeekPair(int date) {
        // change date from YYYYmmDD to "DD-mm-YYYY - DD-mm-YYYY" where the first date is the first day of the week
        String lastDay = formatDateForUser(date);
        String firstDay = formatDateForUser(getSortableDateInAdjust(date, -6));

        return firstDay + " - " + lastDay;
    }



}
