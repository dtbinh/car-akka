package fil.m1.car.akkads.parse;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import fil.m1.car.akkads.actor.TreeNodeDataSendingActor;

public class TreeXMLFileParser {

    public RunInformations readRun(String xmlFile, ActorSystem[] actorSystems) throws JDOMException, IOException {
        final RunInformations runInformations = new RunInformations();
        final SAXBuilder saxBuilder = new SAXBuilder();
        final Document doc = saxBuilder.build(xmlFile);
        final Element rootElement = doc.getRootElement();
        
        final Element nodesElement = rootElement.getChild("nodes");
        final List<Element> nodeElements = nodesElement.getChildren("node");
        final Element[] nodeElementsArray = nodeElements.toArray(new Element[nodeElements.size()]);

        final ActorRef[] actors = new ActorRef[nodeElements.size()];

        for (int i = 0; i < nodeElementsArray.length; i++) {
            final Element nodeElement = nodeElementsArray[i];
            final String name = nodeElement.getChildText("name");
            final Integer actorSystemNumber = Integer.parseInt(nodeElement.getChildText("actorSystem"));
            actors[i++] = actorSystems[actorSystemNumber - 1].actorOf(Props.create(TreeNodeDataSendingActor.class), name);
        }
        
        
        
        Arrays.stream(actors).forEach(System.out::println);
        return runInformations;
    }

    public static void main(String[] args) throws JDOMException, IOException {
        final File configFile1 = new File("conf1.conf");
        final File configFile2 = new File("conf1.conf");

        final Config config1 = ConfigFactory.parseFile(configFile1);
        final Config config2 = ConfigFactory.parseFile(configFile2);

        final ActorSystem actorSystem1 = ActorSystem.create("JediDataSendingSystem1", config1);
        final ActorSystem actorSystem2 = ActorSystem.create("JediDataSendingSystem2", config2);

        new TreeXMLFileParser().readRun("runExample.xml", new ActorSystem[] { actorSystem1, actorSystem2 });

    }

}
