package pl.korbeldaniel;

import java.util.ArrayList;
import java.util.List;

public class TicketRestRequest {
	private List<Integer> tickets = new ArrayList<>();
	
	public TicketRestRequest() {
		
	}

	public TicketRestRequest(Integer ticketId) {
		this.tickets.add(ticketId);
	}
	
	public List<Integer> getTickets() {
		return tickets;
	}

	public void setTickets(List<Integer> tickets) {
		this.tickets = tickets;
	}


}
