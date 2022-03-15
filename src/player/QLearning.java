package player;

import equipment.Board;
import solve.Solve;
import whoiswho.Colour;
import world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QLearning {
    World world = new World();
    Ai abot = new Ai(Colour.BLACK, world);


    private final double alpha = 0.1; //the learning rate
    private final double gamma = 0.9;
    /* discount rate; closer to 0 = more short sighted, faster learning value
    closer to 1 = more far sighted, longer learning value. In this case, I arbitrarily set to 0.9
    */
    private final int reward = 10;
    private final int penalty = -1;
    private final int statecount = 24;
    //provides the number of states equivalent to points on the board

    private int[] possibleActionsFromState(int state) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < statecount; i++) {
            if(Solve.analyze(world.getBoard(), abot));


        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    //method for determining the number of actions the ai can make
    /*
    In this particular case, the results would just be the moves that can be made from each given state.
     */
}
