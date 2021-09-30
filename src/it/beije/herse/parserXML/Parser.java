package it.beije.herse.parserXML;

public class Parser {

	private Element parseElement(String str) {

		String elem;
		Element start = new Element();

		if(str.contains("<") && str.contains(">")) {

			if(str.indexOf("<") < str.indexOf(">")) {

				elem = str.substring(str.indexOf("<")+1,str.indexOf(">"));

				if(elem.charAt(0) == '/') { // </contatto>
					start = null;					
				} else if(elem.charAt(elem.length()-1) == '/') { //	<contatto/>
					start.setTagName(elem.substring(0, elem.length()-1));
				} else {   // <contatto>

					start.setTagName(elem);
					str = str.replace("<" + elem + ">","");

					if(!(str.contains("</" + elem + ">")))
						start = null;
					
				}
				
			} else {
				start = null;
			}

		} else
			start = null;

		return start;

	}

	private Node parseNode(String str) {
		
		String s = str;
		Node node = new Node();
		Element e = parseElement(s);
		node.setElement(e);
		s = s.replace("<" + e.getTagName() + ">","").replace("</" + e.getTagName() + ">","");
		
		return node;
		
	}
	
	//private Document parseDocument(String str) {}

	public String parseComment(String str) {

		String init = "<!--";
		String end = "-->";

		while(str.contains(init)) {

			StringBuilder s = new StringBuilder();

			if(str.contains(end) && str.indexOf(init) < str.indexOf(end)) {

				s.append(str.substring(0, str.indexOf(init)));
				s.append(str.substring(str.indexOf(end)+3, str.length()));

				str = s.toString();

			} else {
				return null;
			}

		}

		return str.replace("  "," ");

	}

	public static void main(String[] args) {

		Parser p = new Parser();

		Element e = p.parseElement("<contatti> <contatto>ciao</contatto> <contatto>ciao2</contatto> </contatti>");

		if(e != null)
			System.out.println(e.getTagName());
		else
			System.out.println("non funziona un cazzo");

	}

}
