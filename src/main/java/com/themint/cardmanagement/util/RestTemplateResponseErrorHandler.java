package com.themint.cardmanagement.util;


import com.themint.cardmanagement.common.exception.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {
        log.error("Response error: {} {}", httpResponse.getStatusCode(), httpResponse.getStatusText());
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(final ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
            throw new ServerErrorException("Unable to connect to vendors server");
        }
    }
}

