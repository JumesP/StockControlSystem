package Stock.classes.Misc;

import java.util.Calendar;
import java.util.Date;

public class Clock {

    private String Day;
    private String Month;
    private final String Year;

    public Clock() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        Day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        Month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        Year = String.valueOf(calendar.get(Calendar.YEAR));

        if (Day.length() == 1) { Day = "0" + Day; }
        if (Month.length() == 1) { Month = "0" + Month; }
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

//        date.setText(day + "/" + month + "/" + year);

        return sortableDate;
    }
}
