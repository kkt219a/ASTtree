package midterm;

public class Binary extends Expression {
	// Binary = Operator op; Expression term1, term2
	Operator op=null;
	Expression term1, term2;
	Binary (Operator op2, Expression l, Expression r) {
		op = op2; term1 = l; term2 = r;
	} // binary
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		if(op!=null)
			System.out.println("Binary: ");
		term1.Print(lev+1);
		op.Print(lev+1);
		term2.Print(lev+1);
	}
}

