package fil.m1.car.akkads.actor;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.japi.Procedure;
import fil.m1.car.akkads.history.DataSendingRecord;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.DataSendingRecordMessage;
import fil.m1.car.akkads.message.SetNeighborsMessage;

public class GraphNodeDataSendingActor extends NodeDataSendingActor {
    
    private List<ActorRef> neighbors;
    
    public GraphNodeDataSendingActor() {
        neighbors = new LinkedList<ActorRef>();
    }

    @Override
    public void sendMessage(DataMessage message) {
        if (neighbors != null) {
            neighbors.forEach(neighbor -> neighbor.tell(message, getSelf()));
        }
    }
    
    @Override
    public Procedure<Object> whenNotSetupState() {
        return new Procedure<Object>() {

            @Override
            public void apply(Object message) throws Exception {
                if (message instanceof SetNeighborsMessage) {
                    final DataSendingRecord record = new DataSendingRecord(getSender(), getSelf(), message);
                    getContext().system().actorSelection(getHistoryActorAddress()).tell(new DataSendingRecordMessage(record), getSelf());
                    final SetNeighborsMessage setNeighborsMessage = (SetNeighborsMessage) message;
                    neighbors = setNeighborsMessage.getNeighbors();
                    unstashAll();
                    getContext().become(whenNotVisitedState());
                } else {
                    stash();
                }
            }
        };
    }

}
