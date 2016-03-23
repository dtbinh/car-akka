package fil.m1.car.akkads;

import akka.actor.ActorRef;

public class AddChildMessage {
    
    private ActorRef child;

    public AddChildMessage(ActorRef child) {
        this.child = child;
    }
    
    public ActorRef getChild() {
        return child;
    }

}
