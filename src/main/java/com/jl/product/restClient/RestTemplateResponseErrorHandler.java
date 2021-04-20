package com.jl.product.restClient;

import com.jl.product.exception.ApiException;
import com.jl.product.exception.InvalidArgumentException;
import com.jl.product.exception.RecordNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);



    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        JsonParser jsonParser = new BasicJsonParser();
        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            throw new ApiException(httpResponse.getStatusText());
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            logger.debug("Status code: " + httpResponse.getStatusCode());

            String responseBody = StreamUtils.copyToString(httpResponse.getBody(), Charset.defaultCharset());
            logger.info("*** responseBody : "+responseBody);
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RecordNotFoundException(responseBody);
            }else if(httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new InvalidArgumentException(jsonParser.parseMap(responseBody).get("message").toString());
            }
            throw new ApiException(httpResponse.getStatusText());
        }
    }
}
