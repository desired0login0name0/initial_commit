package ejava;

public class OOoEJava extends FileEJava {
	static final String Revision =
		"$ date_: 2_009_-_05_-_23 18_:_43_:_07 +_0_900 $";
	public static String version () {
		String date = Revision.split ( " " ) [ 2 ];
		return String.format ( "OOoEJava.java [ 0.0.1 %s ]", date );
	}
	public OOoEJava ( String filename ) {
		super ( filename );
		OOoCompiler compiler = new OOoCompiler ();
		source = compiler.compile ( filename );
	}
}
