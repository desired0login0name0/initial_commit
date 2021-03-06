package connection;

import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

import oooms.OOoMSException;

public class BootstrapConnection extends Connection {
	public XComponentLoader xComponentLoader;
	private XComponentContext xComponentContext;
	public void use () {
		XMultiComponentFactory xMultiComponentFactory =
			getRemoteServiceManager ();
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
	public XComponentContext getXComponentContext () {
		return xComponentContext;
	}
	protected XMultiComponentFactory getRemoteServiceManager () {
		comment ( "get the remote service manager" );
		comment ( "getting a remote office component context" );
		try {
			xComponentContext = Bootstrap.bootstrap ();
			comment ( "connected to a running office" );
		} catch ( BootstrapException exception ) {
			throw new OOoMSException ( exception );
		}
		comment ( "getting the remote office service manager" );
		XMultiComponentFactory xMultiComponentFactory =
			xComponentContext.getServiceManager ();
		comment ( "remote servicemanager is "
			+ xMultiComponentFactory != null ? "available" : "not available" );
		return xMultiComponentFactory;
	}
}
