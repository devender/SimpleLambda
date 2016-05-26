package example;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<Request, Response> {
    private static final Logger LOGGER = Logger.getLogger(Hello.class);

    public Response handleRequest(Request request, Context context) {
        LOGGER.debug("starting lambda...with request " + request);
        Response response = new Response();
        String msg = String.format("Hello %s, %s", request.getFirstName(), request.getLastName());
        response.setGreeting(msg);
        LOGGER.debug("response is " + response);
        return response;
    }

}