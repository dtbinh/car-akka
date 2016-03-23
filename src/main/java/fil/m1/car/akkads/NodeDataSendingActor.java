package fil.m1.car.akkads;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;

public class NodeDataSendingActor extends UntypedActor {

    private ActorRef parent;
    private List<ActorRef> children;

    public NodeDataSendingActor() {
        parent = null;
        children = new LinkedList<ActorRef>();
    }

    @Override
    public void preStart() throws Exception {
        getContext().become(whenNotVisitedYet);
    }

    final Procedure<Object> whenNotVisitedYet = new Procedure<Object>() {
        @Override
        public void apply(Object message) {
            if (message instanceof SetParentMessage) {
                final SetParentMessage setParentMessage = (SetParentMessage) message;
                parent = setParentMessage.getParent();
            } else if (message instanceof AddChildMessage) {
                final AddChildMessage addChildMessage = (AddChildMessage) message;
                final ActorRef child = addChildMessage.getChild();
                children.add(child);
                child.tell(new SetParentMessage(getSelf()), getSelf());
            } else if (message instanceof DataMessage) {
                final DataMessage dataMessage = (DataMessage) message;
                System.out.println(self().path().name() + " received the following message from " + sender().path().name() + " : " + dataMessage);
                if (parent != null) {
                    parent.tell(dataMessage, getSelf());
                }
                children.forEach(child -> child.tell(dataMessage, getSelf()));
                getContext().become(whenAlreadyVisited);
                return;
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
