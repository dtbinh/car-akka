package fil.m1.car.akkads.parse;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Identify;
import akka.actor.Props;
import fil.m1.car.akkads.actor.TreeNodeDataSendingActor;
import fil.m1.car.akkads.message.DataMessage;
import fil.m1.car.akkads.message.SetHierarchyMessage;

public class TreeXMLFileParser {

    public RunInformations readRun(String xmlFile) throws JDOMException, IOException {
        final RunInformations runInformations = new RunInformations();
        final ActorSystem actorSystem = ActorSystem.create();
        
        final SAXBuilder saxBuilder = new SAXBuilder();
        final Document doc = saxBuilder.build(xmlFile);
        final Element rootElement = doc.getRootElement();

        final Element configurationsElement = rootElement.getChild("configurations");
        final Element nodesElement = rootElement.getChild("nodes");
        final Element messagesElement = rootElement.getChild("messages");

        // Configuration elements
        final List<Element> configurationElements = configurationsElement.getChildren("configuration");
        final Element[] configurationElementsArray = configurationElements.toArray(new Element[configurationElements.size()]);
        final Config[] configs = new Config[configurationElements.size()];

        for (int i = 0; i < configurationElementsArray.length; i++) {
            final String configurationFilename = configurationElementsArray[i].getText();
            final Config config = ConfigFactory.parseFile(new File(configurationFilename));
            
            
        }
        
        // Node elements
        final List<Element> nodeElements = nodesElement.getChildren("node");
        final Element[] nodeElementsArray = nodeElements.toArray(new Element[nodeElements.size()]);

        final ActorRef[] actors = new ActorRef[nodeElements.size()];

        for (int i = 0; i < nodeElementsArray.length; i++) {
            final Element nodeElement = nodeElementsArray[i];
            final String name = nodeElement.getChildText("name");
            final String address = nodeElement.getChildText("address");
            //actors[i++] = actorSystem.actorSelection("akka.tcp://" + address);
            //actors[0].tell(new Identify, sender);
        }
        //

        // messages
        final List<Element> hierarchyElements = messagesElement.getChildren("hierarchy");
        final List<Element> dataElements = messagesElement.getChildren("data");
        final List<SetHierarchyMessage> hierarchyMessages = new LinkedList<SetHierarchyMessage>();
        final List<DataMessage> dataMessages = new LinkedList<DataMessage>();

        hierarchyElements.forEach(hierarchy -> {
            final ActorRef parent = actors[Integer.parseInt(hierarchy.getChild("parent").getText()) - 1];
            final List<ActorRef> children = new LinkedList<ActorRef>();
            final Element childrenElement = hierarchy.getChild("children");
            final List<Element> childElements = childrenElement.getChildren("child");
            childElements.forEach(childElement -> children.add(actors[Integer.parseInt(childElement.getText()) - 1]));
            
            //hierarchyMessages.add(new SetHierarchyMessage(parent, children));
        });

        dataElements.forEach(data -> {
            final ActorRef initiator = actors[Integer.parseInt(data.getChild("initiator").getText()) - 1];
            dataMessages.add(new DataMessage(data.getChild("content").getText(), initiator));
        });

        //runInformations.setActors(actors);
        runInformations.setHierarchyMessages(hierarchyMessages);
        runInformations.setDataMessages(dataMessages);
        return runInformations;
    }

    public static void main(String[] args) throws JDOMException, IOException {
        final RunInformations runInformations = new TreeXMLFileParser().readRun("runExample.xml");
        System.out.println(runInformations.getActors());

    }

}
