package ejava;

import oooms.TrimScanner;

public class OOoScanner implements Scanner {
	private TrimScanner trimScanner;
	public OOoScanner ( String source ) {
		trimScanner = new TrimScanner ( source );
	}
	public void scan () {
		trimScanner.scan ();
	}
	public void eachToken ( TokenVisitor v ) {
		trimScanner.eachTrim ( v );
	}
}
