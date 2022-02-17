package equipment;

import java.util.ArrayList;
import equipment.Checker;
import equipment.ephemeral.Point;
import whoiswho.Colour;

public class Board {

    // I'm thinking of an immutable array of mutable ArrayLists (Points)
    // Points could be classes, but they'd function just the same as an ArrayList
    Point[] points;
    ArrayList bar = new ArrayList<Checker>(0);
    ArrayList<Checker> bearOffSection = new ArrayList<Checker>(0);

    public Board(){
        points = new Point[24];
        for(int i = 0; i < 24; i++){
            points[i] = new Point(i);
        }
        fillStartingPosition(points);
    }

    private void fillStartingPosition(Point[] points) {
        int[] initialPositions = {5, 7, 12, 18};
        int[] numberOfCheckers = {5, 3, 5, 2};
        for (Colour colour : Colour.values()){
            for (int index : initialPositions) {
                for(int checkers : numberOfCheckers) {
                    points[(colour == Colour.RED ? index : 23 - index)].placeChecker(new Checker(colour));
                }
            }
        }
    }

    @Override
    public String toString(){
        return "";
    }
}