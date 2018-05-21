/*
 *      File: TokenRequestFilter.java
 *    Author: Esau Mendoza <esau.mendoza@amk-technologies.com>
 *      Date: Nov 30, 2017
 * Copyright: AMK Technologies, S.A. de C.V. 2017
 */
package com.sindelantal.prueba.filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter used to add the TOKEN header to the user requests.
 *
 * @author Esau Mendoza &lt;esau.mendoza@amk-technologies.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public class TokenRequestFilter implements ClientRequestFilter {

    /** Resource logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenRequestFilter.class);

    @Override
    public void filter(final ClientRequestContext clientRequestContext) throws IOException {
        LOGGER.info("|| Calling filter ||");
        String token = null;
        Boolean spotifyTokenRequest = null;
        if (clientRequestContext.getClient().getConfiguration().getProperty("token") != null) {
            token = (String) clientRequestContext.getClient().getConfiguration().getProperty("token");
        }
        LOGGER.info("filter 1");
        if (clientRequestContext.getClient().getConfiguration().getProperty("spotifyTokenRequest") != null) {
            spotifyTokenRequest = (boolean) clientRequestContext.getClient().getConfiguration().getProperty("spotifyTokenRequest");
        }
        final String methodName = clientRequestContext.getMethod();
        LOGGER.info("TOKEN::: " + token);
        LOGGER.info("Method name::: " + methodName);
        if (methodName.equals("POST") && spotifyTokenRequest) {
            LOGGER.info("|| Adding header (accpunts/api/token) ||");
            final MultivaluedMap<String, Object> headers = clientRequestContext.getHeaders();
            headers.add("Authorization", "Basic " + token);
        }
        //This means that it is a TRX token
        if (methodName.equals("GET") && 
                (spotifyTokenRequest != null && !spotifyTokenRequest.booleanValue())) {
            LOGGER.info("|| Adding header (api/token)||");
            final MultivaluedMap<String, Object> headers = clientRequestContext.getHeaders();
            headers.add("Authorization", "Bearer " + token);
        }
    }

}
