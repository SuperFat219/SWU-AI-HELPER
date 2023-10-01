import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise34_6 {
  public static void main(String[] args) {
    new UserInfoDialog().setVisible(true);
  }
}

class UserInfoDialog extends JDialog {
  private JTextField jtfUsername = new JTextField(20);
  private JPasswordField jtfPassword = new JPasswordField(20);
  private JButton jbtOK = new JButton("OK");
  private JButton jbtCancel = new JButton("Cancel");
  private Login login = new Login();
  class Login {
    String username;
    String password;
  }

  public UserInfoDialog() {
    this(null, true);
  }

  public UserInfoDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    setTitle("Login");

    // Group two labels
    JPanel jpLabels = new JPanel(new GridLayout(2, 1));
    jpLabels.add(new JLabel("Username"));
    jpLabels.add(new JLabel("Password"));

    // Group two text fields
    JPanel jpTextFields = new JPanel(new GridLayout(2, 1));
    jpTextFields.add(jtfUsername);
    jpTextFields.add(jtfPassword);

    // Group jpLabels and jpTextFields
    JPanel panel = new JPanel(new BorderLayout(5, 2));
    panel.add(jpLabels, BorderLayout.WEST);
    panel.add(jpTextFields, BorderLayout.CENTER);

    JPanel jpButtons = new JPanel();
    jpButtons.add(jbtOK);
    jpButtons.add(jbtCancel);

    add(jpButtons, BorderLayout.SOUTH);
    add(panel, BorderLayout.CENTER);

    jbtOK.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        login.username = jtfUsername.getText().trim();
        login.password = new String(jtfPassword.getPassword());
        setVisible(false);
      }
    });

    jbtCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        login = null;
        setVisible(false);
      }
    });
  }
}
