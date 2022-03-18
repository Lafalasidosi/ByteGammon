package world;

import java.util.ArrayList;
import java.util.Scanner;

import equipment.Board;
import equipment.Die;
import player.Player;
import solve.*;

public class OneHumanGame {

    World world;

    public OneHumanGame(World w){
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
        Scanner kbd = new Scanner(System.in);
        int choice = 0;
        
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
        
        /*
        If human's turn, play one way
        otherwise let robot make first available move
        */ 

        do{
            // start the play (start of loop)
            System.out.println("\n\n\nHere's the board at the start of " + players[turn].getColour() + "'s turn: \n" + board);
            legalMoves = Solve.analyze(board, players[turn]);
            if(legalMoves.size() > 0) {

                if(turn == 0){
                    System.out.printf("Your roll: %d %d%n", d1.getValue(), d2.getValue());
                    System.out.printf("Your available moves. Please make a choice: %n");
                    printmoves(legalMoves);
                    do{
                        try{
                        choice = kbd.nextInt();
                        } catch (Exception e){
                            System.err.println("Please make a valid choice.");
                        }
                    } while (choice < 1 || choice > legalMoves.size());

                    players[turn].makeMove(legalMoves.get(choice));

                }
                else{
                    System.out.println("Move to be made: \n" + legalMoves.get(0));
                    players[turn].makeMove(legalMoves.get(0)); // just make the first legal move
                }
            }
            else{System.out.println("No legal moves.");}

            for(Die d : dice)
                d.roll();

            turn = (turn + 1) % 2; // advance turn to next player

        } while(board.bothPlayersHaveCheckersOut()); 

            // play (loop) should end when a player declines a double or bears off all pieces

        System.out.println("Game Finished");

   }

    private void printmoves(ArrayList<Move> legalMoves) {
        int l = legalMoves.size();
        for(int i = 0; i < l; i++){
            System.out.printf("%d: %s ", i+1, legalMoves.get(i));
            if( (i+1) % 8 == 0 )
                System.out.println();
        }
    }
    
}
