
package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})
public class SpotifyErrorTokenResponse {

    @JsonProperty("error")
    private Error error;
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public SpotifyErrorTokenResponse() {
    }

    /**
     * 
     * @param error
     */
    public SpotifyErrorTokenResponse(Error error) {
        super();
        this.error = error;
    }

    @JsonProperty("error")
    public Error getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Error error) {
        this.error = error;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SpotifyErrorTokenResponse [error=" + error + "]";
    }

}
