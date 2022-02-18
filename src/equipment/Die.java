package equipment;

import java.util.Random;

public class Die {
    private Random rand;

    public Die(){
        rand = new Random();
    }

    public int roll(){
        return rand.nextInt(6) + 1;
    }

}
