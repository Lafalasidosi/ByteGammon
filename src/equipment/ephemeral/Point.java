package equipment.ephemeral;

import equipment.Checker;
import whoiswho.Colour;
import java.util.ArrayList;

public class Point{

    private int number;
    private ArrayList<Checker> checkers;

    public Point(int n){
        number = n;
        checkers = new ArrayList<Checker>(0);
    }

    public void placeChecker(Checker c){
        if (size() == 0 || this.getLastChecker().getColour() == c.getColour())
            checkers.add(c);
    }
    
    public Checker pickUpChecker(){
      Checker c = getLastChecker();
      checkers.remove(c);
      return c;
    }

    public int size(){
        if (checkers.size() == 0)
          return 0;
        Colour c = checkers.get(0).getColour();
        return c == Colour.RED ? checkers.size() : -1 * checkers.size();
    }

    public Checker getLastChecker(){
        return checkers.get(checkers.size() - 1);
    }

    /* 
     * * Returns Point number from a given player's perspective.
     * * e.g. Red's 5-point is Black's 19-point.
     * *
     * * @return    return the number of a point based on player.
     * */
    public int getPointPerPlayer(Colour c){
        return c == Colour.RED ? number : 23 - number;
    }

    public boolean isBlot(Colour c){
        return c == Colour.RED ? (size() == -1) : (size() == 1);
    }
}
