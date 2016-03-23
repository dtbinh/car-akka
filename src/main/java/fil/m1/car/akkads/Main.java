package fil.m1.car.akkads;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {
        final ActorSystem actorSystem = ActorSystem.create("MyDataSendingSystem");

        final ActorRef node1 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node1");
        final ActorRef node2 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node2");
        final ActorRef node3 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node3");
        final ActorRef node4 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node4");
        final ActorRef node5 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node5");
        final ActorRef node6 = actorSystem.actorOf(Props.create(NodeDataSendingActor.class), "Node6");

        node1.tell(new AddChildMessage(node2), node1);
        node1.tell(new AddChildMessage(node5), node1);
        node2.tell(new AddChildMessage(node3), node2);
        node2.tell(new AddChildMessage(node4), node2);
        node5.tell(new AddChildMessage(node6), node5);

        node1.tell(new DataMessage("Hello"), ActorRef.noSender());

        //actorSystem.shutdown();
    }


}
