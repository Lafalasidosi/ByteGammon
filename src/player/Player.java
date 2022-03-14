package player;

import whoiswho.Colour;
import equipment.Checker;
import world.World;
import solve.Move;

public class Player {
    private Colour colour;
    private World world;

    public Player(Colour colour, World world){
        this.colour = colour;
        this.world = world;
    }

    public void makeMove(Move m){
        int start, end;
        start = m.getPly1().getStartPoint()-1;
        end = m.getPly1().getEndPoint()-1;
        world.getBoard().getPoint(end, this.colour).placeChecker(world.getBoard().getPoint(start, this.colour).pickUpChecker());
    }
    public Colour getColour(){
        return colour;
    }
}
