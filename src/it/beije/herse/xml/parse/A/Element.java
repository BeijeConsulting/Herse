package it.beije.herse.xml.parse.A;

import java.util.List;
import java.util.Map;

public class Element extends Node {

	private String tagName;
	private String textValue;
	//private Map<String, String> attributes;
	private List<Element> childNodes;
	
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
	
	public List<Element> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<Element> childNodes) {
		this.childNodes = childNodes;
	}
	
}
