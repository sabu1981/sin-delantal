
package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "added_at",
    "added_by",
    "is_local",
    "primary_color",
    "track",
    "video_thumbnail"
})
public class ItemTrack {

    @JsonProperty("added_at")
    private String addedAt;
    @JsonProperty("added_by")
    private AddedBy addedBy;
    @JsonProperty("is_local")
    private Boolean isLocal;
    @JsonProperty("primary_color")
    private Object primaryColor;
    @JsonProperty("track")
    private Track track;
    @JsonProperty("video_thumbnail")
    private VideoThumbnail videoThumbnail;
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemTrack() {
    }

    /**
     * 
     * @param primaryColor
     * @param track
     * @param addedAt
     * @param isLocal
     * @param addedBy
     * @param videoThumbnail
     */
    public ItemTrack(String addedAt, AddedBy addedBy, Boolean isLocal, Object primaryColor, Track track, VideoThumbnail videoThumbnail) {
        super();
        this.addedAt = addedAt;
        this.addedBy = addedBy;
        this.isLocal = isLocal;
        this.primaryColor = primaryColor;
        this.track = track;
        this.videoThumbnail = videoThumbnail;
    }

    @JsonProperty("added_at")
    public String getAddedAt() {
        return addedAt;
    }

    @JsonProperty("added_at")
    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    @JsonProperty("added_by")
    public AddedBy getAddedBy() {
        return addedBy;
    }

    @JsonProperty("added_by")
    public void setAddedBy(AddedBy addedBy) {
        this.addedBy = addedBy;
    }

    @JsonProperty("is_local")
    public Boolean getIsLocal() {
        return isLocal;
    }

    @JsonProperty("is_local")
    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    @JsonProperty("primary_color")
    public Object getPrimaryColor() {
        return primaryColor;
    }

    @JsonProperty("primary_color")
    public void setPrimaryColor(Object primaryColor) {
        this.primaryColor = primaryColor;
    }

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track track) {
        this.track = track;
    }

    @JsonProperty("video_thumbnail")
    public VideoThumbnail getVideoThumbnail() {
        return videoThumbnail;
    }

    @JsonProperty("video_thumbnail")
    public void setVideoThumbnail(VideoThumbnail videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemTrack [addedAt=" + addedAt + ", addedBy=" + addedBy + ", isLocal=" + isLocal + ", primaryColor="
                + primaryColor + ", track=" + track + ", videoThumbnail=" + videoThumbnail + "]";
    }

}
