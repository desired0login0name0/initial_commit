package oooms;

import com.sun.star.beans.XPropertySet;
import com.sun.star.uno.UnoRuntime;

public class TrimVisitor {
	public void withTrim ( Object fieldMaster ) {
		comment ( "query the xpropertyset interface" );
		XPropertySet xPropertySet = ( XPropertySet )
			UnoRuntime.queryInterface ( XPropertySet.class, fieldMaster );
		comment ( "set the content property" );
		comment ( "insert the column value into field master" );
		xPropertySet.setPropertyValue ( "Content", arg1 );
	}
	protected void comment ( String s ) {
		new Comment ().log ( s );	// to_do
	}
}
