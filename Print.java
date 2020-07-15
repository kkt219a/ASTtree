package midterm;

public class Print extends Statement {
	Expression e;
	Print(Expression e2){ e= e2; }
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		System.out.println("Print: ");
		e.Print(lev+1);
	}
}
