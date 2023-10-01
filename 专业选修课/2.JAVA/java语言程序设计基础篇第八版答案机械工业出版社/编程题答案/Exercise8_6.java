import javax.swing.JOptionPane;

public class Exercise8_6 {
  static String output = "";

  /** Main method */
  public static void main(String[] args) {
    // Prompt the user to enter year
    String yearString = JOptionPane.showInputDialog(null,
      "Enter full year (i.e. 2001):",
      "Enter Year", JOptionPane.QUESTION_MESSAGE);

    // Convert string into integer
    int year = Integer.parseInt(yearString);

    // Prompt the user to enter month
    String monthString = JOptionPane.showInputDialog(null,
      "Enter month in number between 1 and 12:",
      "Enter Month", JOptionPane.QUESTION_MESSAGE);

    // Convert string into integer
    int month = Integer.parseInt(monthString);

    // Print calendar for the month of the year
    printMonth(year, month);

    JOptionPane.showMessageDialog(null, output);
  }

  /** Print the calendar for a month in a year */
  static void printMonth(int year, int month) {
    // Get start day of the week for the first date in the month
    int startDay = getStartDay(year, month);

    // Get number of days in the month
    int numOfDaysInMonth = getNumOfDaysInMonth(year, month);

    // Print headings
    printMonthTitle(year, month);

    // Print body
    printMonthBody(startDay, numOfDaysInMonth);
  }

  /** Get the start day of the first day in a month */
  static int getStartDay(int year, int month) {
    // Get total number of days since 1/1/1800
    int startDay1800 = 3;
    long totalNumOfDays = getTotalNumOfDays(year, month);

    // Return the start day
    return (int)((totalNumOfDays + startDay1800) % 7);
  }

  /** Get the total number of days since Jan 1, 1800 */
  static long getTotalNumOfDays(int year, int month) {
    long total = 0;

    // Get the total days from 1800 to year -1
    for (int i = 1800; i < year; i++)
    if (isLeapYear(i))
      total = total + 366;
    else
      total = total + 365;

    // Add days from Jan to the month prior to the calendar month
    for (int i = 1; i < month; i++)
      total = total + getNumOfDaysInMonth(year, i);

    return total;
  }

  /** Get the number of days in a month */
  static int getNumOfDaysInMonth(int year, int month) {
    if (month == 1 || month==3 || month == 5 || month == 7 ||
      month == 8 || month == 10 || month == 12)
      return 31;

    if (month == 4 || month == 6 || month == 9 || month == 11)
      return 30;

    if (month == 2)
      if (isLeapYear(year))
        return 29;
      else
        return 28;

    return 0; // If month is incorrect.
  }

  /** Determine if it is a leap year */
  static boolean isLeapYear(int year) {
    if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
      return true;

    return false;
  }

  /** Print month body */
  static void printMonthBody(int startDay, int numOfDaysInMonth) {
    // Pad space before the first day of the month
    int i = 0;
    for (i = 0; i < startDay; i++)
      output += "    ";

    for (i = 1; i <= numOfDaysInMonth; i++) {
      if (i < 10)
        output += "   " + i;
      else
        output += "  " + i;

      if ((i + startDay) % 7 == 0)
        output += "\n";
    }

    output += "\n";
  }

  /** Print the month title, i.e. May, 1999 */
  static void printMonthTitle(int year, int month) {
    output += "         " + getMonthName(month)
      + ", " + year + "\n";
    output += "-----------------------------\n";
    output += " Sun Mon Tue Wed Thu Fri Sat\n";
  }

  /** Get the English name for the month */
  static String getMonthName(int month) {
    String monthName = null;
    switch (month) {
      case 1: monthName = "January"; break;
      case 2: monthName = "February"; break;
      case 3: monthName = "March"; break;
      case 4: monthName = "April"; break;
      case 5: monthName = "May"; break;
      case 6: monthName = "June"; break;
      case 7: monthName = "July"; break;
      case 8: monthName = "August"; break;
      case 9: monthName = "September"; break;
      case 10: monthName = "October"; break;
      case 11: monthName = "November"; break;
      case 12: monthName = "December";
    }

    return monthName;
  }
}
