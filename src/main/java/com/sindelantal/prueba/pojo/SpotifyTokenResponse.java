package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "access_token",
    "token_type",
    "expires_in",
    "scope"
})
public class SpotifyTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("scope")
    private String scope;
    private long totalTimeExpires = 0L;
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public SpotifyTokenResponse() {
    }

    /**
     * 
     * @param scope
     * @param tokenType
     * @param accessToken
     * @param expiresIn
     */
    public SpotifyTokenResponse(String accessToken, String tokenType, Integer expiresIn, String scope) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty("token_type")
    public String getTokenType() {
        return tokenType;
    }

    @JsonProperty("token_type")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonProperty("expires_in")
    public Integer getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonProperty("scope")
    public String getScope() {
        return scope;
    }

    @JsonProperty("scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the totalTimeExpires
     */
    public long getTotalTimeExpires() {
        return totalTimeExpires;
    }

    /**
     * @param totalTimeExpires the totalTimeExpires to set
     */
    public void setTotalTimeExpires(long totalTimeExpires) {
        this.totalTimeExpires = totalTimeExpires;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SpotifyTokenResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + ", expiresIn="
                + expiresIn + ", scope=" + scope + ", totalTimeExpires=" + totalTimeExpires + "]";
    }

}
