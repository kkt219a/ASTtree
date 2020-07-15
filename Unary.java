package midterm;

public class Unary extends Expression {
	// Binary = Operator op; Expression term1, term2
	Operator op;
	Expression term1;
	Unary (Operator op2, Expression l) {
		op = op2; term1 = l;
	} // binary
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		System.out.println("Unary: ");
		op.Print(lev+1);
		term1.Print(lev+1);
	}
}