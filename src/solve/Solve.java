package solve;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import equipment.Board;
import equipment.Die;
import readFile.FileReader;

public class Solve {
    
    public static ArrayList<Move> moves = new ArrayList<Move>(0);
    public static ArrayList<Ply> plies = new ArrayList<Ply>(0);
    public static boolean hit;

    public static ArrayList<Move> Solve(Board b, Die d1, Die d2) {
        moves.clear();
        int[] board = new int[]{1,2,3,4};
        int[] diceRolls;
        int[] reverseDiceRolls;
        hit = true; // for testing

        // take a board from input
        // (translate from Casperson's format to mine)
        // return possible moves

            reverseDiceRolls = new int[]{diceRolls[1], diceRolls[0]};
            if(diceRolls[0] == diceRolls[1]) {
                diceRolls = timesTwo(diceRolls);
                reverseDiceRolls = timesTwo(reverseDiceRolls);
            }

            System.out.println();
            displayBoard(board);
            System.out.println("\nLegal moves for this board:");

            solve(board, plies, diceRolls, true);
            solve(board, plies, reverseDiceRolls, true);

            prune(moves);

            for(Move m : moves){
                System.out.println(m.toString());
            }

            return moves;

    }

    /**
    * Want: First off, the case for returning is if you have no more moves to work with
    * or you reach the end of the board
    * Also, whenever you derecurse, the last ply found should be deleted
    * If an illegal move is encountered, do nothing and continue
    * automatically handles "doubles" since size of rollsLeft is barely mentioned.
    */
    public static void solve(int[] board, ArrayList<Ply> plies, int[] rollsLeft, boolean hit){
        // "base case"
        if(rollsLeft.length == 0){
            moves.add(new Move(plies));
            plies.remove(plies.size() - 1);
            return;
        }

        int[] boardCopy = Arrays.copyOf(board, board.length);

        // case when hit
        if(hit){
            int moveTo;
            for(int i = 23; i > 17; i--){
                moveTo = 24 - rollsLeft[0]; 
                if(board[i] > -2 && i == 24 - rollsLeft[0]){
                    hit = false;
                    plies.add(new Ply(25, moveTo));
                    if (moveTo > -1)
                        boardCopy[moveTo]++;
                    else
                        boardCopy[moveTo] += 2;
                 } else {
                    continue;
                 }
                solve(boardCopy, plies, subarray(rollsLeft), hit); 
            }
        }

       int l = board.length;
       for(int i = 0; i < l; i++){
            if(board[i] < 1)
                continue;
            else{
                int moveTo = boardCopy[i - rollsLeft[0]];
                if (i >= rollsLeft[0] && moveTo > -2) {
                    boardCopy[i]--;
                    plies.add(new Ply(i+1, i+1-rollsLeft[0]));
                    if (moveTo > -1)
                        boardCopy[i - rollsLeft[0]]++;
                    else
                        boardCopy[i - rollsLeft[0]] += 2;
                 } else {
                    continue;
                 }
                solve(boardCopy, plies, subarray(rollsLeft), hit); 
            }
       }
       if(plies.size() > 0) // remove last ply whenever method returns
            moves.add(new Move(plies));
            if(plies.size() > 0)
                plies.remove(plies.size() - 1);
            return;
    }

    public static void prune(ArrayList<Move> moves){
        // you must use as many dice as possible
        removeXPlyMoves(moves, maxMoveSize(moves));

        // remove any duplicate entries
        int length = moves.size();
        for(int i = 0; i < length; i++){
            for(int j = i+1; j < length; j++){
                if(moves.get(i).equals(moves.get(j))){
                    moves.remove(j);
                    length--;
                }
            }
        }
    }

    public static void removeXPlyMoves(ArrayList<Move> moves, int size){
        for(int i = 0; i < moves.size(); i++){
            if(moves.get(i).getSize() < size) {
                moves.remove(i);
                i--;
            }
        }
    }

    public static int maxMoveSize(ArrayList<Move> moves){
        int result = 0;
        for(Move m : moves){
            if(m.getSize() == 4){
                return 4;
            }
            result = Math.max(result, m.getSize());
        }
        return result;
    }
    
    /**
     * Ham-fisted way of shortening the rollsLeft array in solve().
     * 
     * @param arr
     * @return
     */
    public static int[] subarray(int[] arr){
        int[] result;
        if(arr.length > 1){
            result = new int[arr.length - 1];
            for(int i = 0; i < arr.length -1; i++)
                result[i] = arr[i+1];
        }
        else{
            result = new int[0];
        }
        return result;
    }
    
    public static void displayBoard(int[] b){
        for(int a : b)
            System.out.printf("%d,", a);
    }

    public static int[] timesTwo(int[] x){
        int[] result = new int[4];
        for(int i = 0; i < 4; i++)
            result[i] = x[0];
        return result;
    }

}
