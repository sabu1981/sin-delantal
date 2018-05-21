
package com.sindelantal.prueba.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "collaborative",
    "external_urls",
    "href",
    "id",
    "images",
    "name",
    "owner",
    "public",
    "snapshot_id",
    "tracks",
    "type",
    "uri"
})
public class ItemPlayList {

    @JsonProperty("collaborative")
    private Boolean collaborative;
    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;
    @JsonProperty("href")
    private String href;
    @JsonProperty("id")
    private String id;
    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("public")
    private Object _public;
    @JsonProperty("snapshot_id")
    private String snapshotId;
    @JsonProperty("tracks")
    private Tracks tracks;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ItemPlayList() {
    }

    /**
     * 
     * @param snapshotId
     * @param id
     * @param _public
     * @param collaborative
     * @param externalUrls
     * @param name
     * @param owner
     * @param images
     * @param tracks
     * @param type
     * @param uri
     * @param href
     */
    public ItemPlayList(Boolean collaborative, ExternalUrls externalUrls, String href, String id, List<Image> images, String name, Owner owner, Object _public, String snapshotId, Tracks tracks, String type, String uri) {
        super();
        this.collaborative = collaborative;
        this.externalUrls = externalUrls;
        this.href = href;
        this.id = id;
        this.images = images;
        this.name = name;
        this.owner = owner;
        this._public = _public;
        this.snapshotId = snapshotId;
        this.tracks = tracks;
        this.type = type;
        this.uri = uri;
    }

    @JsonProperty("collaborative")
    public Boolean getCollaborative() {
        return collaborative;
    }

    @JsonProperty("collaborative")
    public void setCollaborative(Boolean collaborative) {
        this.collaborative = collaborative;
    }

    @JsonProperty("external_urls")
    public ExternalUrls getExternalUrls() {
        return externalUrls;
    }

    @JsonProperty("external_urls")
    public void setExternalUrls(ExternalUrls externalUrls) {
        this.externalUrls = externalUrls;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty("public")
    public Object getPublic() {
        return _public;
    }

    @JsonProperty("public")
    public void setPublic(Object _public) {
        this._public = _public;
    }

    @JsonProperty("snapshot_id")
    public String getSnapshotId() {
        return snapshotId;
    }

    @JsonProperty("snapshot_id")
    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    @JsonProperty("tracks")
    public Tracks getTracks() {
        return tracks;
    }

    @JsonProperty("tracks")
    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ItemPlayList [collaborative=" + collaborative + ", externalUrls=" + externalUrls + ", href=" + href
                + ", id=" + id + ", images=" + images + ", name=" + name + ", owner=" + owner + ", _public=" + _public
                + ", snapshotId=" + snapshotId + ", tracks=" + tracks + ", type=" + type + ", uri=" + uri + "]";
    }

}
