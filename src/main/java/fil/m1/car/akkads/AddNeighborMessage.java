package fil.m1.car.akkads;

import akka.actor.ActorRef;

public class AddNeighborMessage {
    
    private ActorRef neighbor;

    public AddNeighborMessage(ActorRef neighbor) {
        this.neighbor = neighbor;
    }
    
    public ActorRef getNeighbor() {
        return neighbor;
    }

}
