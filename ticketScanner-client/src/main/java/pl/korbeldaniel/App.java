package pl.korbeldaniel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwidgets.api.leaflet.Circle;
import com.gwidgets.api.leaflet.L;
import com.gwidgets.api.leaflet.QrScanner;
import com.gwidgets.api.leaflet.QrTest;
import com.gwidgets.api.leaflet.options.CircleOptions;

import elemental2.dom.DomGlobal;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
//		Window.alert("TET");
		  Circle circle = L.circle(L.latLng(51.508, 11), new CircleOptions.Builder().build());
		  QrTest test = new QrTest();
		  test.start();
		  QrScanner scanner = new QrScanner(
			  		DomGlobal.document.getElementById("qr-video"),
			  		result -> DomGlobal.console.log("decoded qa code:", result)
			  		);
		  scanner.start();
	}
}
