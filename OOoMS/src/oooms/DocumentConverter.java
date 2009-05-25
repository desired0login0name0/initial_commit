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
	public DocumentConverter ( XComponentLoader document ) {
		this.document = document;
	}
	public void convert ( String loadURL, String filtername, String saveURL ) {
		comment ( "converting a document" );
		XComponent xComponent;
		PropertyValue [] loadProperties = new PropertyValue [ 1 ];
		PropertyValue asTemplate = new PropertyValue ();
		asTemplate.Name = "AsTemplate";
		asTemplate.Value = Boolean.TRUE;
		loadProperties [ 0 ] = asTemplate;
		try {
			xComponent = document.loadComponentFromURL (
				loadURL.toString (), "_blank", 0, loadProperties );
		} catch ( IOException exception ) {
			throw new OOoMSException ( exception );
		} catch ( IllegalArgumentException exception ) {
			throw new OOoMSException ( exception );
		}
		XStorable xStorable = ( XStorable )
			UnoRuntime.queryInterface ( XStorable.class, xComponent );
		PropertyValue [] propertyValue = new PropertyValue [ 2 ];
		propertyValue [ 0 ] = new PropertyValue ();
		propertyValue [ 0 ].Name = "Overwrite";
		propertyValue [ 0 ].Value = Boolean.TRUE;
		propertyValue [ 1 ] = new PropertyValue ();
		propertyValue [ 1 ].Name = "FilterName";
		propertyValue [ 1 ].Value = filtername;
		try {
			xStorable.storeAsURL ( saveURL.toString (), propertyValue );
		} catch ( IOException exception ) {
			throw new OOoMSException ( exception );
		}
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
