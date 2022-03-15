package world;

import equipment.Board;
import equipment.Die;
import player.Player;
import solve.*;
import whoiswho.Colour;
import solve.Move;

import java.util.ArrayList;

public class World {
    private Player player1;
    private Player player2;
    private Board board;
    private Die d1;
    private Die d2;
    private static Die[] dice;
    private Player[] players;
    private int turn;

    public World() {
        //board = new Board();
        player1 = new Player(Colour.RED, this);
        player2 = new Player(Colour.BLACK, this);
        d1 = new Die(); // "p1's" die
        d2 = new Die(); // "p2's" die
        dice = new Die[2];
        dice[0] = d1;
        dice[1] = d2;
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
    }

    public void startGame(){
        // setup
        do{
            d1.setValue(4);
            d2.setValue(6);
        } while (d1.getValue() == d2.getValue());
        if(d1.getValue() > d1.getValue()){
            // player1's turn first
            turn = 0;
        } else{
            // player2's turn first
            turn = 1;
        }

        board = new Board(this); // create the board here so dice are added

        System.out.println("Welcome to the game, here's the board: \n" + board);


        // start the play (start of loop)

        ArrayList<Move> firstLegalMoves = Solve.analyze(board, players[turn]);

        players[turn].makeMove(firstLegalMoves.get(0)); // just make the first legal move


        System.out.println("Here's the new board after one move: \n" + board);


        // play (loop) should end when a player declines a double or bears off all pieces
    }

    public Die[] getDice() {
        return dice;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int n) {
        // no reason for n-1, was just thinking of Player 1/Player 2 etc
        return players[n - 1];
    }

    public void nextTurn(){
        turn = (turn + 1) % 2;
    }

}
