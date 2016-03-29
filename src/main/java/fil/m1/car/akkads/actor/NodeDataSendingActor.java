package fil.m1.car.akkads.actor;

import akka.actor.UntypedActorWithStash;
import akka.japi.Procedure;
import fil.m1.car.akkads.history.DataSendingRecord;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.DataSendingRecordMessage;

public abstract class NodeDataSendingActor extends UntypedActorWithStash {

    @Override
    public void preStart() throws Exception {
        getContext().become(whenNotSetupState());
    }
    
    public String getHistoryActorAddress() {
        return "akka.tcp://HistorySystem@127.0.0.1:5000/user/history";
    }

    public abstract Procedure<Object> whenNotSetupState();

    public Procedure<Object> whenNotVisitedState() {
        return new Procedure<Object>() {
            @Override
            public void apply(Object message) {
                if (message instanceof DataMessage) {
                    final DataSendingRecord record = new DataSendingRecord(getSender(), getSelf(), message);
                    getContext().actorSelection(getHistoryActorAddress()).tell(new DataSendingRecordMessage(record), getSelf());
                    final DataMessage dataMessage = (DataMessage) message;
                    sendMessage(dataMessage);
                    getContext().become(whenVisitedState());
                } else {
                    System.err.println("Cannot handle message " + message);
                }
            }
        };
    }
    
    public Procedure<Object> whenVisitedState() {
        return new Procedure<Object>() {
            @Override
            public void apply(Object message) {
                unhandled(message);
            }
        };
    }

    @Override
    public void onReceive(Object message) throws Exception {
    }

    public abstract void sendMessage(DataMessage message);

}
