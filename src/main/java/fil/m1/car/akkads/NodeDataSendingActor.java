package fil.m1.car.akkads;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class NodeDataSendingActor extends UntypedActor {

    private ActorRef parent;
    private List<ActorRef> children;

    public NodeDataSendingActor() {
        parent = null;
        children = new LinkedList<ActorRef>();
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof SetParentMessage) {
            final SetParentMessage setParentMessage = (SetParentMessage) message;
            parent = setParentMessage.getParent();
        }
        else if (message instanceof AddChildMessage) {
            final AddChildMessage addChildMessage = (AddChildMessage) message;
            children.add(addChildMessage.getChild());
            return;
        }
        else if (message instanceof DataMessage) {
            final DataMessage dataMessage = (DataMessage) message;
            System.out.println(self().path().name() + " received the following message from " + sender().path().name() + " : " + dataMessage);
            children.forEach(child -> child.tell(dataMessage, getSelf()));
            return;
        }
        System.err.println("Cannot handle message " + message);
    }
}
