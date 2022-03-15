package player;

import java.util.ArrayList;

import equipment.ephemeral.Point;
import solve.Move;
import solve.Ply;
import solve.Solve;
import whoiswho.Colour;
import world.World;

import java.util.Collections;
import java.util.Collections.*;

public class Ai extends Player {
    private ArrayList<Object> bot;
    private World world;
    private Colour colour;
    private Move randMove;

    public Ai(Colour c, World w) {
        super(c, w);
        this.bot = new ArrayList<>();
        bot.add(c);
        this.world = w;
        Collections.shuffle(Solve.analyze(world.getBoard(), this));
        randMove = Solve.analyze(world.getBoard(), this).get(0);
    }
    //constructs an ai and adds its attributes accordingly


    @Override
    public Colour getColour() {
        return this.colour;
    }

    public void reShuffle() {
        Collections.shuffle(Solve.analyze(world.getBoard(), this));
        randMove = Solve.analyze(world.getBoard(), this).get(0);
    }
    //reshuffles and picks a new random move form the list of moves



    @Override
    public void makeMove(Move randMove) {
        int start, end;
        Point startPoint, endPoint;
        for (Ply p : randMove.getPlies()) {
            start = p.getStart() - 1;
            end = p.getEnd() - 1;
            startPoint = world.getBoard().getPoint(start, this.colour);
            endPoint = world.getBoard().getPoint(end, this.colour);
            Collections.shuffle(randMove.getPlies());//shuffles the arraylist of plies
            this.reShuffle();
            if(endPoint.isBlot(this.colour)){
                world.getBoard().getBearOffZone().placeChecker(endPoint.pickUpChecker());
            }
            endPoint.placeChecker(startPoint.pickUpChecker());
        }
    }

    /**
     * Works very similarly to the player's makemove method
     * just picks a random move from the list of available moves
     * @return void
     */

    public boolean isTurn () {
        boolean flag = false;
        return flag;
    }
}


