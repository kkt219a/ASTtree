package midterm;

public class Type{
	String typevalue;
	Type(){}
	Type(String s){ typevalue=s; }
	void Print(int lev) {
		for(int i=0;i<lev;i++)
			System.out.print("	");
		System.out.println("Type: "+typevalue);
	}
}