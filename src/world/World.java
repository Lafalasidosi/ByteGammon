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
import java.util.Scanner;

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

    public void playBackGammon(){
        board = new Board(this);
        System.out.printf("Welcome to ByteGammon! Choose one of the following options: %n");
        System.out.printf("1: %s%n2: %s%n3: %s%n", "Play with another human",
                "Play against a robot", "Watch two robots play");

        Scanner kbd = new Scanner(System.in);
        Game g = new Game(this);
        g.startGame(kbd.nextInt());

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

    public Player[] getPlayers(){
        return players;
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
