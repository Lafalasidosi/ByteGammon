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

    public void placeChecker(Checker checker){
        checkers.add(checker);
    }
    
    public Checker pickUpChecker(){
      Checker c = getLastChecker();
      checkers.remove(c);
      return c;
    }

    public int size(){
        return checkers.size();
    }

    public Checker getLastChecker(){
        return checkers.get(size() - 1);
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
}
