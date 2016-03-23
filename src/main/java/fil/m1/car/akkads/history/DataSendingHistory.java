package fil.m1.car.akkads.history;

import java.util.LinkedList;
import java.util.List;

public class DataSendingHistory {
    
    private List<DataSendingRecord> records;
    
    public DataSendingHistory() {
        records = new LinkedList<DataSendingRecord>();
    }
    
    public List<DataSendingRecord> getRecords() {
        return records;
    }
    
    public void addRecord(DataSendingRecord record) {
        records.add(record);
    }

}
