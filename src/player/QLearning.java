package player;

import whoiswho.Colour;
import world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QLearning {
    World w = new World(Config.TRUE);
    Ai theBot = new Ai(Colour.BLACK, w);
    int boardSize;
    private final double alpha = 0.1; //the learning rate
    private final double gamma =  0.9;
    /* "eagerness"; closer to 0 = more short sighted, faster learning value
    closer to 1 = more far sighted, longer learning value
    */
    private final int reward = 10;
    private final int penalty = -1;
    private final int statecount;

}
