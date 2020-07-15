package midterm;

import java.util.ArrayList;

public class Block extends Statement {
	// Block = Statement*
	public ArrayList<Statement> members= new ArrayList<Statement>();
	Block(ArrayList<Statement> a){
		members=a;
	}
	void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Block: ");
		for(int i=0;i<members.size();i++) {
			members.get(i).Print(lev+1);
		}
	}
}