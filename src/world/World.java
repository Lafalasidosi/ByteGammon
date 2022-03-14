package world;

import equipment.Board;
import equipment.Die;
import player.Ai;
import player.Config;
import player.Player;
import whoiswho.Colour;

public class World {
    private Player p1;
    private Player p2;
    private Board board;
    private Die d1;
    private Die d2;
    private static Die[] dice;
    private Player[] players;

    /**
     * This is the World constructor
     */
    public World() {
        board = new Board();
        p1 = new Player(Colour.RED, this);
        p2 = new Player(Colour.BLACK, this);
        d1 = new Die();
        d2 = new Die();
        dice = new Die[2];
        dice[0] = d1;
        dice[1] = d2;
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
    }
    public World(Config c){
        board = new Board();
        p1 = new Player(Colour.RED, this);
        Ai bot = new Ai(Colour.BLACK, this);
        d1 = new Die();
        d2 = new Die();
        dice = new Die[2];
        dice[0] = d1;
        dice[1] = d2;
        players = new Player[2];
        players[0] = p1;
        players[1] = p2;
    }
    //constructs an ai if & only if the player chooses to play vs ai

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
