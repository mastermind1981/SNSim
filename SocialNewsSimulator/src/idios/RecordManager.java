package idios;

import java.util.ArrayList;
import java.util.List;

public class RecordManager<T extends Record> {

    protected List<T> records;
    private RangeIndex<T> rangeIndex;

    public RecordManager() {
        this.records = new ArrayList<>();
        this.rangeIndex = new RangeIndex<>(this.records);
    }

    public RecordManager(List<T> objects) {
        this.records = objects;
    }

    public T get(int id) {
        return records.get(id);
    }

    public void add(T record) {
        records.add(record);
        record.setID(records.size() - 1);
        rangeIndex.addRecord(record);
    }
    
    /**
     * @param time 
     * @return A list view of all records after or at low and before high.
     */
    public List<T> getAllInTimeRange(int low, int high) {
        return rangeIndex.allInRange(low, high);
    }
    
    /**
     * @param time 
     * @return A list view of all records with timestamp strictly earlier than time.
     */
    public List<T> getAllBefore(int time) {
        return rangeIndex.allInRange(Integer.MIN_VALUE, time);
    }
    
    /**
     * @param time 
     * @return A list view of all records with timestamp after or at time.
     */
    public List<T> getAllAfter(int time) {
        return rangeIndex.allInRange(time, Integer.MAX_VALUE);
    }
    
    public List<T> getRecords() {
        return this.records;
    }

}