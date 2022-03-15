package player;

import whoiswho.Colour;
import equipment.Checker;
import world.World;

public class Player {
    private Colour colour;
    private World world;
    private boolean hasdoublingcube;
    private boolean iswinner;



    public Player(Colour colour, World world){
        this.colour = colour;
        this.world = world;
        this.hasdoublingcube = true;
        this.iswinner = false;
    }

    public Colour getColour(){
        return colour;
    }

    public boolean getHasdoublingcube() {
        return hasdoublingcube;
    }

    public void setHasdoublingcube(boolean hasdoublingcube) {
        this.hasdoublingcube = hasdoublingcube;
    }

    public boolean isIswinner() {
        return iswinner;
    }

    public void setIswinner(boolean iswinner) {
        this.iswinner = iswinner;
    }
}
