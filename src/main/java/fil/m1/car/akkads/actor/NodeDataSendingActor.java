package fil.m1.car.akkads.actor;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActorWithStash;
import akka.japi.Procedure;
import fil.m1.car.akkads.history.DataSendingRecord;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.DataSendingRecordMessage;
import fil.m1.car.akkads.message.SetHierarchyMessage;

public class NodeDataSendingActor extends UntypedActorWithStash {

    private ActorRef parent;
    private List<ActorRef> children;
    private static final String HISTORY_ACTOR_ADDRESS = "akka.tcp://HistorySystem@127.0.0.1:5000/user/history"; 

    public NodeDataSendingActor() {
        parent = null;
        children = new LinkedList<ActorRef>();
    }

    @Override
    public void preStart() throws Exception {
        getContext().become(whenNotSetup);
    }

    final Procedure<Object> whenNotSetup = new Procedure<Object>() {

        @Override
        public void apply(Object message) throws Exception {
            if (message instanceof SetHierarchyMessage) {
                final DataSendingRecord record = new DataSendingRecord(getSender(), getSelf(), message);
                getContext().system().actorSelection(HISTORY_ACTOR_ADDRESS).tell(new DataSendingRecordMessage(record), getSelf());
                final SetHierarchyMessage setHierarchyMessage = (SetHierarchyMessage) message;
                parent = setHierarchyMessage.getParent();
                children.addAll(setHierarchyMessage.getChildren());
                unstashAll();
                getContext().become(whenNotVisitedYet);
            } else {
                stash();
            }
        }

    };

    final Procedure<Object> whenNotVisitedYet = new Procedure<Object>() {
        @Override
        public void apply(Object message) {
            if (message instanceof DataMessage) {
                final DataSendingRecord record = new DataSendingRecord(getSender(), getSelf(), message);
                getContext().actorSelection(HISTORY_ACTOR_ADDRESS).tell(new DataSendingRecordMessage(record), getSelf());
                final DataMessage dataMessage = (DataMessage) message;
                //System.out.println(self().path().name() + " received the following message from " + sender().path().name() + " : " + dataMessage.getContent());
                if (parent != null) {
                    parent.tell(dataMessage, getSelf());
                }
                children.forEach(child -> child.tell(dataMessage, getSelf()));
                getContext().become(whenAlreadyVisited);
            } else {
                System.err.println("Cannot handle message " + message);
            }
        }
    };

    final Procedure<Object> whenAlreadyVisited = new Procedure<Object>() {
        @Override
        public void apply(Object message) {
            unhandled(message);
        }
    };

    @Override
    public void onReceive(Object message) throws Exception {
    }

}
