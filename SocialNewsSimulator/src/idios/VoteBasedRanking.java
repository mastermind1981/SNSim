package idios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VoteBasedRanking implements RankingStrategy {

    protected Map<Item, Integer> ups;
    protected Map<Item, Integer> downs;
    private static final Date dateRenderer = new Date();

    @Override
    public List<Item> rankItems(List<Item> items) {
            ups = new HashMap<>();
            downs = new HashMap<>();
            for (Item item : items) {
                int up = 0, down = 0;
                for (Vote vote: item.getVotes()) {
                    int val = vote.getValue();
                    if (val > 0) {
                        up ++;
                    } else if (val < 0) {
                        down ++;
                    }
                }
                ups.put(item, up);
                downs.put(item, down);
            }
            
            //should mark an item as vote-dirty when it gets new votes
            List<Item> sorted = new ArrayList<>(items);
            Collections.sort(sorted, new Comparator<Item>() {
    
                @Override
                public int compare(Item o1, Item o2) {
    //                return o1.getVotes().size() - o2.getVotes().size();
                    if (o1 == o2)
                        return 0;
                    return itemRank(o1) < itemRank(o2) ? 1 : -1;
                }
                
            });
            
            return sorted;
        }

    protected abstract double itemRank(Item item);

    @Override
    public int score(Item item) {
        return ups.get(item) - downs.get(item);
    }
    
    public String renderItem(Item item) {
    	//dateRenderer.setTime(((long)item.timestamp)*1000);
        return String.format("%s: score %d, up: %d, down: %d, age: %0$.1f hrs", item, score(item), ups.get(item), downs.get(item), ((double)(Simulation.getWorldTime() - item.timestamp))/3600.0);
    }

}