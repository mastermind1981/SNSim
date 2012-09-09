package idios;

import java.util.LinkedList;
import java.util.List;

public class Item extends Tasteable {
    
    private User submitter;
    private Topic topic;
    private List<Vote> votes = new LinkedList<>();

    public Item(TasteProfile taste, Topic topic, int timestamp) {
        super(taste, timestamp);
        this.topic = topic;
    }

    public Item(TasteProfile taste, Topic topic) {
        this(taste, topic, Simulation.getWorldTime());
    }
    
    public Vote recordVote(User voter, int voteValue) {
        Vote vote = new Vote(voter, this, voteValue);
        topic.getVoteManager().add(vote);
        System.out.println("Recorded new vote: "+ vote+ " by user "+ voter );
        return vote;
    }
    
    public User getSubmitter() {
        return submitter;
    }
    
    public Topic getTopic() {
        return topic;
    }
    
    public List<Vote> getVotes(){
        return topic.getVoteManager().getRecords();
    }

}
