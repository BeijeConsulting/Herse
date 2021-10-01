package it.beije.herse.parserXML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

	private static boolean root = false;
	private Document document = new Document();

	private Element parseElement(String str) throws XMLexception {

		String elem;
		Element start = new Element();
		if (str.contains("<") && str.contains(">")) {
			if (str.indexOf("<") < str.indexOf(">")) {

				elem = str.substring(str.indexOf("<") + 1, str.indexOf(">"));

				if (elem.charAt(0) == '/') { // </contatto>
					start = null;
				} else if (elem.charAt(elem.length() - 1) == '/') { // <contatto/>
					start.setTagName(elem.substring(0, elem.length() - 1));
					start.setTextContent(null);
				} else { // <contatto>
					//str = str.replace("<" + elem + ">", "");
					str = str.substring(str.indexOf(">")+1);
					String text = str.substring(0, str.indexOf('<'));
					start.setTagName(elem);
					start.setTextContent(text);
					
					if (!(str.contains("</" + elem + ">")) && str != "") {
						start = null;
						throw new XMLexception();
					}
					//str = str.replace("</" + elem + ">", "");
					str = str.substring(text.length());
					//System.out.println(str);

				}

				/*
				 * String str2 = str; List<Node> nodi = new ArrayList<>(); if(str2!="") { Node n
				 * = new Node(); n.setElement(parseElement(str2)); nodi.add(n);
				 * document.getRootNode().addChild(n); }
				 */

			} else {
				start = null;
				throw new XMLexception();
			}

		} else {
			start = null;
		}

		return start;

	}

	private Node parseNode(String str) throws XMLexception {
		Node node = new Node();
		String s = str;

		Element e = parseElement(s);

		if (e != null && e.getTagName() != null) {
			node.setElement(e);

			if (s != "") {
				
				s = s.replace("<" + e.getTagName() + ">", "").replace("</" + e.getTagName() + ">", "");
//				s = s.substring(s.indexOf(">")+1);
//				String attributo = s.substring(0, s.indexOf("</"));
//				s = s.substring(0, s.indexOf(">")+1);
//				s = attributo + s;
			}

			if (!root) {
				root = true;
				document.addNode(node);
				// check
			}
			
			//COMPLETAMENTE DA FIXARE
			if (parseElement(s) != null) {
				int numerofigli = s.split("<" + parseElement(s).getTagName() + ">").length;
				for (int i = 1; i < numerofigli; i++) {
//				if (s.contains("<") && s.contains(">")) {
//					s = s.substring(0, s.indexOf(">") + 1);
//					s = s.substring(s.indexOf("<"), s.indexOf(">") + 1);
//				}
//					if (parseElement(s) != null)
//					s  = s.replace("<" + parseElement(s).getTagName() + ">", "");
					node.addChild(parseNode(s));
				}
			}

			if (s != "") {
				parseNode(s);
			}

		}

		return node;
	}

	// private Document parseDocument(String str) {}

	public String parseComment(String str) {

		String init = "<!--";
		String end = "-->";

		while (str.contains(init)) {

			StringBuilder s = new StringBuilder();

			if (str.contains(end) && str.indexOf(init) < str.indexOf(end)) {

				s.append(str.substring(0, str.indexOf(init)));
				s.append(str.substring(str.indexOf(end) + 3, str.length()));

				str = s.toString();

			} else {
				return null;
			}

		}

		return str.replace("  ", " ");

	}

	public static void main(String[] args) throws XMLexception {

		Parser p = new Parser();

		Element e = p.parseElement("<contatti> <contatto>ciao</contatto> <contatto>ciao2</contatto> </contatti>");

		if (e != null) {
			System.out.println(e.getTagName());
			System.out.println();
			Node n = p.parseNode("<contatti><contatto>ciao</contatto> <contatto>ciao2</contatto> </contatti>");
			System.out.println(n);

		} else {
			System.out.println("non funziona");

		}

	}
}
