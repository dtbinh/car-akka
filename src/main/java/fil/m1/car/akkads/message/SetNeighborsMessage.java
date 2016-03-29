package fil.m1.car.akkads.message;

import java.util.List;

import akka.actor.ActorRef;

public class SetNeighborsMessage implements Message {
    
    private static final long serialVersionUID = 1L;
    private List<ActorRef> neighbors;
    
    public SetNeighborsMessage() {
    }

    public SetNeighborsMessage(List<ActorRef> neighbors) {
        this.neighbors = neighbors;
    }
    
    public List<ActorRef> getNeighbors() {
        return neighbors;
    }
    
}
