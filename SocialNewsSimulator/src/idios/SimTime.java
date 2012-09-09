package idios;

public class SimTime {
    
    public static int getWorldSeconds() {
        return Simulation.getWorldTime();
    }
    
    public static int getWorldMinutes() {
        return Simulation.getWorldTime()/60;
    }
    
    public static int getWorldHours() {
        return Simulation.getWorldTime()/(60 * 60);
    }
    
    public static int getWorldDays() {
        return Simulation.getWorldTime()/(60 * 60 * 24);
    }
    
    public static int getWorldYears() {
        return getWorldDays() / 365;
    }
    
    public static int getClockSeconds() {
        return Simulation.getWorldTime() % 60;
    }
    
    public static int getClockMinutes() {
        return getWorldMinutes() % 60;
    }
    
    public static int getClockHours() {
        return getWorldHours() % 24;
    }
    
    public static int getClockDays() {
        return getWorldDays() % 365;
    }
    
    public static int getClockYears() {
        return getWorldYears();
    }
}
