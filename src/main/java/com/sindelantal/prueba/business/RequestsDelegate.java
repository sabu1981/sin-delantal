package com.sindelantal.prueba.business;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sindelantal.prueba.pojo.CityNotFound;
import com.sindelantal.prueba.pojo.FinalTrackList;
import com.sindelantal.prueba.pojo.ItemPlayList;
import com.sindelantal.prueba.pojo.ItemTrack;
import com.sindelantal.prueba.pojo.PlaylistByCategoryResponse;
import com.sindelantal.prueba.pojo.SearchResponse;
import com.sindelantal.prueba.pojo.SpotifyTokenResponse;
import com.sindelantal.prueba.pojo.TracksResponse;
import com.sindelantal.prueba.utils.PropertiesTools;

public class RequestsDelegate {

    /** Resource LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestsDelegate.class);
    /** LRU Campaign cache. */
    private final CacheAccess<String, SpotifyTokenResponse> spotifyToken;
    
    private Client client;
    
    private final String URL_WEATHER = "urlWeather";
    
    private final String WEATHER_API_ID = "openWeatherMapAPIId";
    
    private final String URL_SPOTIFY = "urlSpotify";
    
    private final String URL_SPOTIFY_TOKEN = "urlSpotifyToken";
    
    private final String SPOTIFY_TOKEN = "spotiFyToken";
    
    private final String SPOTIFY_CLIENT_ID = "spotifyClientId";
    
    private final String SPOTIFY_CLIENT_SECRET = "spotifyClientSecret";
    
    public RequestsDelegate(final Client client) {
        spotifyToken = JCS.getInstance("spotifyToken");
        this.client = client;
    }
    
    /**
     * 
     * @param cityName
     * @return
     */
    public Response getWeatherByCityName(String cityName){
        SearchResponse sr = null;
        CityNotFound cnf = null;
        String path = PropertiesTools.getUrlProperty(URL_WEATHER);
        String weatherApiId = PropertiesTools.getUrlProperty(WEATHER_API_ID);
        final StringBuilder stringBuilder = new StringBuilder(path);
            stringBuilder.append("?");
            stringBuilder.append("q=");
            stringBuilder.append(cityName);
            stringBuilder.append("&units=metric");
            stringBuilder.append("&APPID=");
            stringBuilder.append(weatherApiId);
        LOGGER.info("::::::::::::::::::::::::: "+stringBuilder.toString());
        final WebTarget webTarget = client.target(stringBuilder.toString());
        final Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        LOGGER.info("HTTP status response code (getWeatherByCityName)::: " + response.getStatus());
        switch (response.getStatus()) {
        case 200:
            sr = response.readEntity(SearchResponse.class);
            response = Response.ok().entity(sr).build();
        break;
        case 404:
            cnf = response.readEntity(CityNotFound.class);
            response = Response.status(404).entity(cnf).build();
        break;
        default:
            LOGGER.error("Error getting JSON Weather (city) from API.");
        }
        return response;
    }
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @return
     */
    public Response getWeatherByLatAndLon(Double latitude, Double longitude){
        SearchResponse sr = null;
        CityNotFound cnf = null;
        String path = PropertiesTools.getUrlProperty(URL_WEATHER);
        String weatherApiId = PropertiesTools.getUrlProperty(WEATHER_API_ID);
        final StringBuilder stringBuilder = new StringBuilder(path);
            stringBuilder.append("?");
            stringBuilder.append("lat=");
            stringBuilder.append(latitude);
            stringBuilder.append("&lon=");
            stringBuilder.append(longitude);
            stringBuilder.append("&units=metric");
            stringBuilder.append("&APPID=");
            stringBuilder.append(weatherApiId);
        final WebTarget webTarget = client.target(stringBuilder.toString());
        final Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        LOGGER.info("HTTP status response code (getWeatherByLatAndLon)::: " + response.getStatus());
        switch (response.getStatus()) {
        case 200:
            sr = response.readEntity(SearchResponse.class);
            response = Response.ok().entity(sr).build();
        break;
        case 400:
            LOGGER.info("Is not a float.");
            cnf = response.readEntity(CityNotFound.class);
            response = Response.status(400).entity(cnf).build();
        break;
        default:
            LOGGER.error("Error getting JSON Weather (lat-long) from API.");
        }
        return response;
    }
    
