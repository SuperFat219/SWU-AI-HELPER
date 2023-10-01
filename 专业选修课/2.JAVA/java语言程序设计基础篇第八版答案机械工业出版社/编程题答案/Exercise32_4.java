import java.awt.*;
import javax.swing.*;

import java.lang.Runnable;
import java.util.*;
import java.text.*;

public class Exercise32_4 extends javax.swing.JApplet {
  private Clock clock4;
  private Clock clock3;
  private Clock clock2;
  private Clock clock1;

  public Exercise32_4() {
    clock1 = new Clock();
    clock2 = new Clock();
    clock3 = new Clock();
    clock4 = new Clock();

    setLayout(new java.awt.GridLayout(1, 0, 10, 10));

    clock1.setDateStyle(2);
    clock1.setHeader("London");
    clock1.setTimeZoneID("GMT");
    add(clock1);

    clock2.setHeader("Paris");
    clock2.setTimeZoneID("Europe/Paris");
    add(clock2);

    add(clock3);

    clock4.setHeader("Chicago");
    clock4.setTimeZoneID("CST");
    add(clock4);
//
//        clock5.setHeader("Denver");
//        clock5.setTimeZoneID("MST");
//        add(clock5);
//
//        clock6.setHeader("San Francisco");
//        clock6.setTimeZoneID("PST");
//        add(clock6);
  }

