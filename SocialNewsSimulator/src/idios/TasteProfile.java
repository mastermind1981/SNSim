package idios;


import java.util.Arrays;

import idios.util.Utilities;

// cosine to check taste compatibility, dot to check rating,
// short vector means more discriminating, long means lower standards.

public class TasteProfile {
    public static final int NUM_PREFS = 3;
    private final double[]        preferences;

    public TasteProfile(double[] prefs) {
        assert prefs.length == NUM_PREFS;
        preferences = prefs;
    }
    
    public TasteProfile(TasteProfile source) {
        this(source.preferences.clone());
    }

    public static TasteProfile random() {
        return skewedRandom(0);
    }

    public static TasteProfile skewedRandom(double mean) {
        double[] preferences = new double[NUM_PREFS];
        for (int i = 0; i < NUM_PREFS; i++) {
            preferences[i] = Utilities.rand.nextGaussian() + mean;
        }
        return new TasteProfile(preferences);
    }
    
    public static TasteProfile mutate(TasteProfile parent, double spread) {
        double[] preferences = parent.preferences.clone();
        for (int i = 0; i < NUM_PREFS; i++) {
            preferences[i] += Utilities.rand.nextGaussian()*spread;
        }
        return new TasteProfile(preferences);
    }

    public double getPreference(int i) {
        return preferences[i];
    }

    public double dot(TasteProfile other) {
        double sum = 0;
        for (int i = 0; i < NUM_PREFS; i++) {
            sum += this.preferences[i] * other.preferences[i];
        }
        return sum;
    }
    
    public double lengthSquared(){
        return dot(this);
    }
    
    public double length(){
        return Math.sqrt(lengthSquared());
    }
    
    public double evaluateFitness(TasteProfile[] items) {
        //double[] scores = new double[items.length];
        //Do we want something more nuanced? Weight things over a certain amount? Discard a certain number of things below a certain amount?
        double sum = 0;
        for (TasteProfile item : items) {
            sum += this.dot(item);
        }
        return sum;
    }
    
    public String toString() {
        return Arrays.toString(preferences);
    }

}
