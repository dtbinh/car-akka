package fil.m1.car.akkads.history;

import java.io.Serializable;

import akka.actor.ActorRef;

public class DataSendingRecord implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private ActorRef sender;
    private ActorRef recipient;
    private Object message;
    
    public DataSendingRecord() {
    }
    
    public DataSendingRecord(ActorRef sender, ActorRef recipient, Object message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }
    
    public ActorRef getSender() {
        return sender;
    }
    
    public ActorRef getRecipient() {
        return recipient;
    }
    
    public Object getMessage() {
        return message;
    }

}
