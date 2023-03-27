package helloworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import software.amazon.lambda.powertools.metrics.Metrics;
import software.amazon.lambda.powertools.tracing.Tracing;

import static software.amazon.lambda.powertools.tracing.CaptureMode.DISABLED;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Tracing(captureMode = DISABLED)
    @Metrics(captureColdStart = true)
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        LambdaLogger logger = context.getLogger();
        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        String body = input.getBody();
        logger.log("REQUEST: " + body);

        QuoteRequest quoteRequest;
        try {
            quoteRequest = mapper.readValue(body, QuoteRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);



        String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }");
/*
                    mapper.writeValueAsString(quoteRequest));
*/

        return response
                .withStatusCode(200)
                .withBody(output);
    }
    @Tracing(namespace = "getPageContents")
    private String getPageContents(String address) throws IOException {
        URL url = new URL(address);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
