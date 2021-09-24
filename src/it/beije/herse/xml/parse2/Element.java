package it.beije.herse.xml.parse2;

import java.util.List;

;

public class Element extends Documento {
	private String tag;
	private String val;

	public void setTag(String tag){
		this.tag = tag;
	}
	public void setVal(String val){
		this.val = val;
	}
	public String getTagName(){
		return this.tag;
	}
	public String getTextTag(){
		return this.val;
	}
		

}