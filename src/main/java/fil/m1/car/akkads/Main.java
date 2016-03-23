package fil.m1.car.akkads;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {
        final ActorSystem actorSystem = ActorSystem.create("MyDataSendingSystem");

        final ActorRef node1 = actorSystem.actorOf(Props.create(DataSendingActor.class), "Node1");
        final ActorRef node2 = actorSystem.actorOf(Props.create(DataSendingActor.class), "Node2");
        final ActorRef node3 = actorSystem.actorOf(Props.create(DataSendingActor.class), "Node3");
        final ActorRef node4 = actorSystem.actorOf(Props.create(DataSendingActor.class), "Node4");
        final ActorRef node5 = actorSystem.actorOf(Props.create(DataSendingActor.class), "Node5");
        final ActorRef node6 = actorSystem.actorOf(Props.create(DataSendingActor.class), "Node6");

        node1.tell(node2, node1);
        node1.tell(node5, node1);
        node2.tell(node3, node2);
        node4.tell(node2, node4);
        node5.tell(node6, node5);

        node1.tell("Hello", ActorRef.noSender());

        //actorSystem.shutdown();
    }


}
