package fil.m1.car.akkads.message;

import java.util.List;

import akka.actor.ActorRef;

public class SetNeighborsMessage implements Message {
    
    private List<ActorRef> neighbors;

    public SetNeighborsMessage(List<ActorRef> neighbors) {
        this.neighbors = neighbors;
    }
    
    public List<ActorRef> getNeighbors() {
        return neighbors;
    }
    
}
