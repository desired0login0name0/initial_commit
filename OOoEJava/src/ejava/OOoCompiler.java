package ejava;

import oooms.TrimVisitor;

public class OOoCompiler extends Compiler {
	protected Scanner make_scanner ( String source ) {
		return new OOoScanner ( source );
	}
	public void withToken ( Object o ) {
		new TrimVisitor ().withTrim ( o );
	}
}
