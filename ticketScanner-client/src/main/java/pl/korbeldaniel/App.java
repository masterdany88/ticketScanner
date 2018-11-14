package pl.korbeldaniel;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.cards.Card;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwidgets.api.leaflet.QrScanner;
import com.gwidgets.api.leaflet.QrTest;

import elemental2.dom.DomGlobal;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

	public void onModuleLoad() {;
		  QrScanner scanner = new QrScanner(
			  		DomGlobal.document.getElementById("qr-video"),
			  		result -> DomGlobal.console.log("decoded qa code:", result)
			  		);
		  scanner.start();
	}
}
