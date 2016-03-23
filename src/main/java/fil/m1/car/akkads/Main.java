package fil.m1.car.akkads;

import java.util.Arrays;
import java.util.LinkedList;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fil.m1.car.akkads.actor.HistoryKeeperActor;
import fil.m1.car.akkads.actor.NodeDataSendingActor;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.RenderHistoryMessage;
import fil.m1.car.akkads.message.SetHierarchyMessage;

public class Main {

    public static void main(String[] args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("JediDataSendingSystem");
        
        final ActorRef historyKeeper = actorSystem.actorOf(Props.create(HistoryKeeperActor.class), "history");
        
        final ActorRef node1 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node1");
        final ActorRef node2 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node2");
        final ActorRef node3 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node3");
        final ActorRef node4 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node4");
        final ActorRef node5 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node5");
        final ActorRef node6 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node6");
        
        node1.tell(new SetHierarchyMessage(null, Arrays.asList(node2, node5)), ActorRef.noSender());
        node2.tell(new SetHierarchyMessage(node1, Arrays.asList(node3, node4)), ActorRef.noSender());
        node5.tell(new SetHierarchyMessage(node1, Arrays.asList(node6)), ActorRef.noSender());
        node3.tell(new SetHierarchyMessage(node2, new LinkedList<ActorRef>()), ActorRef.noSender());
        node4.tell(new SetHierarchyMessage(node2, new LinkedList<ActorRef>()), ActorRef.noSender());
        node6.tell(new SetHierarchyMessage(null, new LinkedList<ActorRef>()), ActorRef.noSender());

        node2.tell(new DataMessage("Keyser Söze"), ActorRef.noSender());
        
        // TODO has to be removed
        Thread.sleep(10000);
        
        historyKeeper.tell(new RenderHistoryMessage(), ActorRef.noSender());
    }


}
