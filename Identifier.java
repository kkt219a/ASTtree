package midterm;

public class Identifier extends Expression {
	String value;
	Identifier(){}
	Identifier(String s){ value=s; }
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		System.out.println("Identifier: "+value);
	}
}
