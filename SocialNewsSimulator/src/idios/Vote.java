package idios;

public class Vote extends Record {
    public final User voter;
    public final Item item;
    public final int  value;

    public Vote(User user, Item item, int value, int timestamp) {
        super(timestamp);
        this.voter = user;
        this.item = item;
        this.value = value;
    }
    
    public Vote(User user, Item item, int value) {
        this(user, item, value, Simulation.getWorldTime());
    }

    public User getVoter() {
        return voter;
    }

    public Item getItem() {
        return item;
    }

    public int getValue() {
        return value;
    }
    
    public String toString() {
        return "" + value;
    }
}
