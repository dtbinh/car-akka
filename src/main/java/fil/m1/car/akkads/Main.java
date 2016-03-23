package fil.m1.car.akkads;

import java.util.Arrays;
import java.util.LinkedList;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("MyDataSendingSystem");
        
        final DataSendingHistory history = new DataSendingHistory();

        final ActorRef node1 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class, history), "Node1");
        final ActorRef node2 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class, history), "Node2");
        final ActorRef node3 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class, history), "Node3");
        final ActorRef node4 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class, history), "Node4");
        final ActorRef node5 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class, history), "Node5");
        final ActorRef node6 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class, history), "Node6");
        
        node1.tell(new SetHierarchyMessage(null, Arrays.asList(node2, node5)), ActorRef.noSender());
        node2.tell(new SetHierarchyMessage(node1, Arrays.asList(node3, node4)), ActorRef.noSender());
        node5.tell(new SetHierarchyMessage(node1, Arrays.asList(node6)), ActorRef.noSender());
        node3.tell(new SetHierarchyMessage(node2, new LinkedList<ActorRef>()), ActorRef.noSender());
        node4.tell(new SetHierarchyMessage(node2, new LinkedList<ActorRef>()), ActorRef.noSender());
        node6.tell(new SetHierarchyMessage(null, new LinkedList<ActorRef>()), ActorRef.noSender());

        node2.tell(new DataMessage("Hello"), ActorRef.noSender());
        
        final DataSendingHistoryRenderer historyRenderer = new DataSendingHistoryRenderer();
        
        //System.out.println(historyRenderer.renderHistory(history));
        
        //actorSystem.shutdown();
    }


}
