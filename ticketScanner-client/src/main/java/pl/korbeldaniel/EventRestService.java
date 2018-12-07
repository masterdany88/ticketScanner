package pl.korbeldaniel;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

public interface EventRestService extends RestService {

    @GET
    @Options(dispatcher = PinDispatcher.class)
    @Path("https://test.standupbilety.pl/api/events")
	public void getByPinAndDate(@HeaderParam("STANDUP-PIN") int responseCode, @QueryParam("date") String date,
                      MethodCallback<EventRestResponse> callback);

    @GET
    @Options(dispatcher = PinDispatcher.class)
    @Path("https://test.standupbilety.pl/api/events/{eventId}/tickets")
    public void getTicketsByEventId(@PathParam("eventId") int eventId, MethodCallback<TicketRestResponse> callback);

}
