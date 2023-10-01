public class TestSwingModel1 {
  public static void main(String[] args) {
    javax.swing.JButton jbt = new javax.swing.JButton();

    // Obtain the default model from the component
    javax.swing.ButtonModel model = jbt.getModel();

    // Set properties in the model
    model.setActionCommand("OK");
    model.setMnemonic('O');

    // Display the property values from the component
    System.out.println("actionCommand is " + jbt.getActionCommand());
    System.out.println("mnemonic is " + (char)(jbt.getMnemonic()));
  }
}
