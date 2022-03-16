package solve;

import java.util.ArrayList;
import java.util.Arrays;

import equipment.Board;
import player.Player;
import whoiswho.Colour;
//import readFile.FileReader;

public abstract class Solve {

    public static ArrayList<Move> moves = new ArrayList<Move>(0);
    public static ArrayList<Ply> plies = new ArrayList<Ply>(0);

    public static ArrayList<Move> analyze(Board b, Player player) {
        moves.clear();
        int[] board = extractPlayersBoard(b, player);
        String[] tempDiceRolls = Arrays.copyOfRange(b.toString().split(","), 26, 28);
        int[] diceRolls = new int[]{Integer.parseInt(tempDiceRolls[0]), Integer.parseInt(tempDiceRolls[1])};
        int[] reverseDiceRolls;

        // take a board from input
        // (translate from Casperson's format to mine)
        // return possible moves

        reverseDiceRolls = new int[]{diceRolls[1], diceRolls[0]};
        if (diceRolls[0] == diceRolls[1]) {
            diceRolls = timesTwo(diceRolls);
            reverseDiceRolls = timesTwo(reverseDiceRolls);
        }

//        System.out.println();
//        displayBoard(board);
//        System.out.println("\nLegal moves for this board:");

        solve(board, plies, diceRolls);
        if(diceRolls.length > 2)
            solve(board, plies, reverseDiceRolls);

        prune(moves, player);

//        for (Move m : moves) {
//            System.out.println(m.toString());
//        }

        return moves;

    }

    private static int[] extractPlayersBoard(Board b, Player player) {
        int[] result = new int[25];
        if (player.getColour() == Colour.BLACK) {
            int[] tmp = Arrays.copyOfRange(Board.string2IntArray(b.toString()), 0, 25);
            for (int i = 0; i < 24; i++) {
                result[i] = -1 * tmp[24 - i];
            }
        } else {
            result = Arrays.copyOfRange(Board.string2IntArray(b.toString()), 1, 26);
        }
        return result;
    }

    /**
     * Want: First off, the case for returning is if you have no more moves to work with
     * or you reach the end of the board
     * Also, whenever you derecurse, the last ply found should be deleted
     * If an illegal move is encountered, do nothing and continue
     * automatically handles "doubles" since size of rollsLeft is barely mentioned.
     */
    public static void solve(int[] board, ArrayList<Ply> plies, int[] rollsLeft) {
        int roll = rollsLeft.length > 0 ? rollsLeft[0] : -1;
        // "base case"
        if (rollsLeft.length == 0) {
            moves.add(new Move(plies));
            plies.remove(plies.size() - 1);
            return;
        }

        int[] boardCopy = Arrays.copyOf(board, board.length);

        // case when hit
        if (boardCopy[24] > 0) {
            int moveTo;
            for (int i = 23; i > 17; i--) {
                moveTo = 24 - roll;
                if (board[i] > -2 && i == 24 - roll) {
                    boardCopy[24]--;
                    plies.add(new Ply(25, moveTo));
                    if (moveTo > -1)
                        boardCopy[moveTo]++;
                    else
                        boardCopy[moveTo] += 2;
                } else {
                    continue;
                }
                solve(boardCopy, plies, subarray(rollsLeft));
            }
        }

        // case when can bear off
        if(!existsCheckerOutsideHomeBoard(boardCopy)){
            /*
            start checking at point equal to a die value
            if there's a checker there, find its legal move
            if not, check up to point 6; if checker there, find a legal move for it
            otherwise, remove a checker from the next highest point
             */
            boolean canUseRollOrHigherChecker = false;
            for(int i = roll-1; i < 6 & i >= 0; i++){ // check for point rolled and higher points
                if(boardCopy[i] > 0 && roll == i+1) { // this seems super bad, will rewrite
                    canUseRollOrHigherChecker = true;
                    plies.add(new BearOff(i+1));
                    boardCopy[i]--;
                    solve(boardCopy, plies, subarray(rollsLeft));
                } else if(boardCopy[i] > 0){
                    canUseRollOrHigherChecker = true;
                    boardCopy[i-roll]++;
                    boardCopy[i]--;
                    plies.add(new Ply(i, i-roll));
                    solve(boardCopy, plies, subarray(rollsLeft));
                }
            }

            if(!canUseRollOrHigherChecker) {
                for (int i = roll - 2; i >= 0; i--) { // find next highest checker to move
                    if(boardCopy[i] > 0){
                        plies.add(new BearOff(i+1));
                        solve(boardCopy, plies, subarray(rollsLeft));
                    }
                }
            }
        }

        int l = board.length;
        for (int i = 0; i < l; i++) {
            if (board[i] < 1 || i - roll < 0)
                continue;
            else {
                int moveTo = boardCopy[i - roll];
                if (i >= roll && moveTo > -2) {
                    boardCopy[i]--;
                    plies.add(new Ply(i + 1, i + 1 - roll));
                    if (moveTo > -1)
                        boardCopy[i - roll]++;
                    else
                        boardCopy[i - roll] += 2;
                } else {
                    continue;
                }
                solve(boardCopy, plies, subarray(rollsLeft));
            }
        }
        if (plies.size() > 0) // remove last ply whenever method returns
            moves.add(new Move(plies));
        if (plies.size() > 0)
            plies.remove(plies.size() - 1);
        return;
    }

    private static boolean existsCheckerOutsideHomeBoard(int[] boardCopy) {
        int length = boardCopy.length;
        for(int i = 6; i < length; i++){
            if(boardCopy[i] > 0)
                return true;
        }
        return false;
    }

    public static void prune(ArrayList<Move> moves, Player player) {
        // you must use as many dice as possible
        removeXPlyMoves(moves, maxMoveSize(moves));

        // remove any duplicate entries
        int length = moves.size();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (moves.get(i).equals(moves.get(j))) {
                    moves.remove(j);
                    length--;
                }
            }
        }

        // this code doesn't work last I implemented it
        // also, moves don't necessarily need to be written differently
        // 24/23 is understood to mean 24/23 by Red,
        // 1/2 for Black from Red's perspective
//        if(player.getColour() == Colour.BLACK){
//            for(Move m: moves){
//                m.flip();
//            }
//        }
    }

    public static void removeXPlyMoves(ArrayList<Move> moves, int size) {
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i).getSize() < size) {
                moves.remove(i);
                i--;
            }
        }
    }

    public static int maxMoveSize(ArrayList<Move> moves) {
        int result = 0;
        for (Move m : moves) {
            if (m.getSize() == 4) {
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
    public static int[] subarray(int[] arr) {
        int[] result;
        if (arr.length > 1) {
            result = new int[arr.length - 1];
            for (int i = 0; i < arr.length - 1; i++)
                result[i] = arr[i + 1];
        } else {
            result = new int[0];
        }
        return result;
    }

    public static void displayBoard(int[] b) {
        for (int a : b)
            System.out.printf("%d,", a);
    }

    public static int[] timesTwo(int[] x) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++)
            result[i] = x[0];
        return result;
    }
}

    //method for determining the number of actions the ai can make
    /*
    In this particular case, the results would just be the moves that can be made from each given state.
     */

