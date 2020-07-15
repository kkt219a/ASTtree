package midterm;

class While extends Statement {
	// While = Expression test; Statement body
	Expression test;
	Statement body;
	While (Expression t, Statement b) { test = t; body = b; }
	void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("While: ");
		test.Print(lev+1);
		body.Print(lev+1);
	}
}