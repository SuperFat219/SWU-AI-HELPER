public class ViewController extends CircleView {
  public ViewController() {
    // Register mouse listener
    addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent e) {
        CircleModel model = getModel(); // Get model

        if (model == null) return;

        if (e.isMetaDown())
          model.setRadius(model.getRadius() - 5); // Right button
        else
          model.setRadius(model.getRadius() + 5); // Left button
      }
    });
  }
}
