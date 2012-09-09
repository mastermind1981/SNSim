package idios;

import java.util.List;

public class Topic {
    public static final int NUM_ITEMS_ON_FRONT_PAGE = 10;
    private final TasteableManager<Item> itemManager = TasteableManagerFactory.createItemManager(this);
    private final RecordManager<Vote> voteManager = new RecordManager<>();
    
    private List<Item> cachedFrontPage;
    
    public TasteableManager<Item> getItemManager() {
        return itemManager;
    }
    public RecordManager<Vote> getVoteManager() {
        return voteManager;
    }
    
    public List<Item> frontPage(final RankingStrategy rankingStrategy) {
        cachedFrontPage = rankingStrategy.rankItems(itemManager.getRecords()).subList(0, NUM_ITEMS_ON_FRONT_PAGE);
        return cachedFrontPage;
    }
    
    public List<Item> getCachedFrontPage() {
        return cachedFrontPage;
    }
}
