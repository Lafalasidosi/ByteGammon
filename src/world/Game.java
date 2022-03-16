package world;

import equipment.Board;
import equipment.Die;
import player.Player;
import solve.Move;
import solve.Solve;

import java.util.ArrayList;

public class Game {

    World world;

    public Game(World world){
        this.world = world;
    }

    public void startGame(int option){
        switch (option){
            case 1:
            case 2:
            case 3: startRobotGame();
                break;
            default: System.out.println("Heck");
        }
    }

    public void startRobotGame(){

        Die d1, d2;
        d1 = world.getDice()[0];
        d2 = world.getDice()[1];
        Die[] dice = world.getDice();
        Player[] players = world.getPlayers();

        int turn;
        Board board;

        // setup
        ArrayList<Move> legalMoves;
        do{
            d1.roll();
            d2.roll();
        } while (d1.getValue() == d2.getValue());
        if(d1.getValue() > d1.getValue()){
            // player1's turn first
            turn = 0;
        } else{
            // player2's turn first
            turn = 1;
        }

        board = new Board(world); // create the board here so dice are added

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

            turn = (turn + 1) % 2; // advance turn

        } while(board.bothPlayersHaveCheckersOut());

        // play (loop) should end when a player declines a double or bears off all pieces

        System.out.println("Game Finished");

    }

}
