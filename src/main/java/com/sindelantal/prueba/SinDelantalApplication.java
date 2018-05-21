package com.sindelantal.prueba;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;

import com.sindelantal.prueba.filters.TokenRequestFilter;
import com.sindelantal.prueba.business.BusinessOperations;
import com.sindelantal.prueba.business.RequestsDelegate;
import com.sindelantal.prueba.filters.CORSResponseFilter;
import com.sindelantal.prueba.resources.RequestsResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SinDelantalApplication extends Application<SinDelantalConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SinDelantalApplication().run(args);
    }

    @Override
    public String getName() {
        return "Prueba Sin Delantal";
    }

    @Override
    public void initialize(final Bootstrap<SinDelantalConfiguration> bootstrap) {
     // para swagger
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)));
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new SwaggerBundle<SinDelantalConfiguration>() {

            /**
             * Retrieves the swagger configuration.
             *
             * @param config The Swagger configurtion object.
             * @see io.federecio.dropwizard.swagger.SwaggerBundle#getSwaggerBundleConfiguration(io.dropwizard.Configuration)
             */

            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final SinDelantalConfiguration config) {
                return config.getSwaggerConfig();
            }
        });
    }

    @Override
    public void run(final SinDelantalConfiguration configuration,
                    final Environment environment) {
        //final Client client = new JerseyClientBuilder().build();
        final Client client = new JerseyClientBuilder().register(TokenRequestFilter.class).build();
        final RequestsDelegate requestsDelegate = new RequestsDelegate(client);
        final BusinessOperations businessOperations = new BusinessOperations(requestsDelegate);
        final CORSResponseFilter corsFilter = new CORSResponseFilter();

        environment.jersey().register(new RequestsResource(requestsDelegate, businessOperations));
        environment.jersey().register(corsFilter);
    }

}
