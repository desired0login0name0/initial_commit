package ejava;

public interface Scanner {
	void scan ();
	void eachToken ( TokenVisitor v );
}
