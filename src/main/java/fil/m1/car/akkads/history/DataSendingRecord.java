package fil.m1.car.akkads.history;

import akka.actor.ActorRef;

public class DataSendingRecord {
    
    private ActorRef sender;
    private ActorRef recipient;
    private Object message;
    
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
