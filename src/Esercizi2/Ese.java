package Esercizi2;
public class Ese {
	int i=0;

	String substring(String s, int beginIndex) {

		String s2=new String();
		for(;beginIndex<s.length();beginIndex++) {
			s2+=s.charAt(beginIndex);
		}
		return s2;
	}

	String substring(String s, int beginIndex, int endIndex) {
		String s2=new String();
		for(;beginIndex<endIndex;beginIndex++) {
			s2+=s.charAt(beginIndex);
		}
		return s2;
	}

	String toLowerCase(String s) {
		String s2=new String();
		for(i=0;i<s.length();i++) 
			if(s.charAt(i)>64&&s.charAt(i)<91)
				s2+=(char)(s.charAt(i)+32);
			else
				s2+=s.charAt(i);
		return s2;
	}

	String toUpperCase(String s) {
		String s2=new String();
		for(i=0;i<s.length();i++) 
			if(s.charAt(i)>96&&s.charAt(i)<123)
				s2+=(char)(s.charAt(i)-32);
			else
				s2+=s.charAt(i);
		return s2;
	}

	boolean equals(String s1, String s2) {
		if(s1.length()!=s2.length())
			return false;
		else
			for(i=0;i<s1.length();i++) {
				if(s1.charAt(i)!=s2.charAt(i))
					return false;
			}
		return true;		
	}
	
	boolean equalsIgnoreCase(String s1, String s2) {
		s1=s1.toLowerCase();
		s2=s2.toLowerCase();
		return equals(s1,s2);
	}
	
	boolean contains(String s, String str) {
		int j,k;
		for(i=0;i<str.length();i++) {
			if(str.length()>=(i+s.length()))
					if(str.charAt(i)==s.charAt(0)) {
						k=i;
						for(j=0;j<s.length();j++) {
							if(str.charAt(k)==s.charAt(j)&&(j+1)==s.length())
								return true;
							k++;
						}
					}
		}
		return false;
	}
	
	boolean startsWith(String s, String prefix) {
		if(s.charAt(0)==prefix.charAt(0)&&s.length()>=prefix.length())
			for(i=1;i<prefix.length();i++)
					if(prefix.length()==i+1&&s.charAt(i)==prefix.charAt(i))
									return true;
		return false;
	}
	
	
	boolean endsWith(String s, String suffix) {
		int j;
		for(i=0;i<s.length();i++) {
				if(s.charAt(i)==suffix.charAt(0)&&s.length()==(i+suffix.length())) {
					for(j=0;j<suffix.length();j++) {
						if(s.charAt(i)==suffix.charAt(j)&&(i+1)==s.length())
							return true;
						i++;
					}
				}
		}
		return false;
		
		
	}
	String replace(String s, char oldChar, char newChar) {
		String s2=new String();
		for(i=0;i<s.length();i++) {
			if(s.charAt(i)==oldChar)
				s2+=newChar;
			s2+=s.charAt(i);
		}
		return s2;
	}
	//String replace(String s, String oldChar, String newChar)
	//String trim(String s)
	
	
	
	
	
	
	
	
}