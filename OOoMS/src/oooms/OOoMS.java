package oooms;

import com.sun.star.frame.XComponentLoader;

public class OOoMS {
	public static void convertDocument (
		XComponentLoader document,
		String loadURL, String filtername, String saveURL ) {
		new DocumentConverter ( document ).convert (
			loadURL, filtername, saveURL );
	}
}
