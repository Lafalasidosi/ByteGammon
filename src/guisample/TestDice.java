package guisample;

public class TestDice extends javax.swing.JPanel
{
   private static java.util.Random numGen = null ;


   private static java.util.Random getRandom()
      {
      if (null==numGen)
         {
         long seed = System.nanoTime();
         System.err.println("Random Seed is "+seed+".");
         numGen = new java.util.Random(seed);
         }
      return numGen;
      }
   private static int getRandomDieValue()
      {
      return getRandom().nextInt(6) + 1 ;
      }

   private ca.unbc.cpsc101.gui.Dice myDice ;
   private javax.swing.JButton rollDice ;


   private void setupComponents ()
      {
      myDice = new ca.unbc.cpsc101.gui.Dice();
      rollDice = new javax.swing.JButton("Roll") ;
      }

   private void wireComponents()
      {
      rollDice.addActionListener((ae)->myDice.setValues(getRandomDieValue(),getRandomDieValue()));
      }

   public TestDice()
      {
      super() ;
      setupComponents();
      wireComponents();
      this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
      add(myDice);
      add(rollDice);
      }

   public ca.unbc.cpsc101.gui.Dice getDice() { return myDice;}
   public javax.swing.JButton getRollButton() { return rollDice;}
}
