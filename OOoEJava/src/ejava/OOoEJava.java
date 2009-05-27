package ejava;

import core.Binding;

public class OOoEJava extends FileEJava {
	static final String Revision =
		"$ date_: 2_009_-_05_-_23 18_:_43_:_07 +_0_900 $";
	private OOoCompiler compiler;
	public static String version () {
		String date = Revision.split ( " " ) [ 2 ];
		return String.format ( "OOoEJava.java [ 0.0.1 %s ]", date );
	}
	public OOoEJava ( String filename ) {
		super ( filename );
		compiler = new OOoCompiler ();
		source = compiler.compile ( filename );
	}
	public Object result ( Binding b ) {
		super.result ( b );
		String loadURL = b.unbind ( "loadURL" );
		String filtername = b.unbind ( "filtername" );
		String saveURL = b.unbind ( "saveURL" );
		compiler.eachToken ( b );
		// fix_me
		return null;
	}
}
