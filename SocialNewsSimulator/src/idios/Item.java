package idios;

import idios.util.Utilities;

import java.util.LinkedList;
import java.util.List;

public class Item extends Tasteable {
    
    private User submitter;
    private Topic topic;
    private List<Vote> votes = new LinkedList<>();
    public final int secondsToRead;

    public Item(TasteProfile taste, Topic topic, int timestamp, int length) {
        super(taste, timestamp);
        this.topic = topic;
        this.secondsToRead = length;
    }

    public Item(TasteProfile taste, Topic topic) {
        this(taste, topic, Simulation.getWorldTime(), (int) Math.max(5, 3*60 + Utilities.rand.nextGaussian()*3*60) ); // Idea: track how long a user spends on a page, and weight the vote by that.
    }
    
    public Vote recordVote(User voter, int voteValue) {
        Vote vote = new Vote(voter, this, voteValue);
        topic.getVoteManager().add(vote);
        votes.add(vote);
//        if(voteValue != 0)
//            System.out.println("Recorded new vote: "+ vote+ " by user "+ voter +" on item " + this );
        return vote;
    }
    
    public User getSubmitter() {
        return submitter;
    }
    
    public Topic getTopic() {
        return topic;
    }
    
    public List<Vote> getVotes(){
        return votes;
    }

}
