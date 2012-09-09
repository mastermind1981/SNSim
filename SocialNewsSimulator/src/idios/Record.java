package idios;

public abstract class Record {
    
    private int            id = -1;
    public final int       timestamp;
    
    public Record() {
        this.timestamp = Simulation.getWorldTime();
    }
    
    public Record(int timestamp) {
        this.timestamp = timestamp;
    }
    
    public void setID(int id) {
        if (this.id < 0 && id >= 0)
            this.id = id;
    }

    public int getID() {
        return id;
    }

    public int getTimestamp() {
        return timestamp;
    }
}