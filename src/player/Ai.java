package player;

import java.util.ArrayList;
import solve.Move;
import whoiswho.Colour;
import world.World;

public class Ai{
    private ArrayList<Object> bot;
    private World world;

    public Ai(Colour c, World w){
        this.bot = new ArrayList<>();
        bot.add(c);
        this.world = w;
    }


    public void makeMove(Move m){
    }
    //constructs an ai and adds its attributes accordingly

    public boolean isTurn(){
        boolean flag = false;
        return flag;
    }

}
