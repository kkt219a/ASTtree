package midterm;

import java.util.ArrayList;

public class Declarations {
	ArrayList<Declaration> decls;
	String name="Declarations";
	Declarations(ArrayList <Declaration> d){ decls=d; }
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		System.out.println("Declarations: ");
		for(int i=0;i<decls.size();i++) {
			decls.get(i).Print(lev+1);
		}
	}
}
