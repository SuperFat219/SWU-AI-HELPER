import javax.swing.*;

/**
 * 8.6
 * 2021.12.4
 */
public class calendar {
    //TODO:8.6
    static String output = "";

    public static void main(String[] args) {
        String yearString = JOptionPane.showInputDialog(null, "Enter full year (i.e. 2001):", "Enter Year", JOptionPane.QUESTION_MESSAGE);
        int year = Integer.parseInt(yearString);
        String monthString = JOptionPane.showInputDialog(null, "Enter month in number between 1 and 12:", "Enter Month", JOptionPane.QUESTION_MESSAGE);
        int month = Integer.parseInt(monthString);
        printMonth(year, month);
        JOptionPane.showMessageDialog(null, output);
    }

    static void printMonth(int year, int month) {
        int startDay = getStartDay(year, month);
        int numOfDaysInMonth = getNumOfDaysInMonth(year, month);
        printMonthTitle(year, month);
        printMonthBody(startDay, numOfDaysInMonth);
    }

    static int getStartDay(int year, int month) {
        int startDay1800 = 3;
        long totalNumOfDays = getTotalNumOfDays(year, month);
        return (int) ((totalNumOfDays + startDay1800) % 7);
    }

    static long getTotalNumOfDays(int year, int month) {
        long total = 0;
        for (int i = 1800; i < year; i++)
            if (isLeapYear(i)) {
                total = total + 366;
            } else {
                total = total + 365;
            }
        for (int i = 1; i < month; i++) {
            total = total + getNumOfDaysInMonth(year, i);
        }
        return total;

    }

    static int getNumOfDaysInMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) return 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
        if (month == 2) {
            if (isLeapYear(year)) return 29;
            else return 28;
        }
        return 0;

    }

    static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
    }

    static void printMonthBody(int startDay, int numOfDaysInMonth) {
        int i = 0;
        for (i = 0; i < startDay; i++) {
            output += "        ";
        }
        for (i = 1; i <= numOfDaysInMonth; i++) {
            if (i < 10) {
                output += "      " + i;
            } else {
                output += "    " + i;
            }
            if ((i + startDay) % 7 == 0) {
                output += "\n";
            }

        }
        output += "\n";

    }

    static void printMonthTitle(int year, int month) {
        output += " " + getMonthName(month) + ", " + year + "\n";
        output += "------------------------------------\n";
        output += " Sun Mon Tue Wed Thu Fri Sat\n";
    }

    static String getMonthName(int month) {
        String monthName = null;
        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
        }
        return monthName;
    }
}
