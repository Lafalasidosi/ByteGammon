package equipment;

import java.util.ArrayList;
import equipment.Checker;
import equipment.ephemeral.*;
import equipment.world.World;

public class Board {

    Point[] points;
    Bar bar;
    ArrayList<Checker> bearOffSection = new ArrayList<Checker>(0);

    public Board() {
        points = new Point[24];
        bar = new Bar(24);
        for (int i = 0; i < 24; i++) {
            points[i] = new Point(i);
        }
        fillStartingPosition(points);
    }

    /**
     * Place checkers in their starting positions. 
     * 
     * @param points
     */
    private void fillStartingPosition(Point[] points) {
        int[] initialPositions = { 5, 7, 12, 23 };
        int[] numberOfCheckers = { 5, 3, 5, 2 };
        for (Colour colour : Colour.values()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < numberOfCheckers[i]; j++) {
                    // might be convoluted, but it works
                    points[points[initialPositions[i]].getPointPerPlayer(colour)].placeChecker(new Checker(colour));
                }
            }
        }
    }

    /**
     * The state of the board is described by the placement of the checkers and the
     * current value shown on the dice. For example, at the beginning of a game the
     * checkers are placed on points per the fillStartingPosition method, no pieces
     * on the bar (first two numbers of output), and the dice are assumed to both 
     * show the value 1.
     * 
     * @return A String representation of the board.
     * 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // count checkers in the Bar
        sb.append(String.format("%d,", bar.getCheckersOfColour(Colour.RED)));
        sb.append(String.format("%d,", bar.getCheckersOfColour(Colour.BLACK)));

        // count checkers on each of the 24 main points
        for (Point point : points) {
            sb.append(String.format("%d,", point.size()));
        }

        // current dice values
        sb.append(String.format("%d,", World.getDice()[0].getValue()));
        sb.append(String.format("%d", World.getDice()[1].getValue()));
        return sb.toString();
    }
}
