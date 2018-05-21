package com.sindelantal.prueba.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessOperations {
    
    /** Resource LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessOperations.class);
    
    RequestsDelegate requestsDelegate;
    
    /**
     * Constructor
     * @param requestsDelegate
     */
    public BusinessOperations(final RequestsDelegate requestsDelegate){
        this.requestsDelegate = requestsDelegate;
    }

    public String getSpotifyToken(){
        LOGGER.info("Getting spotify token, first looking for it in cache memory, if it is not there... getting from REST service.");
        String token = null;
        
        
        return token;
    }
}
