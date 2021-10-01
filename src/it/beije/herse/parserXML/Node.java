package it.beije.herse.parserXML;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Element elem;
	private List<Node> childs = new ArrayList<Node>();
	private Node next;
	private Node before;
	
	protected void setElement(Element e) {
		elem = e;
	}
	
	public Element getElement() {
		return elem;
	}

	public Node getNext() {
		return next;
	}
	
	protected void setNext(Node next) {
		this.next = next;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public Node getBefore() {
		return before;
	}
	
	protected void setBefore(Node before) {
		this.before = before;
	}
	
	public List<Node> getChildNodes(){
		return childs;
	}
	
	public List<Element> getChildElements(){
		
		List<Element> list = new ArrayList<Element>();
		
		for(Node n : childs)
			list.add(n.getElement());
			
		return list;
	}
	
	protected void addChild(Node child) {
		childs.add(child);
	}

	@Override
	public String toString() {
		return "Node [elem=" + elem + ", childs=" + childs + ", next=" + next + ", before=" + before + "]";
	}

	
}
