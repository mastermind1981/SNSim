package idios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeIndex<T extends Record> {

    private final ArrayList<Integer>    timestamps         = new ArrayList<>();
    private final Map<Integer, Integer> timestampToLeastID = new HashMap<>();
    private final List<T>          records;
    private int                         maxID              = -1;

    public RangeIndex(List<T> records) {
        this.records = records;
    }

    /**
     * @param r
     *            A record to add to the index. Must be added in chronological
     *            order.
     */
    public void addRecord(T r) {
        assert r.getTimestamp() >= timestamps.get(timestamps.size() - 1) : "Newest record added out of chronological order.";
        
        timestamps.add(r.getTimestamp());
        maxID = r.getID();
        if (!timestampToLeastID.containsKey(r.getTimestamp())) {
            timestampToLeastID.put(r.getTimestamp(), r.getID());
        }
    }

    /**
     * @param query
     *            An integer sought in the array.
     * @return The smallest integer greater than or equal to query.
     */
    private int findNearestDatumIndex(int query) {
        int low = 0, mid;
        int high = timestamps.size() - 1;
        int midval;

        while (low <= high) {
            mid = ((high - low) / 2) + low;
            midval = timestamps.get(mid);

            if (query > midval) {
                low = mid + 1;
            } else if (query < midval) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return low;
    }

    public List<T> allInRange(int low, int high) {
        assert low < high;

        int lowIndex = findNearestDatumIndex(low);
        int highIndex = findNearestDatumIndex(high);

        int lowID, highID;

        if (lowIndex >= timestamps.size()) {
            lowIndex = timestamps.size() - 1;
        }

        lowID = timestampToLeastID.get(timestamps.get(lowIndex));

        if (highIndex >= timestamps.size()) {
            highID = maxID + 1;
        } else {
            highID = timestampToLeastID.get(timestamps.get(highIndex));
        }

        return records.subList(lowID, highID);
    }

}
