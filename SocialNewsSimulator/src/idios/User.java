package idios;

import idios.util.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Tasteable {
    
    private Map<Item, Vote> itemsToVotes = new HashMap<>();
    
    private int timeToNextItem = 0;
    private Item reading = null;

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
        //System.out.println("\tUser "+ this + " rated \n\titem "+ item +" as " + String.format("%0$.2f", rating));
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
    
    public void step(Topic topic) {
        if (timeToNextItem > 0) {
            timeToNextItem--;
        } else if (reading != null) {
            finishReadingItem();
        } else {
            reading = selectNextItemToRead(topic);
            startReadingItem();
        }
    }

    public void finishReadingItem() {
        this.voteAccordingToTaste(reading);
        reading = null;
        timeToNextItem = 0; // can't be TOO SAFE
    }

    public void startReadingItem() {
        if(reading != null)
            timeToNextItem = reading.secondsToRead;
    }

    public Item selectNextItemToRead(Topic topic) {
    	if (Utilities.percentChance(.33)) {
    		return selectFromNewItems(topic);
    	} else {
    		return selectFromFrontPage(topic);
    	}
    }
    
    private Item selectFromNewItems(Topic topic) {
    	List<Item> items = topic.getItemManager().getRecords();
    	Item item = null;
    	
        for (int i = 0; i < Math.min(20, items.size()); i++) {
            try {
                item = items.get(items.size() - 1 - i);
                if (!this.votedOnItem(item) && Utilities.percentChance(.5)) {
                    return item;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return null;
    }
    
    private Item selectFromFrontPage(Topic topic) {
    	List<Item> items = topic.getCachedFrontPage(); // Should mark front page as dirty whenever sim runs.
		if (items == null) {
    		return null;
    	}
		
    	Item item = null;
        for (int i = 0; i < Math.min(20, items.size()); i++) {
            try {
                item = items.get(i);
                if (!this.votedOnItem(item) && Utilities.percentChance(.5)) {
                    return item;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return null;
    }
    
}
