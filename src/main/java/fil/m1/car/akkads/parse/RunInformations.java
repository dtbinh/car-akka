package fil.m1.car.akkads.parse;

import java.util.List;

import akka.actor.ActorRef;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.SetHierarchyMessage;

public class RunInformations {
    
    private ActorRef[] actors;
    private List<SetHierarchyMessage> hierarchyMessages;
    private List<DataMessage> dataMessages;
    
    public RunInformations() {
        actors = null;
        hierarchyMessages = null;
        dataMessages = null;
    }
    
    public ActorRef[] getActors() {
        return actors;
    }
    
    public List<SetHierarchyMessage> getHierarchyMessages() {
        return hierarchyMessages;
    }
    
    public List<DataMessage> getDataMessages() {
        return dataMessages;
    }
    
    public void setActors(ActorRef[] actors) {
        this.actors = actors;
    }
    
    public void setHierarchyMessages(List<SetHierarchyMessage> setHierarchyMessages) {
        this.hierarchyMessages = setHierarchyMessages;
    }
    
    public void setDataMessages(List<DataMessage> dataMessages) {
        this.dataMessages = dataMessages;
    }

}
