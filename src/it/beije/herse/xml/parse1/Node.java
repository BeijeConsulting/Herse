package it.beije.herse.xml.parse1;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String tagName;
	private String contenuto;
	private List<Node> children = new ArrayList<>();
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	
	public List<Node> getChild() {
		return children;
	}
	public void setChild(List<Node> child) {
		this.children = child;
	}
	public void addChild(Node n) {
		children.add(n);
	}
}
