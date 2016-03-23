package fil.m1.car.akkads;

import akka.actor.ActorRef;

public class SetParentMessage {
    
    private ActorRef parent;

    public SetParentMessage(ActorRef parent) {
        this.parent = parent;
    }
    
    public ActorRef getParent() {
        return parent;
    }

}
