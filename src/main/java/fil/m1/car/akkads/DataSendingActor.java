package fil.m1.car.akkads;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.LinkedList;
import java.util.List;

public class DataSendingActor extends UntypedActor {

    private List<ActorRef> children;

    public DataSendingActor() {
        children = new LinkedList<ActorRef>();
    }

    // TODO refactoring
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            System.out.println(self().path().name() + " received the following message from " + sender().path().name() + " : " + message);
            children.forEach(child -> child.tell(message, getSelf()));
            return;
        }
        else if (message instanceof ActorRef) {
            final ActorRef child = (ActorRef) message;
            children.add(child);
            return;
        }
        unhandled(message);
    }
}
