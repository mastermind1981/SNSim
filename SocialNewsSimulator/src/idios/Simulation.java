package idios;

import idios.util.Utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {

    private static int worldTime = 0;
    private static int linksPerHour = 5;
    private static double minutesPerLink = 3;
    private static final int initialUsers = 10;

    public static int getWorldTime() {
        return worldTime;
    }

    private Map<String, Topic>     topics      = new HashMap<>();
    private TasteableManager<User> userManager = TasteableManagerFactory
                                                       .createUserManager(0);

    private RankingStrategy        rankingStrategy;

    public Simulation(RankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    public void setup() {
        User original = userManager.createRandom();
        for (int i = 0; i < initialUsers - 1; i++) {
            userManager.createMutate(original);
        }
        
        topics.put("kings", new Topic());
    }

    public void run(int duration) {
        for (int i = 0; i < duration; i++) {
            step();
        }
    }

    private void step() {
        TasteableManager<Item> itemManager = topics.get("kings").getItemManager();
        if (Utilities.percentChance(linksPerHour/60.0/60.0)) {
            itemManager.createSkewed();
        }
        
        // Idea... time delay for voting to ensure not lots of quick votes.
        
        for (final User user : userManager.getRecords()) {
            if (Utilities.percentChance(minutesPerLink/60.0)) {
                List<Item> items = itemManager.getRecords();
                Item item = null;
                for (int i = items.size() - 1; i >= 0; i--) {
                    item = items.get(i);
                    if (!user.votedOnItem(item)) {
                        user.voteAccordingToTaste(item);
                    }
                }
            }
        }
        
        worldTime++;
    }
    
    public static void main(String[] args) {
        Utilities.seed(42089);
        Simulation sim = new Simulation(null);
        sim.setup();
        sim.run(60*60*24);
    }
}
