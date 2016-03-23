package fil.m1.car.akkads.message;

import java.util.List;

import akka.actor.ActorRef;

public class SetHierarchyMessage implements Message {
    
    private ActorRef parent;
    private List<ActorRef> children;
    
    public SetHierarchyMessage(ActorRef parent, List<ActorRef> children) {
        this.parent = parent;
        this.children = children;
    }
    
    public ActorRef getParent() {
        return parent;
    }
    
    public List<ActorRef> getChildren() {
        return children;
    }

}