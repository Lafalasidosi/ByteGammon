package player;

import equipment.Board;
import solve.Solve;
import whoiswho.Colour;
import equipment.Checker;
import solve.Move;
import world.World;

import java.util.ArrayList;

public class Player extends Ai{

    private ArrayList<Object> player;
    private World world;
    private Colour colour;



    public Player(Colour colour, World world){
        super(colour, world);
        this.player = new ArrayList<>();
        player.add(colour);
        this.world = world;
    }
    //constructs the player and assigns its attributes

    public Colour getColour(){
        return this.colour;
    }
    //returns the colour of a particular player

@Override
    public void makeMove(Move m){
    }

@Override
    public boolean isTurn(){
        boolean flag = false;
        return flag;
    }

    /*
    Passes a move to a makemove method. If the move is contained within the list of valid moves then
    the passed move will be made.
     */
}
