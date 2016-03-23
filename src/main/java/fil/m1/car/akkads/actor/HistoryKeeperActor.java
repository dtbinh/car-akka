package fil.m1.car.akkads.actor;

import akka.actor.UntypedActor;
import fil.m1.car.akkads.history.DataSendingHistory;
import fil.m1.car.akkads.history.DataSendingRecord;
import fil.m1.car.akkads.message.DataSendingRecordMessage;
import fil.m1.car.akkads.message.RenderHistoryMessage;

public class HistoryKeeperActor extends UntypedActor {
    
    private DataSendingHistory history;
    
    public HistoryKeeperActor() {
        this.history = new DataSendingHistory();
    }
    
    public DataSendingHistory getHistory() {
        return history;
    }
    
    public void addRecord(DataSendingRecord record) {
        history.addRecord(record);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof DataSendingRecordMessage) {
            System.out.println("???");
            final DataSendingRecordMessage dataSendingRecordMessage = (DataSendingRecordMessage) message;
            history.addRecord(dataSendingRecordMessage.getRecord());
        }
        else if (message instanceof RenderHistoryMessage) {
            System.out.println("H : " + history.getRecords().size());
        }
        else {
            unhandled(message);
        }
    }

}
