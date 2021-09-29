package it.beije.herse.xml.parse.B;

import java.util.List;
import java.util.Map;

public class Element extends Node {

	private String tagName;
	private String textValue;
	//private Map<String, String> attributes;
	private List<Node> childNodes;

	public Element() {
		super();
		super.setElement(true);
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTextValue() {
		return textValue;
	}
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public List<Node> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<Node> childNodes) {
		this.childNodes = childNodes;
	}
	@Override
	public String toString() {
		return "Element [tagName=" + tagName + ", textValue=" + textValue + ", childNodes=" + childNodes + "]";
		//return this.isElement();
	}


}
