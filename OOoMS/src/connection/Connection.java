package connection;

import com.sun.star.uno.XComponentContext;

public abstract class Connection {
	public abstract void use ();
	public abstract XComponentContext getXComponentContext ();
	protected void comment ( String s ) {
		new oooms.Comment ().log ( s );	// to_do
	}
}