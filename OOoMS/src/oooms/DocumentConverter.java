package oooms;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.io.IOException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.XComponent;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloseable;

public class DocumentConverter {
	private XComponentLoader document;
	private XStorable xStorable;
	public DocumentConverter ( XComponentLoader document ) {
		this.document = document;
	}
	public void convert ( String loadURL, String filtername, String saveURL ) {
		comment ( "converting a document" );
		XComponent xComponent = newDocumentComponentFromTemplate ( loadURL );
		storeDocumentComponent ( xComponent, saveURL, filtername );
		close ();
	}
	protected XComponent newDocumentComponentFromTemplate ( String loadURL ) {
		comment ( "load a document as template" );
		XComponent xComponent;
		comment ( "define load properties"
			+ "\ntell the office to create a new document from the given file" );
		PropertyValue [] loadProperties = new PropertyValue [ 2 ];
		PropertyValue asTemplate = new PropertyValue ();
		asTemplate.Name = "AsTemplate";
		asTemplate.Value = Boolean.TRUE;
		PropertyValue hidden = new PropertyValue ();
		hidden.Name = "Hidden";
		hidden.Value = Boolean.TRUE;
		loadProperties [ 0 ] = asTemplate;
		loadProperties [ 1 ] = hidden;
		try {
			comment ( "load" );
			xComponent = document.loadComponentFromURL (
				loadURL.toString (), "_blank", 0, loadProperties );
		} catch ( IOException exception ) {
			throw new OOoMSException ( exception );
		} catch ( IllegalArgumentException exception ) {
			throw new OOoMSException ( exception );
		}
		return xComponent;
	}
	protected void storeDocumentComponent (
		XComponent document, String storeURL, String filtername ) {
		comment ( "store a document, using a filter" );
		xStorable = ( XStorable )
			UnoRuntime.queryInterface ( XStorable.class, document );
		PropertyValue [] storeProperties = new PropertyValue [ 2 ];
		storeProperties [ 0 ] = new PropertyValue ();
		storeProperties [ 0 ].Name = "FilterName";
		storeProperties [ 0 ].Value = filtername;
		storeProperties [ 1 ] = new PropertyValue ();
		storeProperties [ 1 ].Name = "Overwrite";
		storeProperties [ 1 ].Value = Boolean.TRUE;
		try {
			xStorable.storeAsURL ( storeURL, storeProperties );
		} catch ( IOException exception ) {
			throw new OOoMSException ( exception );
		}
	}
	protected void close () {
		comment ( "closing a document" );
		XCloseable xCloseable = ( XCloseable )
			UnoRuntime.queryInterface ( XCloseable.class, xStorable );
		if ( xCloseable != null ) {
			try {
				xCloseable.close ( false );
			} catch ( CloseVetoException exception ) {
				throw new OOoMSException ( exception );
			}
		}
		else {
			XComponent xComponent1 = ( XComponent )
				UnoRuntime.queryInterface ( XComponent.class, xStorable );
			xComponent1.dispose ();
		}
	}
	private void comment ( String s ) {
		new Comment ().log ( s );	// to_do
	}
}
