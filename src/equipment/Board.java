package equipment;

import java.util.ArrayList;
import equipment.Checker;
import equipment.ephemeral.*;
import whoiswho.Colour;
import world.World;

public class Board {

    // I'm thinking of an immutable array of mutable ArrayLists (Points)
    // Points could be classes, but they'd function just the same as an ArrayList
    Point[] points;
    Bar bar;
    ArrayList<Checker> bearOffSection = new ArrayList<Checker>(0);

    public Board(){
        points = new Point[24];
        bar = new Bar(24);
        for(int i = 0; i < 24; i++){
            points[i] = new Point(i);
        }
        fillStartingPosition(points);
    }

    private void fillStartingPosition(Point[] points) {
        int[] initialPositions = {5, 7, 12, 23};
        int[] numberOfCheckers = {5, 3, 5, 2};
        for (Colour colour : Colour.values()){
            for (int i = 0; i < 4; i++) {
                for(int j = 0; j<numberOfCheckers[i]; j++){
                    points[points[initialPositions[i]].getPointPerPlayer(colour)].placeChecker(new Checker(colour));
                }
            }
        }
    }

    @Override
    public String toString(){
        /*
        want: go through points and add the number of checkers currently on it
         */
        int count;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d,", bar.getCheckersOfColour(Colour.RED)));
        sb.append(String.format("%d,", bar.getCheckersOfColour(Colour.BLACK)));
        for(Point point: points) {
            sb.append(String.format("%d,", point.size()));
        }
        sb.append(String.format("%d,", World.getDice()[0].getValue()));
        sb.append(String.format("%d", World.getDice()[1].getValue()));
        return sb.toString();
    }
}
