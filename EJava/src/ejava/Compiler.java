package ejava;

import core.Binding;

public abstract class Compiler implements TokenVisitor {
	private Scanner scanner;
	private Binding binding;
	public < T > T compile ( String s ) {
		Buffer < T > out = new Buffer < T > ( this );
		scanner = make_scanner ( s );
		scanner.scan ();
		return out.getScript ();
	}
	protected abstract Scanner make_scanner ( String source );
	protected void eachToken ( Binding b ) {
		binding = b;
		scanner.eachToken ( this );
	}
}
