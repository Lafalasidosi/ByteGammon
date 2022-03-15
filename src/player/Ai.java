package player;

import java.util.ArrayList;

import equipment.ephemeral.Point;
import solve.Move;
import solve.Ply;
import whoiswho.Colour;
import world.World;

public class Ai extends Player {
    private ArrayList<Object> bot;
    private World world;
    private Colour colour;

    public Ai(Colour c, World w) {
        super(c, w);
        this.bot = new ArrayList<>();
        bot.add(c);
        this.world = w;
    }
    //constructs an ai and adds its attributes accordingly


    @Override
    public Colour getColour() {
        return this.colour;
    }

    @Override
    public void makeMove(Move m) {
        int start, end;
        Point startPoint, endPoint;
        for (Ply p : m.getPlies()) {
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

        public boolean isTurn () {
            boolean flag = false;
            return flag;
        }

    }


