package midterm;

public class Assignment extends Statement {
	// Assignment = Identifier target; Expression source
	Identifier target;
	Expression source;
	Assignment (Identifier t, Expression e) {
		target = t;
		source = e;
	}
	void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Assignment: ");
		target.Print(lev+1);
		source.Print(lev+1);
	}
}
