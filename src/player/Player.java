package player;

import solve.Ply;
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
        for(Ply p: m.getPlies()) {
            start = p.getStartPoint() - 1;
            end = p.getEndPoint() - 1;
            world.getBoard().getPoint(end, this.colour).placeChecker(world.getBoard().getPoint(start, this.colour).pickUpChecker());
        }
    }
    public Colour getColour(){
        return colour;
    }
}
