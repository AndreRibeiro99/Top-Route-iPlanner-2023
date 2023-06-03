package classes;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

//Class Date

public class Date implements Comparable<Date>, Serializable {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public Date(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public Date() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        this.month = gregorianCalendar.get(Calendar.MONTH) + 1; // metodo, os meses começa a 0
        this.year = gregorianCalendar.get(Calendar.YEAR);
        this.hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregorianCalendar.get(Calendar.MINUTE);
    }

    public boolean beforeDate(Date d) {
        return compareTo(d) == -1;
    }

    public boolean afterDate(Date d) {
        return compareTo(d) == 1;
    }

    public boolean isLeapYear() {
        return this.year % 4 == 0 && (this.year % 400 == 0 || this.year % 100 != 0);
    }

    public void incrementDate() {
    }

    public int differenceYears(Date d) {
        return Math.abs(this.year - d.year);
    }

    public int differenceMonths(Date d) {
        return Math.abs(this.month - d.month);
    }

    public int compareTo(Date d) {
        if (this.year == d.year && this.month == d.month && this.day == d.day && this.hour == d.hour && this.minute == d.minute) {
            return 0;
        } else if (this.year == d.year) {
            if (this.month == d.month) {
                if (this.day == d.day) {
                    if (this.hour == d.hour) {
                        return this.minute < d.minute ? -1 : 1;
                    }
                    return this.hour < d.hour ? -1 : 1;
                }
                return this.day < d.day ? -1 : 1;
            } else {
                return this.month < d.month ? -1 : 1;
            }
        } else {
            return this.year < d.year ? -1 : 1;
        }
    }

    public int compareHourTo(Date d) {
        if (this.hour == d.hour && this.minute == d.minute) {
            return 0;
        } else if (this.hour == d.hour) {
            return this.minute < d.minute ? -1 : 1;
        } else {
            return this.hour < d.hour ? -1 :1;
        }
    }

    @Override
    public String toString() {
        return day +
                "/" + month +
                "/" + year +
                " " + hour +
                ":" + minute ;
    }

    public int daysMonth() {
        switch (this.month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return isLeapYear() ? 29 : 28;
            default:
                return 30;
        }
    }

    public static int daysMonth(int month, int year,int hour,int minute) {
        Date d = new Date(1, month, year,hour,minute);
        return d.daysMonth();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

}