    /**
     * 
     * @param category
     * @return
     */
    public Response getPlaylitsByCategory(String category){
        Response response = null;
        PlaylistByCategoryResponse playlistObj = null;
        SpotifyTokenResponse spotiToken = null;
        Response playlistResponse = null; 
        String urlEndpoint = null;
        String firstPlaylistName = null;
        TracksResponse trkRes = null;
        FinalTrackList ftl = null;
        spotiToken = getToken();
        
        if(spotiToken != null){
            LOGGER.info("Checkpoint...");
            playlistResponse = getPlaylistToken(category, spotiToken.getAccessToken());
            switch (playlistResponse.getStatus()) {
            case 200:
                //call endpoint tracks
                LOGGER.info("..:: playlistResponse ::..");
                playlistObj = (PlaylistByCategoryResponse) playlistResponse.getEntity(); 
                List<ItemPlayList> playlistItems = playlistObj.getPlaylists().getItems(); 
                LOGGER.info("playlistItems size:::: "+playlistItems.size());
                ItemPlayList firstItemPlaylist = playlistItems.get(0);
                firstPlaylistName = firstItemPlaylist.getName();
                urlEndpoint = firstItemPlaylist.getTracks().getHref();
                Response playlistTracksResponse = getSpotifyTracksPlaylist(urlEndpoint);
                switch (playlistTracksResponse.getStatus()) {
                case 200:
                    ftl = new FinalTrackList();
                    ftl.setPlayListName(firstPlaylistName);
                    ftl.setCategory(category);
                    trkRes = (TracksResponse) playlistTracksResponse.getEntity();
                    if(trkRes != null){
                        LOGGER.info(":: trkRes.getItems() size -::"+trkRes.getItems().size());
                        ftl.setTracks(new ArrayList<String>());
                        for(ItemTrack track : trkRes.getItems()){
                            LOGGER.info("-------------->>>> "+track.getTrack().getName());
                            ftl.getTracks().add(track.getTrack().getName());
                        }
                    }
                    response = Response.ok().entity(ftl).build();
                break;
                default:
                    response = Response.status(500).build();
                    LOGGER.error("Error getting PLAYLISTS TRACKS from API.");
                }    
            break;
            default:
                response = Response.status(500).build();
                LOGGER.error("Error getting PLAYLISTS from API.");
            }
        }
        
        return response;
        
    }
    
    /**
     * 
     * @param clientId
     * @param clientSecret
     * @return
     */
    private String encodeCredentials(String clientId, String clientSecret) {
        String encodedValue = null;
        byte[] arr= new String(clientId+":"+clientSecret).getBytes();
        encodedValue = Base64.getEncoder().encodeToString(arr);
        LOGGER.info("encodedValue::: " + encodedValue);
        return encodedValue;
    }
    
    /**
     * 
     * @return
     */
    private SpotifyTokenResponse getToken() {
        SpotifyTokenResponse spotiToken = null;
        Response responseGettingToken = null; 
        LOGGER.info("** Getting token: " );
        try {
            spotiToken = spotifyToken.get(SPOTIFY_TOKEN);
            if (spotiToken == null) {
                LOGGER.info("Spotify Token does not exist in cache");
                //go to spoti token
                responseGettingToken = getSpotifyToken();
                if (responseGettingToken.getStatus() == 200) {
                    spotiToken = (SpotifyTokenResponse) responseGettingToken.getEntity();
                    synchronized (spotifyToken) {
                        LOGGER.info("Setting spotify token in cache...");
                        spotifyToken.put(SPOTIFY_TOKEN, spotiToken);
                        LOGGER.info("Token setted in cache (1)");
                    }
                } else {
                    LOGGER.info(":: responseGettingToken.getEntity() == null ::");
                }
            } else {
                LOGGER.info("Validating if the token is still valid (from cache)");
                long currentTime = System.currentTimeMillis();
                long tokenTime = spotiToken.getTotalTimeExpires();
                LOGGER.info("currentTime::: "+currentTime);
                LOGGER.info("tokenTime::: "+tokenTime);
                if(currentTime >= tokenTime){
                    LOGGER.info("Not valid token");
                    spotifyToken.remove(SPOTIFY_TOKEN);
                    LOGGER.info("Getting token once again...");
                    responseGettingToken = getSpotifyToken();
                    if (responseGettingToken.getStatus() == 200) {
                        spotiToken = (SpotifyTokenResponse) responseGettingToken.getEntity();
                        synchronized (spotifyToken) {
                            LOGGER.info("Setting spotify token in cache...");
                            spotifyToken.put(SPOTIFY_TOKEN, spotiToken);
                            LOGGER.info("Token setted in cache (2)");
                        }
                    } else {
                        LOGGER.info(":: responseGettingToken.getEntity() == null ::");
                    }
                }else{
                    LOGGER.info("Still valid!!");
                }
            }
        }  
        catch (final Exception ex) {
            LOGGER.info("Exception :: " + ex.getMessage());
        }
        LOGGER.info("||--------------------------||\n"+spotiToken.toString());
        return spotiToken;
    
    }
    
