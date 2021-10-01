package it.beije.herse.parserXML;

import java.util.HashMap;

public class Element {
	
	private String tag;
	private String text;
	private HashMap<String,String> attributes = new HashMap<String,String>();
	
	public HashMap<String,String> getAllAttributes(){
		return attributes;
	}
	
	protected void addAttribute(String name, String value) {
		attributes.put(name,value);
	}
	
	public String getTagName() {
		return tag;
	}
	
	protected void setTagName(String tag) {
		this.tag = tag;
	}

	public String getTextContent() {
		return text;
	}
	
	protected void setTextContent(String text) {
		this.text = text;
	}

	public String getAttribute(String attribute) {
		return attributes.get(attribute);
	}

	public boolean equals(String tag) {
		return this.tag.equals(tag);
	}

	@Override
	public String toString() {
		return "Element [tag=" + tag + ", text=" + text + ", attributes=" + attributes + "]";
	}
	
	
	
}
