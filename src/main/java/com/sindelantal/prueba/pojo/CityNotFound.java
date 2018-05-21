package com.sindelantal.prueba.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityNotFound {

    @JsonProperty("cod")
    private Integer cod;
    @JsonProperty("message")
    private String message;
    
    /**
     * @param cod
     * @param message
     */
    public CityNotFound(Integer cod, String message) {
        this.cod = cod;
        this.message = message;
    }

    /**
     * 
     */
    public CityNotFound() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the cod
     */
    @JsonProperty("cod")
    public Integer getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    @JsonProperty("cod")
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    /**
     * @return the message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
