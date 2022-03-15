package guisample;

import ca.unbc.cpsc101.logical.PlayerColor;

import static ca.unbc.cpsc101.logical.PlayerColor.Black;

public class BoardModel
{
   final private ca.unbc.cpsc101.gui.Board b;
   final private int[] counts;
   final private PlayerColor[] colours;

   private int blackBarCount;
   private int whiteBarCount;

   private void syncGUI()
      {
      b.setBarWhiteCount(whiteBarCount);
      b.setBarBlackCount(blackBarCount);
      for (int i=1;i<=24;++i)
         {
         b.getPoint(i).setCountAndColour(counts[i-1], colours[i-1]);
         }
      }

   public BoardModel(ca.unbc.cpsc101.gui.Board b)
      {
      this.b = b;
      this.counts = new int[24];
      java.util.Arrays.fill(counts,0);
      this.colours = new ca.unbc.cpsc101.logical.PlayerColor[24];
      java.util.Arrays.fill(colours, Black);
      syncGUI() ;
      }

   public void setCountAndColour(int i, int c, PlayerColor pc)
      {
      counts[i] = c;
      colours[i] = pc;
      }

   public int getCount(int i)
      {
      return counts[i];
      }

   public PlayerColor getColour(int i)
      {
      return colours[i];
      }

   public void towards(int i,PlayerColor pc)
      {
      int oldCount = counts[i] ;
      PlayerColor oldPC = colours[i];
      if (oldCount==0) oldPC=pc ;
      int newCount = (pc.equals(oldPC)) ? oldCount+1 : 0 ;
      setCountAndColour(i,newCount,pc);
      }

   public java.awt.event.ActionListener towardsAction(int i,PlayerColor pc)
      {
      return (ae)->
              {
              towards(i,pc) ;
              b.getPoint(i+1).setCountAndColour(getCount(i), pc);
              };
      }

   public java.awt.event.ActionListener resetBarAction(PlayerColor pc)
      {
      return pc.equals(Black) ? blackResetBarAction() : whiteResetBarAction();
      }

   public java.awt.event.ActionListener blackResetBarAction()
      {
      final java.util.function.IntConsumer barSetter = b::setBarBlackCount ;
      return (ae)->
              {
              blackBarCount=0 ;
              barSetter.accept(0) ;
              };
      }

   public java.awt.event.ActionListener whiteResetBarAction()
      {
      final java.util.function.IntConsumer barSetter = b::setBarWhiteCount ;
      return (ae)->
              {
              whiteBarCount = 0 ;
              barSetter.accept(0);
              };
      }

   public java.awt.event.ActionListener incrementBarAction(PlayerColor pc)
      {
      return pc.equals(Black) ? blackIncrementBarAction() : whiteIncrementBarAction() ;
      }

   public java.awt.event.ActionListener blackIncrementBarAction()
      {
      final java.util.function.IntConsumer barSetter = b::setBarBlackCount ;
      return (ae)->
              {
              ++blackBarCount ;
              barSetter.accept(blackBarCount) ;
              };
      }

   public java.awt.event.ActionListener whiteIncrementBarAction()
      {
      final java.util.function.IntConsumer barSetter = b::setBarWhiteCount ;
      return (ae)->
              {
              ++whiteBarCount;
              barSetter.accept(whiteBarCount);
              };
      }
}