package idios;

import idios.util.Utilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Simulation {

    private static int worldTime = 0;
    private static int linksPerHour = 100;
    private static double minutesPerLink = 3;
    private final int initialUsers;
    private final int newUsers;

    public static int getWorldTime() {
        return worldTime;
    }

    private Map<String, Topic>     topics      = new HashMap<>();
    private TasteableManager<User> userManager = TasteableManagerFactory
                                                       .createUserManager(0);
    
    public Topic getTopic(String name) {
        return topics.get(name);
    }

    private RankingStrategy        rankingStrategy;
    
    private List<User> veterans = new LinkedList<>();

    public Simulation(RankingStrategy rankingStrategy, int numInitialUsers, int numNewUsers) {
        this.rankingStrategy = rankingStrategy;
        this.initialUsers = numInitialUsers;
        this.newUsers = numNewUsers;
        worldTime = 0;
    }

    public void setup() {
        createFounderPopulation(); //idea: inject new users, mutate taste from an existing item to simulate an inflow of users from a shared link.
        
        topics.put("kings", new Topic());
    }

	private void createFounderPopulation() {
		User original = userManager.createRandom();
        veterans.add(original);
        for (int i = 0; i < initialUsers - 1; i++) {
            veterans.add(userManager.createMutate(original));
        }
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
        
        // Idea... time delay for voting to ensure not lots of quick votes. We record every transaction and we don't want a dos.
        
        for (final User user : userManager.getRecords()) {
            user.step(topics.get("kings"));
        }
        
        worldTime++;
    }
    
    public RankingStrategy getRankingStrategy() {
        return rankingStrategy;
    }
    
    public List<Item> frontPage(final String topicName) {
        return rankingStrategy.rankItems(topics.get(topicName).getItemManager().getRecords()).subList(0, 10);
    }
    
    public double[] judgeFitnessOfFrontPage(final String topicName) {
        List<Item> frontPage = this.frontPage(topicName);
        TasteProfile[] tastes = new TasteProfile[frontPage.size()];
        List<User> users = userManager.getRecords();
        double[] fitness = new double[users.size()];
        
        for (int i = 0; i < frontPage.size(); i++) {
            tastes[i] = frontPage.get(i).taste;
        }
        
        for (int i = 0; i < users.size(); i++) {
            fitness[i] = users.get(i).taste.evaluateFitness(tastes); // Refactor this to make Tasteables evaluate a Tasteables List
        }
        
        return fitness;
    }
    
    public static void main(String[] args) {
        Utilities.seed(42089);
        Simulation sim = new Simulation(new RedditRanking(), 10, 0);
        sim.setup();
        sim.run(60*60*24);
        
        double[] fitness = sim.judgeFitnessOfFrontPage("kings");
        
        System.out.println("Kings front page: ");
        for (Item item : sim.frontPage("kings")) {
            System.out.print(item);
            System.out.print(": score ");
            System.out.print(sim.rankingStrategy.score(item));
            System.out.print(", time: ");
            System.out.println(item.timestamp);
        }
        
        for(int i = 0; i < fitness.length; i++) {
            System.out.println("User " + sim.userManager.get(i) + ": "+ String.format("%0$.2f", fitness[i]));
        }
    }
}
