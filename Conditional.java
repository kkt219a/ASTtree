package midterm;

class Conditional extends Statement {
	// Conditional = Expression test; Statement thenbranch, elsebranch
	Expression test;
	Statement thenbranch, elsebranch;
	Conditional (Expression t, Statement tp) {
		test = t; thenbranch = tp; elsebranch = null;
	}
	Conditional (Expression t, Statement tp, Statement ep) {
		test = t; thenbranch = tp; elsebranch = ep;
	}
	void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Conditional: ");
		test.Print(lev+1);
		thenbranch.Print(lev+1);
		if(elsebranch!=null)
			elsebranch.Print(lev+1);
	}
}