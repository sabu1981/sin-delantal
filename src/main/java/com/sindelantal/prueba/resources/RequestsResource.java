package com.sindelantal.prueba.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sindelantal.prueba.business.BusinessOperations;
import com.sindelantal.prueba.business.RequestsDelegate;
import com.sindelantal.prueba.pojo.CityNotFound;
import com.sindelantal.prueba.pojo.FinalTrackList;
import com.sindelantal.prueba.pojo.Main;
import com.sindelantal.prueba.pojo.SearchResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/getPlayListByWeather")
@Api(value = "/playlists", tags = "playlists")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RequestsResource {

    /** Resource LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestsResource.class);
    
    private RequestsDelegate requestsDelegate;

    private BusinessOperations businessOperations;
    
    private final String PARTY = "party";
    
    private final String POP = "pop";
    
    private final String ROCK = "rock";
    
    private final String CLASSICAL = "classical";
    /**
     * Creates a new instance of the service, assigns the DAO.
     *
     * @param dao Database data access object to use.
     * @param campaignBusiness business class.
     */
    public RequestsResource(final RequestsDelegate requestsDelegate, 
            final BusinessOperations businessOperations) {
        this.requestsDelegate = requestsDelegate;
        this.businessOperations = businessOperations;
    }
    
    /**
     * 
     * @param cityName
     * @return
     */
    @GET
    @Path("/getPlaylistsByCityName")
    @ApiOperation(value = "Gets the tracks via city name.", 
        notes = "Returns a single object. Input params via PATH params.")
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "City not founds.")})
    public Response getPlaylistsByCity(@ApiParam(value = "City name", required = true) @QueryParam("cityName") final String cityName) {
        LOGGER.info("Service by cityName:: " +cityName.replace(" ", "+"));
        Response playlistResponse = null;
        Response responseFromWeatherApi = null;
        SearchResponse sr = null;
        CityNotFound cnf = null;
        responseFromWeatherApi = requestsDelegate.getWeatherByCityName(cityName.replace(" ", "+"));
        switch (responseFromWeatherApi.getStatus()) {
        case 200:
            sr = (SearchResponse) responseFromWeatherApi.getEntity();
            playlistResponse = getPlaylists(sr);
            break;
        case 404:
            LOGGER.info("City not found.");
            cnf = (CityNotFound) responseFromWeatherApi.getEntity();
            playlistResponse = Response.status(404).entity(cnf).build();
            break;
        default:
            LOGGER.error("Error getting JSON from WEATHER city API.");
        }
        return playlistResponse;
    }

    /**
     * 
     * @param latitude
     * @param longitude
     * @return
     */
    @GET
    @Path("/getPlaylistsByLatAndLong")
    @ApiOperation(value = "Gets the tracks via latitude and longitude.",
        notes = "Returns a single object. Input params via QUERY params."
    )
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "City not found")
    })
    public Response getPlaylistsByLatLog(@ApiParam(value = "Latitude", required = true) @QueryParam("latitude") final Double latitude,
            @ApiParam(value = "Longitude", required = true) @QueryParam("longitude") final Double longitude){
        LOGGER.info("Service by latitude ans longitude");
        Response responseFromWeatherApi = null;
        Response playlistResponse = null;
        SearchResponse sr = null;
        CityNotFound cnf = null;
        responseFromWeatherApi = requestsDelegate.getWeatherByLatAndLon(latitude, longitude);
        switch (responseFromWeatherApi.getStatus()) {
        case 200:
            sr = (SearchResponse) responseFromWeatherApi.getEntity();
            playlistResponse = getPlaylists(sr);
            break;
        case 400:
            LOGGER.info("Not found.");
            cnf = (CityNotFound) responseFromWeatherApi.getEntity();
            playlistResponse = Response.status(400).entity(cnf).build();
            break;
        default:
            LOGGER.error("Error getting JSON from WEATHER city API.");
        }
        return playlistResponse;
    }
    
    /**
     * 
     * @param sr
     * @return
     */
    private Response getPlaylists(SearchResponse sr) {
        Response response = null;
        FinalTrackList ftl = null;
        Main main = sr.getMain();
        Double temp = main.getTemp();
        //party, pop, party, classical
        String musicCategory = getMusicCategory(temp);
        
        response = requestsDelegate.getPlaylitsByCategory(musicCategory);
        switch (response.getStatus()) {
        case 200:
            ftl = (FinalTrackList) response.getEntity();
            response = Response.ok().entity(ftl).build();
        break;
        default:
            response = Response.status(500).build();
            LOGGER.error("Error getting PLAYLISTS TRACKS from API.");
        }
        return response;
    }

    /**
     * 
     * @param temp
     * @return
     */
    private String getMusicCategory(Double temp) {
        String category = null;
        LOGGER.info("Temp of the city:: "+temp);
        if(temp != null){
            double temperature = temp.doubleValue();
            if(temperature > 30.00){
                category = PARTY;
            } else if(temperature >= 15.00 && temperature <= 30.00){
                category = POP;
            } else if(temperature >= 10.00 && temperature <= 14.99){
                category = ROCK;
            } else {
                category = CLASSICAL;
            }
        }
        LOGGER.info("Playlist category to return:: "+category);
        return category;
    }

}
