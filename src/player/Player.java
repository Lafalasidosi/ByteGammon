package player;

import solve.Ply;
import whoiswho.Colour;
import equipment.Die;
import equipment.ephemeral.Point;
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
        Point startPoint, endPoint;
        for(Ply p: m.getPlies()) {
            start = p.getStart() - 1;
            end = p.getEnd() - 1;
            startPoint = world.getBoard().getPoint(start, this.colour);
            endPoint = world.getBoard().getPoint(end, this.colour);
            if(endPoint.isBlot(this.colour)){ // if end point of ply is a blot, hit opponent
                world.getBoard().getBearOffZone().placeChecker(endPoint.pickUpChecker());
            }
            endPoint.placeChecker(startPoint.pickUpChecker());
        }
    }

    public void rollDice(){
        for(Die d : world.getDice()){
            d.roll();
        }
    }

    public Colour getColour(){
        return colour;
    }
}
