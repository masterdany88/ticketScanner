package pl.korbeldaniel;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwidgets.api.leaflet.QrScanner;
import com.gwidgets.api.leaflet.QrTest;

import elemental2.dom.DomGlobal;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {
	private QrScanner scanner;

	public void onModuleLoad() {
		initEvents();
		initStartCameraButton();
		initStopCameraButton();
		//initFlashButton();
		initScanner();
	}

	private void initEvents() {
		EventRestRequest request = new EventRestRequest("3084214");
		EventRestService service = GWT.create(EventRestService.class);
		service.getByDateAndPin(request, new MethodCallback<List<Event>>() {
			
			@Override
			public void onSuccess(Method method, List<Event> response) {
				// TODO Auto-generated method stub
				for(Event e: response) {
					Window.alert(e.toString());
				}
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				Window.alert("Failed to get events");
			}
		});
	}
	
	private void initStartCameraButton() {
		Button startCameraButton = new Button("Uruchom kamerę");
		startCameraButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				scanner.start();
			}
		});
		RootPanel.get("topBar").add(startCameraButton);
	}
	private void initStopCameraButton() {
		Button stopCameraButton = new Button("Zatrzymaj kamerę");
		stopCameraButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				scanner.stop();
			}
		});
		RootPanel.get("topBar").add(stopCameraButton);
	}

	private void initFlashButton() {
		Button flashButton = new Button("Flash");
		flashButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

			}
		});
		RootPanel.get("topBar").add(flashButton);
	}
	
	private void initScanner() {
		scanner = new QrScanner(DomGlobal.document.getElementById("qr-video"),
				result -> handleScannedQrCode(result),
				"800");
	}

	private void handleScannedQrCode(String result) {
		Audio audio = Audio.createIfSupported();
		audio.setSrc("audio/scan.mp3");
		audio.play();
		DomGlobal.console.log("decoded qa code:", result);
	}
	
}
