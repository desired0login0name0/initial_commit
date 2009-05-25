package ejava;

import core.Binding;
import core.TopLevel;

public abstract class EJavaBase implements EJava {
	public String filename;
	public EJavaBase () {
		filename = null;
	}
	public void run () {
		run ( TopLevel.Binding );
	}
	public void run ( Binding b ) {
		System.out.print ( result ( b ) );
	}
	public Object result () {
		return result ( TopLevel.Binding );
	}
	public abstract Object result ( Binding b );
}
