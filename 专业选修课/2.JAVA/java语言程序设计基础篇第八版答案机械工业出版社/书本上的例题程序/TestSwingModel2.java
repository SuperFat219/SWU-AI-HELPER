public class TestSwingModel2 {
  public static void main(String[] args) {
    javax.swing.JButton jbt = new javax.swing.JButton();

    // Create a new button model
    javax.swing.ButtonModel model =
      new javax.swing.DefaultButtonModel();

    // Set properties in the model
    model.setActionCommand("OK");
    model.setMnemonic('O');

    // Assign the model to the button
    jbt.setModel(model);

    // Display the property values from the component
    System.out.println("actionCommand is " + jbt.getActionCommand());
    System.out.println("mnemonic is " + jbt.getMnemonic());
  }
}
