package testing;

import java.util.Scanner;

import world.World;

public class RunGame {

    public static void main(String[] args){
        World myWorld = new World();

        System.out.printf("Choose a game mode:%n1: %s%n2: %s%n3: %s%n", 
        "Human against Human (not available)", 
        "Human against Robot", "Watch two Robots play.");

        Scanner kbd = new Scanner(System.in);

        myWorld.startGame(kbd.nextInt());
        kbd.close();
    }
    
}
