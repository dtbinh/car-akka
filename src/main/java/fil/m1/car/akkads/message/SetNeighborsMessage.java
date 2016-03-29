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
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("neighbors : ");
        if (neighbors != null) {
            neighbors.forEach(neighbor -> builder.append(neighbor.path().name() + " "));
        }
        else {
            builder.append("none");
        }
        return builder.toString();
    }
    
}