  public static void main(String[] args) {
    Exercise32_4 applet = new Exercise32_4();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setTitle("Exercise32_4");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class Clock extends Component implements Runnable {
  // Create a thread to control clock running
  protected transient Thread thread = null;

  /** Create TimeZone */
  protected TimeZone tz = TimeZone.getDefault();

  /** Holds value of property secondHandColor. */
  private java.awt.Color secondHandColor = Color.black;

  /** Holds value of property minuteHandColor. */
  private java.awt.Color minuteHandColor = Color.black;

  /** Holds value of property hourHandColor. */
  private java.awt.Color hourHandColor = Color.red;

  /** Holds value of property headerColor. */
  private java.awt.Color headerColor = Color.black;

  /** Holds value of property header. */
  private String header = "Savannah";

  /** Holds value of property showingHeader. */
  private boolean showingHeader = true;

  /** Holds value of property digitalDateTimeColor. */
  private java.awt.Color digitalDateTimeColor = Color.black;

  /** Holds value of property showingDigitalDateTime. */
  private boolean showingDigitalDateTime = true;

  /** Holds value of property dateStyle:
   * DateStyle 0-FULL, 1-LONG, 2-MEDIUM, 3-SHORT
   */
  private int dateStyle = 3;

  /** Holds value of property timeStyle:
   * 0-FULL, 1-LONG, 2-MEDIUM, 3-SHORT
   */
  private int timeStyle = 3;

  /** Holds value of property usingTimeZoneID. */
  private boolean usingTimeZoneID = true;

  /** Holds value of property timeZoneID. */
  private String timeZoneID = "EST";

  /** Holds value of property running. */
  private boolean running;

  /** Holds value of property timeZoneOffset. */
  private int timeZoneOffset;

  public Clock() {
    // Start clock
    setRunning(true);
  }

  /** Display the clock*/
  public void paint(Graphics g) {
    int clockRadius =
        (int) Math.min(getWidth(), getHeight() * 0.7 * 0.5);
    int xCenter = (getWidth()) / 2;
    int yCenter = (getHeight()) / 2;

    // Draw circle
    g.setColor(Color.black);
    g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
               2 * clockRadius, 2 * clockRadius);
    g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
    g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
    g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
    g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

    if (usingTimeZoneID) {
      tz = TimeZone.getTimeZone(timeZoneID);
    }
    else {
      tz.setRawOffset(timeZoneOffset * 1000 * 3600);

      // Get current time using GregorianCalendar
    }
    GregorianCalendar cal = new GregorianCalendar(tz);

    // Draw second hand
    int s = (int) cal.get(GregorianCalendar.SECOND);
    int sLength = (int) (clockRadius * 0.9);
    int secondx =
        (int) (xCenter + sLength * Math.sin(s * (2 * Math.PI / 60)));
    int secondy =
        (int) (yCenter - sLength * Math.cos(s * (2 * Math.PI / 60)));
    g.setColor(secondHandColor);
    g.drawLine(xCenter, yCenter, secondx, secondy);

    // Draw minute hand
    int m = (int) cal.get(GregorianCalendar.MINUTE);
    int mLength = (int) (clockRadius * 0.8);
    int minutex =
        (int) (xCenter + mLength * Math.sin(m * (2 * Math.PI / 60)));
    int minutey =
        (int) (yCenter - mLength * Math.cos(m * (2 * Math.PI / 60)));
    g.setColor(minuteHandColor);
    g.drawLine(xCenter, yCenter, minutex, minutey);

    // Draw hour hand
    int h = (int) cal.get(GregorianCalendar.HOUR_OF_DAY);
    int hLength = (int) (clockRadius * 0.7);
    int hourx = (int) (xCenter + hLength *
                       Math.sin( (h + m / 60.0) * (2 * Math.PI / 12)));
    int houry = (int) (yCenter - hLength *
                       Math.cos( (h + m / 60.0) * (2 * Math.PI / 12)));
    g.setColor(hourHandColor);
    g.drawLine(xCenter, yCenter, hourx, houry);

    if (showingHeader) {
      // Display header
      FontMetrics fm = g.getFontMetrics();
      g.setColor(headerColor);
      g.drawString(header, (getWidth() -
                            fm.stringWidth(header)) / 2,
                   (yCenter - clockRadius) / 2);
    }

    if (showingDigitalDateTime) {
      // Set display format in specified style, locale and timezone
      DateFormat myFormat = DateFormat.getDateTimeInstance
          (dateStyle, timeStyle, getLocale());
      myFormat.setTimeZone(tz);

      // Display time
      String today = myFormat.format(cal.getTime());
      FontMetrics fm = g.getFontMetrics();
      g.setColor(digitalDateTimeColor);
      g.drawString(today, (getWidth() -
                           fm.stringWidth(today)) / 2,
                   yCenter + clockRadius + 30);
    }
  }

  /** Run the clock */
  public void run() {
    //TODO: implement this java.lang.Runnable method;
    while (true) {
      if (thread == null) {
        return;
      }

      try {
        thread.sleep(1000);
      }
      catch (InterruptedException e) {}
      repaint();
    }
  }

  /** Start the clock */
  public void start() {
    if (thread == null) {
      thread = new Thread(this);
      thread.start();
    }
  }

  /** Stop the clock */
  public void stop() {
    if (thread != null) {
      thread = null;
    }
  }

  public java.awt.Color getSecondHandColor() {
    return secondHandColor;
  }

  public void setSecondHandColor(java.awt.Color secondHandColor) {
    this.secondHandColor = secondHandColor;
  }

  public java.awt.Color getMinuteHandColor() {
    return minuteHandColor;
  }

  public void setMinuteHandColor(java.awt.Color minuteHandColor) {
    this.minuteHandColor = minuteHandColor;
  }

  public java.awt.Color getHourHandColor() {
    return hourHandColor;
  }

  public void setHourHandColor(java.awt.Color hourHandColor) {
    this.hourHandColor = hourHandColor;
  }

  public java.awt.Color getHeaderColor() {
    return headerColor;
  }

  public void setHeaderColor(java.awt.Color headerColor) {
    this.headerColor = headerColor;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public boolean isShowingHeader() {
    return showingHeader;
  }

  public void setShowingHeader(boolean showingHeader) {
    this.showingHeader = showingHeader;
  }

  public java.awt.Color getDigitalDateTimeColor() {
    return digitalDateTimeColor;
  }

  public void setDigitalDateTimeColor(
      java.awt.Color digitalDateTimeColor) {
    this.digitalDateTimeColor = digitalDateTimeColor;
  }

  public boolean isShowingDigitalDateTime() {
    return showingDigitalDateTime;
  }

  public void setShowingDigitalDateTime(
      boolean showingDigitalDateTime) {
    this.showingDigitalDateTime = showingDigitalDateTime;
  }

  public int getDateStyle() {
    return dateStyle;
  }

  public void setDateStyle(int dateStyle) {
    this.dateStyle = dateStyle;
  }

  public int getTimeStyle() {
    return timeStyle;
  }

  public void setTimeStyle(int timeStyle) {
    this.timeStyle = timeStyle;
  }

  public boolean isUsingTimeZoneID() {
    return usingTimeZoneID;
  }

  public void setUsingTimeZoneID(boolean usingTimeZoneID) {
    this.usingTimeZoneID = usingTimeZoneID;
  }

  public String getTimeZoneID() {
    return timeZoneID;
  }

  public void setTimeZoneID(String timeZoneID) {
    this.timeZoneID = timeZoneID;
  }

  public boolean isRunning() {
    return running;
  }

  public void setRunning(boolean running) {
    this.running = running;
    if (running) {
      start();
    }
    else {
      stop();
    }
  }

  public int getTimeZoneOffset() {
    return timeZoneOffset;
  }

  public void setTimeZoneOffset(int timeZoneOffset) {
    this.timeZoneOffset = timeZoneOffset;
  }

  /** Set the preferred size for the clock */
  public Dimension getPreferredSize() {
    return new Dimension(200, 200);
  }
}
