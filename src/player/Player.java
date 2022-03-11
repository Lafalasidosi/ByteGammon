package player;

import whoiswho.Colour;
import equipment.Checker;
import solve.Move;
import java.util.ArrayList;

public class Player extends player.Move {
    private Colour colour;
    private ArrayList<Object> player;

    public Player(Colour colour){
        this.player = new ArrayList<>();
        player.add(colour);
    }

    public Colour getColour(){
        return colour;
    }

    public void makeMove(Move move){
        if(getLegalMoves(move));
    }
    /*
    Passes a move to a makemove method. If the move is contained within the list of valid moves then
    the passed move will be made.
     */
}
