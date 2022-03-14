package player;

import whoiswho.Colour;
import equipment.Checker;
import world.World;

public class Player {
    private Colour colour;
    private World world;

    public Player(Colour colour, World world){
        this.colour = colour;
        this.world = world;
    }

    public Colour getColour(){
        return colour;
    }
}
