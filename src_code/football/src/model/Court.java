package model;

import java.io.Serializable;

public class Court implements Serializable{
    private int id;
    private String type;
    private float pricePerSession;
    private String description;

    public Court() {
        super();
    }

    public Court(String type, float pricePerSession, String description) {
        super();
        this.type = type;
        this.pricePerSession = pricePerSession;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPricePerSession() {
        return pricePerSession;
    }

    public void setPricePerSession(float pricePerSession) {
        this.pricePerSession = pricePerSession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}


