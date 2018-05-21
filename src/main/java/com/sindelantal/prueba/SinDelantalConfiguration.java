package com.sindelantal.prueba;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SinDelantalConfiguration extends Configuration {
    
    /** Swagger configuration. */
    private SwaggerBundleConfiguration swaggerConfig;

    /**
     * Getter for swagger configuration.
     * @return the swaggerConfig.
     */
    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwaggerConfig() {
        return swaggerConfig;
    }

    /**
     * Setter for swagger configuration.
     * @param swaggerConfig the swaggerConfig to set.
     */
    public void setSwaggerConfig(final SwaggerBundleConfiguration swaggerConfig) {
        this.swaggerConfig = swaggerConfig;
    }

}
