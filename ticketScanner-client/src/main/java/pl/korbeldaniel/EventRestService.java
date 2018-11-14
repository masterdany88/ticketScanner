package pl.korbeldaniel;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface EventRestService extends RestService {
    @GET
    @Path("api/event/")
    public void getByDateAndPin(EventRestRequest request, 
                      MethodCallback<List<Event>> callback);

}
