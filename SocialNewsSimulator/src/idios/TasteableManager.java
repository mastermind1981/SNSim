package idios;

public class TasteableManager<T extends Tasteable> extends RecordManager<T> {
    public RecordFactory<T>        randomFactory;
    public RecordFactory<T>        skewedFactory;
    public DerivedRecordFactory<T> mutateFactory;
    public DerivedRecordFactory<T> copyFactory;

    public TasteableManager() {
        super();
    }

    public T createRandom() {
        T t = randomFactory.create();
        add(t);
        return t;
    }

    public T createSkewed() {
        T t = skewedFactory.create();
        add(t);
        return t;
    }

    public T createMutate(T o) {
        T t = mutateFactory.create(o);
        add(t);
        return t;
    }

    public T createCopy(T o) {
        T t = copyFactory.create(o);
        add(t);
        return t;
    }
}
