package pl.korbeldaniel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.REST;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwidgets.api.leaflet.QrScanner;
import com.gwidgets.api.leaflet.QrTest;

import elemental2.dom.DomGlobal;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {
	private int pin;
	private int selectedEventId;
	private QrScanner scanner;
	private EventRestService service;
	private boolean isCameraStarted;
	protected List<Ticket> currentEventTickets;
	
	public void onModuleLoad() {
		initPinRequest();
		initRest();
		//initFlashButton();
		initScanner();
		
	}
	
	private void initPinRequest() {
		VerticalPanel pinRequestBox = new VerticalPanel();
		TextBox pinTextBox = new TextBox();
		Button pinSubmitButton = new Button("Zatwierdź");
		pinSubmitButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pin = Integer.valueOf(pinTextBox.getValue());
				initEvents();
				pinRequestBox.clear();
			}
		});
		pinRequestBox.add(new Label("Proszę wprowadzić PIN"));
		pinRequestBox.add(pinTextBox);
		pinRequestBox.add(pinSubmitButton);
		RootPanel.get().add(pinRequestBox);
	}
	
	private void initRest() {
    	Defaults.setAddXHttpMethodOverrideHeader(false);
	    service = GWT.create(EventRestService.class);
	}

	private void initEvents() {
	    service.getByPinAndDate(pin, "2018-11-15", getEventHandler());
	}
	
	private MethodCallback<EventRestResponse> getEventHandler() {
		return new MethodCallback<EventRestResponse>() {
			@Override
			public void onSuccess(Method method, EventRestResponse response) {
				if(response != null) {
					for(Event e: response.events) {
						DomGlobal.console.log(e.toString());
						Button link = new Button(e.name + " " + e.city + " " + e.place);
						link.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								selectedEventId = e.id;
								initTicketScanner();
							}
						});
						RootPanel.get("topBar").add(link);
					}
				}
			}
			@Override
			public void onFailure(Method method, Throwable exception) {
				Window.alert("Failed to get events: "  + exception.getMessage());
			}
		};
		
	}
	
	private void initTicketScanner() {
		fetchTicketsList();
	}
	
	private void fetchTicketsList() {
		service.getTicketsByEventId(selectedEventId, getTicketHandler());
	}

	private MethodCallback<TicketRestResponse> getTicketHandler() {
		return new MethodCallback<TicketRestResponse>() {
			@Override
			public void onFailure(Method method, Throwable exception) {
				Window.alert("Failed to get tickets: "  + exception.getMessage());
			}
			@Override
			public void onSuccess(Method method, TicketRestResponse response) {
				RootPanel.get("topBar").clear();
				currentEventTickets = response.tickets;
				for (Ticket ticket : response.tickets) {
					Label label = new Label(ticket.toString());
					if(ticket.checked) {
						label.getElement().getStyle().setBackgroundColor("GREEN");
					}
					RootPanel.get("topBar").add(label);
				}
				initCameraButton();
			}
		};
	}

	private void initCameraButton() {
		Button cameraButton = new Button("Uruchom kamerę");
		cameraButton.setHeight("100px");
		cameraButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(isCameraStarted) {
					scanner.stop();
					isCameraStarted = false;
					cameraButton.setText("Uruchom kamerę");
				} else {
					scanner.start();
					isCameraStarted = true;
					cameraButton.setText("Zatrzymaj kamerę");
				}
			}
		});
		RootPanel.get("topBar").add(cameraButton);
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
				"1200");
	}

	private void handleScannedQrCode(String result) {
		Audio audio = Audio.createIfSupported();
		audio.setSrc("/js/ticketScanner/audio/scan.mp3");
		audio.play();
		DomGlobal.console.log("decoded qa code:", result);
		RootPanel.get("bottomBar").add(new Label(getCurrentTime() + " " + result));
		Ticket tempTicket = new Ticket(result);
		if(currentEventTickets.contains(tempTicket)) {
			Window.alert("Zatwierdzono bilet: " + currentEventTickets.get(currentEventTickets.indexOf(tempTicket)).ownerFirstame);
		}
	}

	private String getCurrentTime() {
		DateTimeFormat dt1 = DateTimeFormat.getFormat("HH:mm:ss.S");
		return dt1.format(new Date());
	}
	
}
