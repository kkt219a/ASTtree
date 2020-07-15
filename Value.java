package midterm;

abstract class Value extends Expression {
	protected Type type;
	protected boolean undef = true;
	boolean isUndef( ) { return undef; }
	Type type ( ) { return type; }
}
