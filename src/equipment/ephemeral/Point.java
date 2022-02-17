package equipment.ephemeral;

import equipment.Checker;

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
}