package guisample;

import ca.unbc.cpsc101.gui.DoublingCube;

import javax.swing.*;

public class TestDoublingCube extends javax.swing.JPanel
{
   private DoublingCube slot ;
   private JButton blackDoubles ;
   private JButton whiteDoubles ;
   private JButton resetCube ;

   private void setupComponents ()
      {
      slot = new DoublingCube() ;

      blackDoubles = new JButton("Black Doubles") ;
      whiteDoubles = new JButton("White Doubles") ;
      resetCube = new JButton("reset Cube") ;
      }

   public ca.unbc.cpsc101.gui.DoublingCube getCube()
      {
      return slot;
      }

   public JButton getBlackDoublesButton()
      {
      return blackDoubles;
      }

   public JButton getWhiteDoublesButton()
      {
      return whiteDoubles;
      }

   public JButton getResetButton()
      {
      return resetCube;
      }

   private void wireComponents()
      {
      blackDoubles.addActionListener((ae)->slot.blackDoubles());
      whiteDoubles.addActionListener((ae) -> slot.whiteDoubles());
      resetCube.addActionListener((ae)->slot.reset());
      }

   public TestDoublingCube()
      {
      super() ;
      javax.swing.JPanel dCubeButtons = new javax.swing.JPanel();
      this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
      dCubeButtons.setLayout(new javax.swing.BoxLayout(dCubeButtons, javax.swing.BoxLayout.Y_AXIS));
      setupComponents();
      wireComponents();
      this.add(slot);
      dCubeButtons.add(resetCube);
      dCubeButtons.add(whiteDoubles);
      dCubeButtons.add(blackDoubles);
      this.add(dCubeButtons) ;
      }


}
