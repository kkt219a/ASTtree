package midterm;

public class BoolValue extends Value {
	private String value;
	BoolValue (String v) { value = v; undef = false; }
	public String toString( ) {
		if (undef) return "undef";
		return "" + value;
	}
	public void Print(int lev) {
		for(int i=0;i<lev;i++) {
			System.out.print("	");
		}
		System.out.println("Boolvalue: "+value);
	}
}
