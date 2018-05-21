package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "playlists"
})
public class PlaylistByCategoryResponse {

    @JsonProperty("playlists")
    private Playlists playlists;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlaylistByCategoryResponse() {
    }

    /**
     * 
     * @param playlists
     */
    public PlaylistByCategoryResponse(Playlists playlists) {
        super();
        this.playlists = playlists;
    }

    @JsonProperty("playlists")
    public Playlists getPlaylists() {
        return playlists;
    }

    @JsonProperty("playlists")
    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PlaylistByCategoryResponse [playlists=" + playlists + "]";
    }

}
