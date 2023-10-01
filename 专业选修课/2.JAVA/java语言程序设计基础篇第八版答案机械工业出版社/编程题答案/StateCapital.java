import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StateCapital extends JApplet {
  boolean isStandalone = false;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JButton jButton1 = new JButton();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JLabel jlblQuestionCount = new JLabel();
  FlowLayout flowLayout1 = new FlowLayout();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JLabel jlblCorrectCount = new JLabel();
  FlowLayout flowLayout2 = new FlowLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel7 = new JPanel();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel7 = new JLabel();

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  //Construct the applet
  public StateCapital() {
  }

  //Initialize the applet
  public void init() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    this.setSize(new Dimension(392, 174));
    setLayout(borderLayout1);
    jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
    jLabel4.setText("Correct count: ");
    jlblCorrectCount.setText("");
    jPanel4.setLayout(flowLayout2);
    flowLayout2.setAlignment(FlowLayout.RIGHT);
    jPanel5.setLayout(borderLayout3);
    jLabel6.setText("");
    jPanel6.setLayout(flowLayout3);
    jTextField1.setColumns(15);
    jTextField1.addActionListener(new StateCapital_jTextField1_actionAdapter(this));
    jPanel7.setLayout(flowLayout4);
    jLabel7.setText("");
    borderLayout3.setVgap(10);
    jlblQuestionCount.setDisplayedMnemonic('0');
    jButton1.addActionListener(new StateCapital_jButton1_actionAdapter(this));
    jButton2.setText("Next Question");
    jButton2.addActionListener(new StateCapital_jButton2_actionAdapter(this));
    jButton3.setText("Submit It");
    jButton3.addActionListener(new StateCapital_jButton3_actionAdapter(this));
    add(jPanel1, java.awt.BorderLayout.NORTH);
    jButton1.setText("Start");
    jPanel2.setLayout(borderLayout2);
    jLabel2.setText("Question: ");
    jlblQuestionCount.setText("");
    jPanel3.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.LEFT);
    jPanel1.add(jLabel1);
    jPanel1.add(jButton1);
    add(jPanel2, java.awt.BorderLayout.CENTER);
    jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);
    jPanel3.add(jLabel2);
    jPanel3.add(jlblQuestionCount);
    jPanel2.add(jPanel4, java.awt.BorderLayout.SOUTH);
    jPanel4.add(jLabel4);
    jPanel4.add(jlblCorrectCount);
    jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);
    jPanel6.add(jLabel6, null);
    jPanel6.add(jTextField1, null);
    jPanel6.add(jButton3);
    jPanel7.add(jLabel7, null);
    jPanel7.add(jButton2);
    jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);
    jPanel5.add(jPanel7, java.awt.BorderLayout.SOUTH);
    jLabel1.setText("Click Start to start a new test");
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Applet Information";
  }

  //Get parameter info
  public String[][] getParameterInfo() {
    return null;
  }

  //Main method
  public static void main(String[] args) {
    StateCapital applet = new StateCapital();
    applet.isStandalone = true;

    JFrame frame = new JFrame();
    applet.isStandalone = true;
    //EXIT_ON_CLOSE == 3;
    frame.setDefaultCloseOperation(3);
  }

  //static initializer for setting look & feel
  static {
    try {
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
  }

  String[][] statecapital = { {"Alabama", "Montgomery"}, {"Alaska", "Juneau"},
    {"Arizona", "Phoenix"}, {"Arkansas", "Little Rock"}, {"California",
    "Sacramento"}, {"Colorado", "Denver"}, {"Connecticut", "Hartford"},
    {"Delaware", "Dover"}, {"Florida", "Tallahassee"}, {"Georgia", "Atlanta"},
    {"Hawaii", "Honolulu"}, {"Idaho", "Boise"}, {"Illinois", "Springfield"},
    {"Maryland", "Annapolis"}, {"Minnesota", "Saint Paul"}, {"Iowa",
    "Des Moines"}, {"Maine", "Augusta"}, {"Kentucky", "Frankfort"}, {"Indiana",
    "Indianapolis"}, {"Kansas", "Topeka"}, {"Louisiana", "Baton Rouge"},
    {"Oregon", "Salem"}, {"Oklahoma", "Oklahoma City"}, {"Ohio", "Columbus"},
    {"North Dakota", "Bismark"}, {"New York", "Albany"}, {"New Mexico",
    "Santa Fe"}, {"New Jersey", "Trenton"}, {"New Hampshire", "Concord"},
    {"Nevada", "Carson City"}, {"Nebraska", "Lincoln"}, {"Montana", "Helena"},
    {"Missouri", "Jefferson City"}, {"Mississippi", "Jackson"},
    {"Massachusettes", "Boston"}, {"Michigan", "Lansing"}, {"Pennslyvania",
    "Harrisburg"}, {"Rhode Island", "Providence"}, {"South Carolina",
    "Columbia"}, {"South Dakota", "Pierre"}, {"Tennessee", "Nashville"},
    {"Texas", "Austin"}, {"Utah", "Salt Lake City"}, {"Vermont", "Montpelier"},
    {"Virginia", "Richmond"}, {"Washington", "Olympia"}, {"West Virginia",
    "Charleston"}, {"Wisconsin", "Madison"}, {"Wyoming", "Cheyenne"}
  };

  List list = Arrays.asList(statecapital);

  int correctCount = 0;
  int questionCount = 0;
  FlowLayout flowLayout3 = new FlowLayout();
  FlowLayout flowLayout4 = new FlowLayout();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();

  public void jButton1_actionPerformed(ActionEvent e) {
    list = Arrays.asList(statecapital);
    Collections.shuffle(list);

    correctCount = 0;
    questionCount = 0;

    jlblQuestionCount.setText(null);
    jlblCorrectCount.setText(null);
    jLabel7.setText(null);
    displayQuestion(questionCount);
  }

  private void displayQuestion(int questionCount) {
    // Prompt the user with a question
    jTextField1.setText(null);
    jlblQuestionCount.setText(questionCount + 1 + "");
    jLabel6.setText("What is the capital of " +
                    ((String[])(list.get(questionCount)))[0] + "?");
  }

  public void jTextField1_actionPerformed(ActionEvent e) {
  }

  public void jButton2_actionPerformed(ActionEvent e) {
    if (questionCount > 50) {
      jLabel7.setText("You have anwsered all questions");
    }
    else {
      questionCount++;
      displayQuestion(questionCount);
    }
  }

  public void jButton3_actionPerformed(ActionEvent e) {
    String capital = jTextField1.getText().trim();

    if (capital.equals(((String[])(list.get(questionCount)))[1])) {
      correctCount++;
      jLabel7.setText("Your anwswer is correct");
    }
    else {
      jLabel7.setText(
        "The correct answer should be " +
        ((String[])(list.get(questionCount)))[1]);
    }

    jlblCorrectCount.setText(correctCount + " ");
  }
}

class StateCapital_jButton3_actionAdapter implements ActionListener {
  private StateCapital adaptee;
  StateCapital_jButton3_actionAdapter(StateCapital adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class StateCapital_jButton2_actionAdapter implements ActionListener {
  private StateCapital adaptee;
  StateCapital_jButton2_actionAdapter(StateCapital adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class StateCapital_jTextField1_actionAdapter implements ActionListener {
  private StateCapital adaptee;
  StateCapital_jTextField1_actionAdapter(StateCapital adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTextField1_actionPerformed(e);
  }
}

class StateCapital_jButton1_actionAdapter implements ActionListener {
  private StateCapital adaptee;
  StateCapital_jButton1_actionAdapter(StateCapital adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}
