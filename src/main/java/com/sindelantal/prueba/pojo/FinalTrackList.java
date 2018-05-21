package com.sindelantal.prueba.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinalTrackList {

    @JsonProperty("playListName")
    private String playListName;
    @JsonProperty("category")
    private String category;
    @JsonProperty("tracks")
    private List<String> tracks;

    /**
     * 
     */
    public FinalTrackList() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param playListName
     * @param tracks
     */
    public FinalTrackList(String playListName, String category, List<String> tracks) {
        this.playListName = playListName;
        this.category = category;
        this.tracks = tracks;
    }

    /**
     * @return the playListName
     */
    @JsonProperty("playListName")
    public String getPlayListName() {
        return playListName;
    }

    /**
     * @param playListName the playListName to set
     */
    @JsonProperty("playListName")
    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the tracks
     */
    @JsonProperty("tracks")
    public List<String> getTracks() {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    @JsonProperty("tracks")
    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FinalTrackList [playListName=" + playListName + ", category=" + category + ", tracks=" + tracks + "]";
    }
    
}
