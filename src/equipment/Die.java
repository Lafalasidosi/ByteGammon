package equipment;

import java.util.Random;

public class Die {
    private Random rand;
    private int value = 1;

    public Die(){
        rand = new Random();
    }

    public void roll(){
        value = rand.nextInt(6) + 1;
    }

    public int getValue(){
      return value;
    }

}
