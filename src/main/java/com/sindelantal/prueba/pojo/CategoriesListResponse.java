
package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "categories"
})
public class CategoriesListResponse {

    @JsonProperty("categories")
    private Categories categories;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoriesListResponse() {
    }

    /**
     * 
     * @param categories
     */
    public CategoriesListResponse(Categories categories) {
        super();
        this.categories = categories;
    }

    @JsonProperty("categories")
    public Categories getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
