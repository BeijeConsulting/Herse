package it.beije.herse.parserXML;

import java.util.ArrayList;
import java.util.List;

public class Document{
	
	private Node rootNode;
	private List<Element> elem = new ArrayList<Element>();
	
	public void addNode(Node node) {
		rootNode = node;
	}
	
	public Element getRootElement(){
		return rootNode.getElement();
	}
	public Node getRootNode() {
		return rootNode;
	}
	
	public List<Element> getElementsByTagName(String tagName) {
		
		List<Element> el = new ArrayList<Element>();
		
		for(int i = 0; i < elem.size(); i++)
			if(elem.get(i).equals(tagName))
				el.add(elem.get(i));
		
		return el;
		
	}
	
}
