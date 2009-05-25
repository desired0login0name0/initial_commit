package oooms;

import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import ejava.Scanner;

public class TrimScanner implements Scanner {
	private XComponentLoader xComponentLoader;
	private XMultiComponentFactory xMultiComponentFactory;
	private XComponentContext xComponentContext;
	public TrimScanner ( String source ) {
		useConnection ();
		comment ( "creating the service of desktop" );
		Object desktop;
		try {
			desktop = xMultiComponentFactory.createInstanceWithContext (
				"com.sun.star.frame.Desktop", xComponentContext );
		} catch ( Exception exception ) {
			throw new OOoMSException ( exception );
		}
		comment ( "querying for the interface xcomponentloader on the desktop" );
		xComponentLoader = ( XComponentLoader )
			UnoRuntime.queryInterface ( XComponentLoader.class, desktop );
	}
	public void scan () {
		// fix_me
	}
	protected void useConnection () {
		comment ( "getting a remote office component context" );
		try {
			xComponentContext = Bootstrap.bootstrap ();
			comment ( "connected to a running office" );
		} catch ( BootstrapException exception ) {
			throw new OOoMSException ( exception );
		}
		comment ( "getting the remote office service manager" );
		xMultiComponentFactory = xComponentContext.getServiceManager ();
		comment ( "remote servicemanager is "
			+ xMultiComponentFactory != null ? "available" : "not available" );
	}
	private void comment ( String s ) {
		new Comment ().log ( s );	// to_do
	}
}
