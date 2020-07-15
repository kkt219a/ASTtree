package midterm;

public class IntValue extends Value {
	private int value = 0;
	int level=0;
	IntValue (int v) { value = v; undef = false; }
	public String toString( ) {
		if (undef) return "undef";
		return "" + value;
	}
	public void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Intvalue: "+value);
	}
}
