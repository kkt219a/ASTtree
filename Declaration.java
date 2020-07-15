package midterm;

public class Declaration {
	Type type;
	Identifier id;
	Declaration(Type t, Identifier i){ type=t; id=i; }
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		System.out.println("Declaration: ");
		type.Print(lev+1);
		id.Print(lev+1);
	}
}
