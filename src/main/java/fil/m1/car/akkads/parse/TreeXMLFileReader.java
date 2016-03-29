package fil.m1.car.akkads.parse;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class TreeXMLFileReader {
	
	public static void main(String[] args) {
		Document doc = null;
		final SAXBuilder saxBuilder = new SAXBuilder();
		try {
			doc = saxBuilder.build(new File("test.xml"));
		}
		catch(Exception e) {}
		
		final Element rootElement = doc.getRootElement();
		final Element messageElement = rootElement.getChild("message");
		final Element nodesElement = rootElement.getChild("nodes");
		
		final List<Element> nodeElements = nodesElement.getChildren("node");
		System.out.println(nodeElements.get(0).getChildText("confFile"));
	}

}
