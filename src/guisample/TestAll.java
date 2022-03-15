package guisample;

public class TestAll extends javax.swing.JFrame
{
   private javax.swing.JComponent diceTester ;
   private javax.swing.JComponent cubeTester ;
   private BoardTester boardTester ;

   private ca.unbc.cpsc101.gui.Board myBoard ;
   private Buttons26 whiteButtons;
   private Buttons26 blackButtons ;

   private void setupComponents ()
      {
      diceTester = new TestDice();
      cubeTester = new TestDoublingCube() ;
      boardTester = new BoardTester();

      myBoard = boardTester.getBoard();
      blackButtons = boardTester.getBlackButtons() ;
      whiteButtons = boardTester.getWhiteButtons() ;
      }

   private javax.swing.JComponent southComponent()
      {
      javax.swing.JPanel south = new javax.swing.JPanel() ;
      south.add(blackButtons);
      south.add(whiteButtons);
      return south ;
      }

   private javax.swing.JComponent eastComponent()
      {
      javax.swing.JPanel east = new javax.swing.JPanel();
      east.setLayout(new javax.swing.BoxLayout(east, javax.swing.BoxLayout.Y_AXIS));
      east.add(cubeTester);
      east.add(diceTester);
      east.add(javax.swing.Box.createVerticalGlue());
      return east ;
      }

   private void setupFrame()
      {
      add(myBoard, java.awt.BorderLayout.CENTER);
      add(southComponent(), java.awt.BorderLayout.SOUTH);
      add(eastComponent(), java.awt.BorderLayout.EAST);
      setSize(1200,800);
      setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      }

   TestAll()
      {
      super("Test Lots of stuff");
      }

   private static void go()
      {
      TestAll t = new TestAll();
      t.setupComponents();
      t.setupFrame();
      }

   public static void main(String [] args)
      {
      javax.swing.SwingUtilities.invokeLater(TestAll::go);
      }

  }
