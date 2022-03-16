package world;

import equipment.Board;
import equipment.Die;
import equipment.DoublingCube;
import player.Ai;
import player.Config;
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
    private DoublingCube doublingCube;
    private Ai abot;

    public World() {
        //board = new Board();
        player1 = new Player(Colour.RED, this);
        player2 = new Player(Colour.BLACK, this);
        d1 = new Die(); // "p1's" die
        d2 = new Die(); // "p2's" die
        doublingCube = new DoublingCube();
        dice = new Die[2];
        dice[0] = d1;
        dice[1] = d2;
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;
    }

    public World(Config c){
        player1 = new Player(Colour.RED, this);
        abot = new Ai(Colour.BLACK, this);
        d1 = new Die(); // "p1's" die
        d2 = new Die(); // the bot's die
        doublingCube = new DoublingCube();
        dice = new Die[2];
        dice[0] = d1;
        dice[1] = d2;
        players = new Player[2];
        players[0] = player1;
        players[1] = abot;
    }

    public void startGame(){
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

        board = new Board(this); // create the board here so dice are added

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

            nextTurn();

        } while(board.bothPlayersHaveCheckersOut()); 

            // play (loop) should end when a player declines a double or bears off all pieces

        System.out.println("Game Finished");

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

    public DoublingCube getdoublingcube(){
        return this.doublingCube;

    }

    public void offerdoublingcube(Player player, boolean accept){

        if(player.getHasdoublingcube()&&accept){
            doublingCube.doubling();
            player.setHasdoublingcube(false);
        }else {
            if (!player.getHasdoublingcube()) {
                System.out.println("cannot double");
            }
            if (!accept) {
                player.setIswinner(true);
            }
        }
    }

}
