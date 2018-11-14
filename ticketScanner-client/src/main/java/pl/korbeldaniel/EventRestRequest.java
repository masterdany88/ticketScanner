package pl.korbeldaniel;

public class EventRestRequest {
	private String pin;
	private String date;
	
	public EventRestRequest() {}
	public EventRestRequest(String pin) {
		super();
		this.pin = pin;
		this.date = "2018-11-15";
	}

	public String getPin() {
		return pin;
	}

	public String getDate() {
		return date;
	}
}
