package solve;

public class Ply{

    private String move;

    /**
    * Assumes valid notation is passed.
    */
    public Ply(String move){
        this.move = move;
    }

    public Ply(int start, int end){
        move = String.format("%d/%d", start, end);
    }

    public String getPly(){
        return move;
    }
    
    public int getStartPoint(){
        return Integer.parseInt(move.split("/")[0]);
    }

    public int getEndPoint(){
        return Integer.parseInt(move.split("/")[1]);
    }

    public boolean equals(Ply p){
        boolean startSame = this.getStartPoint() == p.getStartPoint();
        boolean endSame = this.getEndPoint() == p.getEndPoint();
        return startSame && endSame;
    }

    @Override
    public String toString(){
        return move;
    }
}
