package idios;

import java.util.List;

public interface RankingStrategy {

    public List<Item> rankItems(List<Item> items);
    
    public int score(Item item);
    
    public String renderItem(Item item);
    
}
