
package com.sindelantal.prueba.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "href",
    "items",
    "limit",
    "next",
    "offset",
    "previous",
    "total"
})
public class TracksResponse {

    @JsonProperty("href")
    private String href;
    @JsonProperty("items")
    private List<ItemTrack> items = null;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("next")
    private Object next;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("previous")
    private Object previous;
    @JsonProperty("total")
    private Integer total;
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public TracksResponse() {
    }

    /**
     * 
     * @param total
     * @param limit
     * @param previous
     * @param items
     * @param next
     * @param offset
     * @param href
     */
    public TracksResponse(String href, List<ItemTrack> items, Integer limit, Object next, Integer offset, Object previous, Integer total) {
        super();
        this.href = href;
        this.items = items;
        this.limit = limit;
        this.next = next;
        this.offset = offset;
        this.previous = previous;
        this.total = total;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("items")
    public List<ItemTrack> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<ItemTrack> items) {
        this.items = items;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("next")
    public Object getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(Object next) {
        this.next = next;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("previous")
    public Object getPrevious() {
        return previous;
    }

    @JsonProperty("previous")
    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TracksResponse [href=" + href + ", items=" + items + ", limit=" + limit + ", next=" + next + ", offset="
                + offset + ", previous=" + previous + ", total=" + total + "]";
    }

}
