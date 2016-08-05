
public class testCode {
public static void main (String []args) {
	String aaaa="q，a d；a";
	String[]aStringArray=aaaa.split("。|；|，| ");
	for(String aString:aStringArray)
		System.out.println(aString);
	
}
}
