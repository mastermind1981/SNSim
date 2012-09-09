package idios;

public class Topic {
    private final TasteableManager<Item> itemManager = TasteableManagerFactory.createItemManager(this);
    private final RecordManager<Vote> voteManager = new RecordManager<>();
    
    
    
    public TasteableManager<Item> getItemManager() {
        return itemManager;
    }
    public RecordManager<Vote> getVoteManager() {
        return voteManager;
    }
}
