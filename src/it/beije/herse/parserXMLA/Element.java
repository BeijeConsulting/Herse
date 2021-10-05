package it.beije.herse.parserXMLA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Element /*extends Node*/ {

	private String tagName;
	private String textValue;
	private Map<String, String> attributes = new HashMap<String,String>();
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
	
	public Map<String,String> getAttributes(){
		return attributes;
	}
	
	public void addAttribute(String key, String value) {
		attributes.put(key, value);
	}
	
	public List<Element> getChildNodes() {
		return childNodes;
	}
	
	public void setChildNodes(List<Element> childNodes) {
		this.childNodes = childNodes;
	}
	
}
