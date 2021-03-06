package pl.korbeldaniel;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

public interface EventRestService extends RestService {

    @GET
    //@Options(dispatcher = PinDispatcher.class)
    @Path("https://test.pl/api/events")
	public void getByPinAndDate(@HeaderParam("STANDUP-PIN") int pin, @QueryParam("date") String date,
                      MethodCallback<EventRestResponse> callback);

    @GET
    //@Options(dispatcher = PinDispatcher.class)
    @Path("https://test.pl/api/events/{eventId}/tickets")
    public void getTicketsByPinAndEventId(@HeaderParam("STANDUP-PIN") int pin, @PathParam("eventId") int eventId, MethodCallback<TicketRestResponse> callback);

    //post api/events/{eventId}/tickets w body ma być json np: {"tickets": [1,2,3...]} cufry to oczywiście id tick
    @POST
    @Path("https://test.pl/api/events/{eventId}/tickets")
    public void updateTicketUsage(@HeaderParam("STANDUP-PIN") int pin, @PathParam("eventId") int eventId, TicketRestRequest ticketRequest, MethodCallback<Void> callback);
}
