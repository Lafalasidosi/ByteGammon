package solve;

public class Ply{

    private int start;
    private int end;

    /**
    * Assumes valid notation is passed.
    */
//    public Ply(String move){
//        this.move = move;
//    }

    public Ply(int start, int end){
        this.start = start;
        this.end = end;
    }

    public String getPly(){
        return String.format("%d/%d", start, end);
    }
    
    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    private void setStartPoint(int i){
        this.start = i;
    }

    private void setEndPoint(int i){
        this.end = i;
    }

    public boolean equals(Ply p){
        boolean startSame = this.getStart() == p.getStart();
        boolean endSame = this.getEnd() == p.getEnd();
        return startSame && endSame;
    }

    @Override
    public String toString(){
        return String.format("%d/%d", start, end);
    }

    public void flip() {
        setStartPoint(23 - start);
        setEndPoint(23 - end);
    }
}
