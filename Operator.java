package midterm;

public class Operator {
	// ArithmeticOp = + | - | * | /
	final static String PLUS = "+";
	final static String MINUS = "-";
	final static String TIMES = "*";
	final static String DIV = "/";
	String val;
	Operator (String s) { val = s; }
	public String toString( ) { return val; }
	public boolean equals(Object obj) { return val.equals(obj); }
	public void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Operator: "+val);
	}
}
