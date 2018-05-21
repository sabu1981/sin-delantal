/*
 *      File: CORSResponseFilter.java
 *    Author: Horacio Ferro <horacio.ferro@amk-technologies.com>
 *      Date: Mar 22, 2017
 * Copyright: AMK Technologies, S.A. de C.V. 2017
 */
package com.sindelantal.prueba.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Filter used to add the CORS headers to the responses.
 *
 * @author Horacio Ferro &lt;horacio.ferro@amk-technologies.com&gt;
 * @version 1.0.0
 * @since 1.0.0
 */
public class CORSResponseFilter implements ContainerResponseFilter {

    /**
     * Adds the CORS filters to the response.
     *
     * @param requestContext The context of the HTTP request.
     * @param responseContext The context of the HTTP response.
     * @see javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.container.ContainerRequestContext,
     * javax.ws.rs.container.ContainerResponseContext)
     */
    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
            throws IOException {
        final MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
    }

}
