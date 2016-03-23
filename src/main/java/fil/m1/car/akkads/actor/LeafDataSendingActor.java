package fil.m1.car.akkads.actor;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class LeafDataSendingActor extends UntypedActor {
    
    private List<ActorRef> neighbors;
    
    public LeafDataSendingActor() {
        neighbors = new LinkedList<ActorRef>();
    }

    @Override
    public void onReceive(Object arg0) throws Exception {
        
    }

}
