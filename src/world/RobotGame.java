package world;

import java.util.ArrayList;
import equipment.Board;
import equipment.Die;
import player.Player;
import solve.*;

public class RobotGame {
    
    World world;

    public RobotGame(World w){
        world = w;
    }


    public void start(){
         // setup
         ArrayList<Move> legalMoves;
         int turn;
         Die[] dice = world.getDice();
         Die d1 = dice[0];
         Die d2 = dice[1];
         Player[] players = world.getPlayers();
         
         do{
             d1.roll();
             world.getDice()[1].roll();
         } while (d1.getValue() == d2.getValue());
         if(d1.getValue() > d1.getValue()){
             // player1's turn first
             turn = 0;
         } else{
             // player2's turn first
             turn = 1;
         }
 
         Board board = world.getBoard(); // create the board here so dice are added
 
         System.out.println("Welcome to the game, here's the board: \n" + board);
 
         do{
             // start the play (start of loop)
             System.out.println("\n\n\nHere's the board at the start of " + players[turn].getColour() + "'s turn: \n" + board);
             legalMoves = Solve.analyze(board, players[turn]);
             if(legalMoves.size() > 0) {
                 System.out.println("Move to be made: \n" + legalMoves.get(0));
                 players[turn].makeMove(legalMoves.get(0)); // just make the first legal move
             }
             else{System.out.println("No legal moves.");}
 
             for(Die d : dice)
                 d.roll();
 
             turn = (turn + 1) % 2; // advance turn to next player
 
         } while(board.bothPlayersHaveCheckersOut()); 
 
             // play (loop) should end when a player declines a double or bears off all pieces
 
         System.out.println("Game Finished");
 
    }
}
