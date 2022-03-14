package equipment;

public class DoublingCube {
    private int doublingcube;

    public DoublingCube(){
        doublingcube = 1;
    }

    public int getDoublingcube() {
        return doublingcube;
    }

    public void setDoublingcube(int doublingcube) {
        this.doublingcube = doublingcube;
    }

    public void doubling(){
        if (doublingcube <64)
            doublingcube *= 2;
    }
}

