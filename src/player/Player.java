package player;

import equipment.Colour;

public class Player {
    private Colour colour;

    public Player(Colour colour){
        this.colour = colour;
    }

    public Colour getColour(){
        return colour;
    }
}
