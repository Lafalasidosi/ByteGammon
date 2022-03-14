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
    
    @Override
    public void makeMove(){
    }
    //constructs an ai and adds its attributes accordingly

    public void isTurn(){

    }

}
