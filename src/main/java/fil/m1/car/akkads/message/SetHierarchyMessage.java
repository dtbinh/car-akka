package fil.m1.car.akkads.message;

import java.util.List;

import akka.actor.ActorRef;

public class SetHierarchyMessage implements Message {
    
    private static final long serialVersionUID = 1L;
    private ActorRef parent;
    private List<ActorRef> children;
    
    public SetHierarchyMessage() {
    }
    
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
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("parent : ");
        builder.append(parent != null ? parent.path().name() + "\n" : "none\n");
        builder.append("children : ");
        if (children != null) {
            children.forEach(child -> builder.append(child.path().name() + " "));
        }
        else {
            builder.append("none");
        }
        return builder.toString();
    }

}
