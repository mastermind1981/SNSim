package idios;

public class TasteableManagerFactory {
    public static final double MUTATION_RATE = 0.75;
    public static final double DEFAULT_SKEW = -0.25;

    public static TasteableManager<User> createUserManager() {
        return createUserManager(DEFAULT_SKEW);
    }

    public static TasteableManager<User> createUserManager(final double skew) {
        TasteableManager<User> manager = new TasteableManager<>();
        manager.randomFactory = new RecordFactory<User>() {

            @Override
            public User create() {
                TasteProfile taste = TasteProfile.random();
                return new User(taste);
            }
        };
        manager.skewedFactory = new RecordFactory<User>() {

            @Override
            public User create() {
                TasteProfile taste = TasteProfile.skewedRandom(skew);
                return new User(taste);
            }
        };
        manager.mutateFactory = new DerivedRecordFactory<User>() {
            
            @Override
            public User create(User u) {
                TasteProfile taste = TasteProfile.mutate(u.taste, MUTATION_RATE);
                return new User(taste);
            }
        };

        return manager;
    }

    public static TasteableManager<Item> createItemManager(Topic topic) {
        return createItemManager(topic, DEFAULT_SKEW);
    }

    public static TasteableManager<Item> createItemManager(final Topic topic, final double skew) {
        TasteableManager<Item> manager = new TasteableManager<>();
        manager.randomFactory = new RecordFactory<Item>() {

            @Override
            public Item create() {
                TasteProfile taste = TasteProfile.random();
                return new Item(taste, topic);
            }
        };
        manager.skewedFactory = new RecordFactory<Item>() {

            @Override
            public Item create() {
                TasteProfile taste = new TasteProfile(new double[TasteProfile.NUM_PREFS]);
                taste = TasteProfile.mutate(taste, 1);
                return new Item(taste, topic);
            }
        };

        return manager;
    }

}
