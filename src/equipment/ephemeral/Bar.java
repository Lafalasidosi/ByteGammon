package equipment.ephemeral;

import equipment.Checker;
import whoiswho.Colour;
import java.util.ArrayList;

public class Bar extends Point{

  private int number;
  private ArrayList<Checker> checkers; 

  public Bar(int n){
    super(n);
    number = n;
    checkers = new ArrayList<Checker>(0);
  }

  public int getCheckersOfColour(Colour c){
    int count = 0;
    for(Checker checker : checkers){
      if (checker.getColour() == c)
        count++;
    }
    return c == Colour.RED ? count : -1 * count;
  }



}
