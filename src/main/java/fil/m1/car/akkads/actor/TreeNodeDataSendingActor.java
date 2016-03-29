package fil.m1.car.akkads.actor;

import java.util.List;

import akka.actor.ActorRef;
import akka.japi.Procedure;
import fil.m1.car.akkads.history.DataSendingRecord;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.DataSendingRecordMessage;
import fil.m1.car.akkads.message.SetHierarchyMessage;

public class TreeNodeDataSendingActor extends NodeDataSendingActor {

    private ActorRef parent;
    private List<ActorRef> children;

    public TreeNodeDataSendingActor() {
        parent = null;
        children = null;
    }

    @Override
    public void sendMessage(DataMessage message) {
        if (parent != null) {
            parent.tell(message, getSelf());
        }
        if (children != null) {
            children.forEach(child -> child.tell(message, getSelf()));
        }
    }

    @Override
    public Procedure<Object> whenNotSetupState() {
        return new Procedure<Object>() {

            @Override
            public void apply(Object message) throws Exception {
                if (message instanceof SetHierarchyMessage) {
                    final DataSendingRecord record = new DataSendingRecord(getSender(), getSelf(), message);
                    getContext().system().actorSelection(getHistoryActorAddress()).tell(new DataSendingRecordMessage(record), getSelf());
                    final SetHierarchyMessage setHierarchyMessage = (SetHierarchyMessage) message;
                    parent = setHierarchyMessage.getParent();
                    children = setHierarchyMessage.getChildren();
                    unstashAll();
                    getContext().become(whenNotVisitedState());
                } else {
                    stash();
                }
            }
        };
    }

}
