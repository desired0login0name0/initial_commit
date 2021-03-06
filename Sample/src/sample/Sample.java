package sample;

import java.util.Hashtable;
import java.util.Map;

import core.Binding;

import ejava.EJava;
import ejava.OOoEJava;

public class Sample {
	public static void main ( String [] arguments ) {
		System.out.println ( OOoEJava.version () );
		EJava ejava = new OOoEJava ( "OpenDocument-v1.1.odt" );
		Map < String, String > recipient = new Hashtable < String, String > ();
		recipient.put ( "CommitteeName", "OpenDocument" );
		recipient.put ( "Version", "v1.1" );
		recipient.put ( "Date", "27 May 2_009" );
		Binding b = new Binding ();
		b.bind ( recipient );
		ejava.run ( b );
	}
}