    /**
     * 
     * @return
     */
    private Response getSpotifyToken(){
        LOGGER.info("Endpoint getSpotifyToken");
        SpotifyTokenResponse sTknResp = null;
        String path = PropertiesTools.getUrlProperty(URL_SPOTIFY_TOKEN);
        String clientId = PropertiesTools.getUrlProperty(SPOTIFY_CLIENT_ID);
        String clientSecret = PropertiesTools.getUrlProperty(SPOTIFY_CLIENT_SECRET);
        String encodedCredential = encodeCredentials(clientId, clientSecret);
        client.property("token", encodedCredential);
        client.property("spotifyTokenRequest", true);//spotifyNormalRequest
        final WebTarget webTarget = client.target(path);
        final Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.form(fillFormGetToken()));
        LOGGER.info("HTTP status response code (getSpotifyToken)::: " + response.getStatus());
        switch (response.getStatus()) {
        case 200:
            sTknResp = response.readEntity(SpotifyTokenResponse.class);
            long currentMills = System.currentTimeMillis();
            Integer timeLastTkn = sTknResp.getExpiresIn() * 1000;
            long timeLastTknLong = timeLastTkn.longValue();
            sTknResp.setTotalTimeExpires(currentMills+timeLastTknLong);
            response = Response.ok().entity(sTknResp).build();
        break;
        default:
            response = Response.status(500).build();
            LOGGER.error("Error getting TOKEN from API.");
        }
        return response;
    }
    
    /**
     * 
     * @return
     */
    private Response getSpotifyTracksPlaylist(String urlEndpoint){
        LOGGER.info("Endpoint getSpotifyToken");
        TracksResponse tracksResp = null;
        String path = PropertiesTools.getUrlProperty(URL_SPOTIFY);
        String token = getToken().getAccessToken();
        LOGGER.info("token getSpotifyTracksPlaylist::: "+token);
        client.property("token", token);
        client.property("spotifyTokenRequest", false);//spotifyNormalRequest
        final WebTarget webTarget = client.target(urlEndpoint);
        final Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = builder.get();
        LOGGER.info("HTTP status response code (getSpotifyTracksPlaylist)::: " + response.getStatus());
        switch (response.getStatus()) {
        case 200:
            tracksResp = response.readEntity(TracksResponse.class);
            response = Response.ok().entity(tracksResp).build();
        break;
        default:
            response = Response.status(500).build();
            LOGGER.error("Error getting TRACKS from API.");
        }
        return response;
    }
    
    /**
     * 
     * @return
     */
    private Response getPlaylistToken(String category, String accessToken){
        Response response = null;
        PlaylistByCategoryResponse pl = null;
        String path = PropertiesTools.getUrlProperty(URL_SPOTIFY);
        //https://api.spotify.com/v1/
        //.... browse/categories/classical/playlists
        final StringBuilder stringBuilder = new StringBuilder(path);
            stringBuilder.append("browse/categories/");
            stringBuilder.append(category);
            stringBuilder.append("/playlists");
        client.property("token", accessToken);
        client.property("spotifyTokenRequest", false);//spotifyNormalRequest
        final WebTarget webTarget = client.target(stringBuilder.toString());
        final Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        response = builder.get();
        LOGGER.info("HTTP status response code (getPlaylistToken)::: " + response.getStatus());
        switch (response.getStatus()) {
        case 200:
            pl = response.readEntity(PlaylistByCategoryResponse.class);
            response = Response.ok().entity(pl).build();
        break;
        default:
            response = Response.status(500).build();
            LOGGER.error("Error getting TOKEN from API.");
        }
        return response;
    }
    
    /**
     * Auxiliary method.
     *
     * @return form Form data. Desc.
     */
     private Form fillFormGetToken() {
         final Form form = new Form();
         form.param("grant_type", "client_credentials");
         return form;
     }
}
