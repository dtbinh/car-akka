package fil.m1.car.akkads.message;

import akka.actor.ActorRef;

public class DataMessage implements Message {
    
    private static final long serialVersionUID = 1L;
    private String content;
    private ActorRef initiator;
    
    public DataMessage() {
    }

    public DataMessage(String content, ActorRef initiator) {
        this.content = content;
        this.initiator = initiator;
    }
    
    public String getContent() {
        return content;
    }
    
    public ActorRef getInitiator() {
        return initiator;
    }
    
    @Override
    public String toString() {
        return "message : \"" + content + "\"";
    }
}
