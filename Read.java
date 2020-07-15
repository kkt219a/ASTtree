package midterm;

public class Read extends Statement {
	// Assignment = Identifier target; Expression source
	Identifier target;
	Read (Identifier t) {
		target = t;
	}
	void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Read: ");
		target.Print(lev+1);
	}
}
