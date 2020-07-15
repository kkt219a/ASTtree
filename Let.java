package midterm;

class Let extends Statement {
	// Let = Declarations decls ; Block body
	Declarations decls;
	Block body;
	Let(Declarations ds, Block b) {
		decls = ds;
		body = b;
	}
	void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Let: ");
		decls.Print(lev+1);
		body.Print(lev+1);
	}
}