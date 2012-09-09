package idios;

import java.util.HashMap;
import java.util.Map;

public class User extends Tasteable {
    
    private Map<Item, Vote> itemsToVotes = new HashMap<>();

    public User(TasteProfile taste, int timestamp) {
        super(taste, timestamp);
    }

    public User(TasteProfile taste) {
        this(taste, Simulation.getWorldTime());
    }
    
    public void voteAccordingToTaste(Item item) {
        // Do we need to adjust this? What if items meeting critera 1 with a lot don't meet criteria 2?
        // Should the user just downvote anything he doesn't upvote, or should he have a range of apathy where he doesn't vote at all (like now)?
        double rating = this.taste.dot(item.taste);
        System.out.println("\tUser "+ this + " rated \n\titem "+ item +" as " + String.format("%0$.2f", rating));
        if (rating > this.taste.length) {
            voteUp(item);
        } else if (rating < -0.5) {
            voteDown(item);
        } else {
            voteMeh(item);
        }
    }

    public void voteOnItem(Item item, int voteValue) {
        // What if we're changing our vote?
        Vote vote = item.recordVote(this, voteValue);
        itemsToVotes.put(item, vote);
    }
    
    public void voteUp(Item item) {
        voteOnItem(item, 1);
    }
    
    public void voteDown(Item item) {
        voteOnItem(item, -1);
    }
    
    public void voteMeh(Item item) {
        voteOnItem(item, 0);
    }
    
    public boolean votedOnItem(Item item) {
        return itemsToVotes.containsKey(item);
    }
}
