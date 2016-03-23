package fil.m1.car.akkads;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActorWithStash;
import akka.japi.Procedure;

public class NodeDataSendingActor extends UntypedActorWithStash {

    private DataSendingHistory history;
    private ActorRef parent;
    private List<ActorRef> children;

    public NodeDataSendingActor(DataSendingHistory history) {
        this.history = history;
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
                history.addRecord(new DataSendingRecord(getSender(), getSelf(), message));
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
                history.addRecord(new DataSendingRecord(getSender(), getSelf(), message));
                final DataMessage dataMessage = (DataMessage) message;
                System.out.println(self().path().name() + " received the following message from " + sender().path().name() + " : " + dataMessage.getContent());
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
