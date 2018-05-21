
package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url"
})
public class VideoThumbnail {

    @JsonProperty("url")
    private Object url;
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public VideoThumbnail() {
    }

    /**
     * 
     * @param url
     */
    public VideoThumbnail(Object url) {
        super();
        this.url = url;
    }

    @JsonProperty("url")
    public Object getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(Object url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VideoThumbnail [url=" + url + "]";
    }

}
