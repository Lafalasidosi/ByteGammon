package player;

import equipment.Board;
import solve.Solve;
import whoiswho.Colour;
import equipment.Checker;
import solve.Move;
import world.World;

import java.util.ArrayList;

public class Player extends Ai{
    private Colour colour;
    private ArrayList<Object> player;
    private World world;


    public Player(Colour colour, World world){
        super(colour, world);
        this.player = new ArrayList<>();
        player.add(colour);
        this.world = world;
    }
    //constructs the player and assigns its attributes

    public Colour getColour(){
        return colour;
    }
    //returns the colour of a particular player

    public void makeMove(Move m){

    }

    public void isTurn(){

    }

    /*
    Passes a move to a makemove method. If the move is contained within the list of valid moves then
    the passed move will be made.
     */
}
