package pl.korbeldaniel;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Dispatcher;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.dispatcher.DefaultDispatcher;
import org.fusesource.restygwt.client.dispatcher.DefaultFilterawareDispatcher;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;

public class PinDispatcher implements Dispatcher{
    public static final PinDispatcher INSTANCE = new PinDispatcher();

    @Override
    public Request send(Method method, RequestBuilder builder) throws RequestException {
        builder.setHeader("STANDUP-PIN", "3084214");
        return builder.send();
        //return send(method, builder);
    }

}