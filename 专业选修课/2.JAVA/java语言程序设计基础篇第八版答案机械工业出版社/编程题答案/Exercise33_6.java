import javax.swing.*;

import java.awt.*;

public class Exercise33_6 extends javax.swing.JApplet {
  /** Creates new form Exercise33_6 */
  public Exercise33_6() {
    buttonGroup1 = new javax.swing.ButtonGroup();
    jPanel1 = new javax.swing.JPanel();
    jrbTop = new javax.swing.JRadioButton();
    jrbLeft = new javax.swing.JRadioButton();
    jrbRight = new javax.swing.JRadioButton();
    jrbBottom = new javax.swing.JRadioButton();
    jtpFigures = new javax.swing.JTabbedPane();
    figurePanel1 = new FigurePanel();
    figurePanel2 = new FigurePanel();
    figurePanel3 = new FigurePanel();
    figurePanel4 = new FigurePanel();

    jPanel1.setBorder(new javax.swing.border.TitledBorder("Specify Tab Location"));
    jrbTop.setText("Top");
    buttonGroup1.add(jrbTop);
    jrbTop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jrbTopActionPerformed(evt);
      }
    });

    jPanel1.add(jrbTop);

    jrbLeft.setText("Left");
    buttonGroup1.add(jrbLeft);
    jrbLeft.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jrbLeftActionPerformed(evt);
      }
    });

    jPanel1.add(jrbLeft);

    jrbRight.setText("Right");
    buttonGroup1.add(jrbRight);
    jrbRight.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jrbRightActionPerformed(evt);
      }
    });

    jPanel1.add(jrbRight);

    jrbBottom.setText("Bottom");
    buttonGroup1.add(jrbBottom);
    jrbBottom.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jrbBottomActionPerformed(evt);
      }
    });

    jPanel1.add(jrbBottom);

    add(jPanel1, java.awt.BorderLayout.SOUTH);

    jtpFigures.addTab("Square", figurePanel1);

    figurePanel2.setType(2);
    jtpFigures.addTab("Rectangle", figurePanel2);

    figurePanel3.setType(3);
    jtpFigures.addTab("Circle", figurePanel3);

    figurePanel4.setType(4);
    jtpFigures.addTab("Oval", figurePanel4);

    add(jtpFigures, java.awt.BorderLayout.CENTER);
  }

  private void jrbBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbBottomActionPerformed
    jtpFigures.setTabPlacement(JTabbedPane.BOTTOM);
  }//GEN-LAST:event_jrbBottomActionPerformed

  private void jrbRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbRightActionPerformed
    jtpFigures.setTabPlacement(JTabbedPane.RIGHT);
  }//GEN-LAST:event_jrbRightActionPerformed

  private void jrbLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbLeftActionPerformed
    jtpFigures.setTabPlacement(JTabbedPane.LEFT);
  }//GEN-LAST:event_jrbLeftActionPerformed

  private void jrbTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTopActionPerformed
    jtpFigures.setTabPlacement(JTabbedPane.TOP);
  }//GEN-LAST:event_jrbTopActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private FigurePanel figurePanel4;
  private FigurePanel figurePanel3;
  private FigurePanel figurePanel2;
  private FigurePanel figurePanel1;
  private javax.swing.JRadioButton jrbBottom;
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.JRadioButton jrbRight;
  private javax.swing.JRadioButton jrbTop;
  private javax.swing.JTabbedPane jtpFigures;
  private javax.swing.JRadioButton jrbLeft;
  // End of variables declaration//GEN-END:variables

  public static void main(String[] args) {
    Exercise33_6 applet = new Exercise33_6();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setTitle("Exercise33_6");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}