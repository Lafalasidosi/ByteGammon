package guisample;

public class TestButtons26 extends javax.swing.JFrame
{
   private Buttons26 myButtons ;

   TestButtons26()
      {
      super("Test 26 Button Panel") ;
      setSize(500,500);
      setupButtons() ;
      setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      }

   private void setupButtons()
      {
      myButtons = new Buttons26((i) -> String.format("Button %2d", i +1));
      myButtons.setButtonsForeground(java.awt.Color.RED);
//      myButtons.setButtonsBackground(new java.awt.Color(0,0,96));
      myButtons.setButtonsBackground(java.awt.Color.blue);
      add(myButtons);
      }

   public static void main(String [] args)
      {
      javax.swing.SwingUtilities.invokeLater(TestButtons26::new);
      }
}
