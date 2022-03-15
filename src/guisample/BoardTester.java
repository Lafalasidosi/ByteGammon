package guisample;

public class BoardTester
{
   private boolean initDone ;
   private ca.unbc.cpsc101.gui.Board myBoard ;
   private BoardModel myBoardModel ;
   private Buttons26 whiteButtons;
   private Buttons26 blackButtons ;

   public BoardTester()
      {
      setupComponents();
      wireComponents();
      initDone = true ;
      }

   private void setupComponents ()
      {
      myBoard = new ca.unbc.cpsc101.gui.Board() ;
      myBoardModel = new BoardModel(myBoard);

      whiteButtons = new Buttons26((i)->String.format("add W at %2d",i+1)) ;
      blackButtons = new Buttons26((i)->String.format("add B at %2d",i+1)) ;
      whiteButtons.getButton(25).setText("Clear Bar");
      blackButtons.getButton(25).setText("Clear Bar");
      whiteButtons.getButton(24).setText("Add to Bar");
      blackButtons.getButton(24).setText("Add to Bar");
      whiteButtons.setButtonsForeground(java.awt.Color.BLACK);
      blackButtons.setButtonsForeground(java.awt.Color.WHITE);
      blackButtons.setButtonsBackground(java.awt.Color.DARK_GRAY);
      }

   private void wireComponents()
      {
      ButtonWirer buttonWirer = new ButtonWirer(myBoardModel) ;
      whiteButtons.doButtons(buttonWirer::wireWhiteButton);
      blackButtons.doButtons(buttonWirer::wireBlackButton);
      }

   public javax.swing.JComponent getButtonComponent()
      {
      javax.swing.JPanel south = new javax.swing.JPanel() ;
      south.add(blackButtons);
      south.add(whiteButtons);
      return south ;
      }

   public ca.unbc.cpsc101.gui.Board getBoard()
      {
      return myBoard;
      }

   public Buttons26 getWhiteButtons()
      {
      return whiteButtons;
      }

   public Buttons26 getBlackButtons()
      {
      return blackButtons;
      }

   private static class ButtonWirer
   {
      final private BoardModel myModel;

      public ButtonWirer(BoardModel m)
         {
         this.myModel = m ;
         }

      public void wireWhiteButton(Integer i, javax.swing.JButton button)
         {
         if (i==25)
            {
            button.addActionListener(myModel.resetBarAction(ca.unbc.cpsc101.logical.PlayerColor.White));
            }
         else if (i==24)
            {
            button.addActionListener(myModel.incrementBarAction(ca.unbc.cpsc101.logical.PlayerColor.White));
            }
         else
            {
            button.addActionListener(myModel.towardsAction(i, ca.unbc.cpsc101.logical.PlayerColor.White));
            }
         }

      public void wireBlackButton(Integer i, javax.swing.JButton button)
         {
         if (i==25)
            {
            button.addActionListener(myModel.resetBarAction(ca.unbc.cpsc101.logical.PlayerColor.Black));
            }
         else if (i==24)
            {
            button.addActionListener(myModel.incrementBarAction(ca.unbc.cpsc101.logical.PlayerColor.Black));
            }
         else
            {
            button.addActionListener(myModel.towardsAction(i, ca.unbc.cpsc101.logical.PlayerColor.Black));
            }
         }
   }
}
