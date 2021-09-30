package it.beije.herse.xml.parse.B;

public class Node{
	
	private boolean isElement;
	private String textContent;
	
	public boolean isElement() {
		return isElement;
	}
	public void setElement(boolean isElement) {
		this.isElement = isElement;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	@Override
	public String toString() {
		return "Node [isElement=" + isElement + ", textContent=" + textContent + "]";
	}

	
}
