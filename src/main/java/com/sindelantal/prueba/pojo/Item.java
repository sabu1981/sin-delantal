
package com.sindelantal.prueba.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "icons",
    "id",
    "name"
})
public class Item {

    @JsonProperty("href")
    private String href;
    @JsonProperty("icons")
    private List<Icon> icons = null;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param id
     * @param name
     * @param icons
     * @param href
     */
    public Item(String href, List<Icon> icons, String id, String name) {
        super();
        this.href = href;
        this.icons = icons;
        this.id = id;
        this.name = name;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("icons")
    public List<Icon> getIcons() {
        return icons;
    }

    @JsonProperty("icons")
    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Item [href=" + href + ", icons=" + icons + ", id=" + id + ", name=" + name + "]";
    }


}
