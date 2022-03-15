package guisample;

/**
 * Buttons26 is a panel of 26 buttons
 * laid out as 6×4 + 1×2.
 *
 * It has a few utility methods for doing something to all of the
 * buttons uniformly by applying some form of Button Consumer.
 *
 * The actual buttons are from a subclass of JButton that has a
 * flaky ability to set the background colour.  This needs more
 * work.
 */

public class Buttons26 extends javax.swing.JPanel
{
   private javax.swing.JButton [] buttons ;

   public javax.swing.JButton getButton(int i)
      {
      return buttons[i];
      }
   public void setButton(int i, javax.swing.JButton b)
      {
      buttons[i] = b;
      }

   public void doButtons(java.util.function.Consumer<javax.swing.JButton> c)
      {
      for (javax.swing.JButton b:buttons)
         c.accept(b);
      }
   public void doButtons(java.util.function.BiConsumer<Integer,javax.swing.JButton> c)
      {
      for (int i=0; i<buttons.length;++i)
         c.accept(i,buttons[i]);
      }
   public void setButtonsForeground(java.awt.Color c)
      {
      doButtons((b) -> b.setForeground(c));
      }
   public void setButtonsBackground(java.awt.Color c)
      {
      doButtons((b) -> b.setBackground(c));
      }
   private static int transpose46(int q)
      {
      int r = q / 4 ;
      int c = q - 4 * r ;
      return r + 6*c ;
      }
   public void setButtons(javax.swing.JPanel container, java.util.function.IntFunction<String> labeller)
      {
      buttons = new javax.swing.JButton[26 ] ;
      for (int i=0; i<24;++i)
         {
            setButton(i, new XButton(labeller.apply(i)));
         }
      for (int i=0; i<24;++i)
         {
         container.add(getButton(transpose46(i)));
         }
      setButton(24, new XButton(labeller.apply(24)));
      setButton(25, new XButton(labeller.apply(25)));
      }
   public Buttons26(java.util.function.IntFunction<String> labeller)
      {
      super();
      javax.swing.JPanel topPanel = new javax.swing.JPanel(new java.awt.GridLayout(6,4)) ;
      javax.swing.JPanel bottomPanel = new javax.swing.JPanel(new java.awt.GridLayout(1,2)) ;
      this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
      setButtons(topPanel,labeller);
      this.add(topPanel) ;
      bottomPanel.add(this.getButton(24));
      bottomPanel.add(this.getButton(25));
      this.add(bottomPanel) ;
      }
}

class XButton extends javax.swing.JButton
{
   XButton() { super(); }
   XButton(String s) { super(s); setOpaque(true);}

   @Override
   protected void paintComponent(java.awt.Graphics g)
      {
      java.awt.Graphics2D g2 = (java.awt.Graphics2D) g ;
      g2.setColor(getBackground());
      g2.fillRoundRect(3,3, getWidth()-6, getHeight()-6,3,3);
      g2.setColor(getForeground());
      g2.setFont(new java.awt.Font("Helvetica", java.awt.Font.PLAIN, 16));
      drawString(g2,getText());
      // super.paintComponent(g);
      }

   private void drawString(java.awt.Graphics2D g2, String s)
      {
      final java.awt.FontMetrics fm = g2.getFontMetrics(getFont()) ;
      final float xLoc = (getWidth()-fm.stringWidth(s))/2.0f ;
      final float yLoc = (getHeight() + fm.getHeight()) /2.0f ;
      g2.drawString(s, xLoc, yLoc);
      }
}
