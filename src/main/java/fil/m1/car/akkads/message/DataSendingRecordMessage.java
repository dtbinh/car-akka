package fil.m1.car.akkads.message;

import fil.m1.car.akkads.history.DataSendingRecord;

public class DataSendingRecordMessage implements Message {
    
    private static final long serialVersionUID = 1L;

    public DataSendingRecordMessage() {
    }

    private DataSendingRecord record;

    public DataSendingRecordMessage(DataSendingRecord record) {
        this.record = record;
    }
    
    public DataSendingRecord getRecord() {
        return record;
    }
    
}
