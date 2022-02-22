package player;

import whoiswho.Colour;

public class Move {

    public Move() {

    }

    public void move(Player p, String move) {

    }

    public static String[] getLegalMoves(String b, Colour c) {
        /**
         * want:
         * look for first legal move
         */
        return new String[] { "sdf", "sag" };
    }

    public String[] moveToArray(String move) {
        String s = "43: 24/21 13/9";
        s = s.replaceAll("[:/]", " ");
        return s.split("\\s+"); // i.e. return {43, 24, 21, 13, 9}
        // maybe 43 (dice values) should be split?
    }
}
