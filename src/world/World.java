package world;

import equipment.Board;
import equipment.Die;
import player.Player;
import whoiswho.Colour;

public class World {
    private Player player1;
    private Player player2;
    private Board board;
    private Die d1;
    private Die d2;
    private static Die[] dice;
    private Player[] players;

    public World() {
        //board = new Board();
        player1 = new Player(Colour.RED);
        player2 = new Player(Colour.BLACK);
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
        do{
            d1.roll();
            d2.roll();
        } while (d1.getValue() == d2.getValue());
        if(d1.getValue() > d1.getValue()){
            // player1's turn first
        } else{
            // player2's turn first
        }

        board = new Board(); // create the board here so dice are added



    }

    public static Die[] getDice() {
        return dice;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int n) {
        // no reason for n-1, was just thinking of Player 1/Player 2 etc
        return players[n - 1];
    }

}
