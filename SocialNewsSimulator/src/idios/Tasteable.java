package idios;

public abstract class Tasteable extends Record {

    protected TasteProfile taste;

    public Tasteable(TasteProfile taste, int timestamp) {
        super(timestamp);
        this.taste = taste;
    }

    public Tasteable(TasteProfile taste) {
        this(taste, Simulation.getWorldTime());
    }

    public String toString() {
//        return "(" + getID() + ", " + taste.toString() + ", " + String.format("%0$.2f", taste.length) + ")";
    	return "(" + getID() + ", " + String.format("%0$.2f", taste.length) + ")";
    }

}
