package com.backend.botanicare.model;

public class Plant {
    Long id;
    String name;
    String art;
    String raum;
    String wasserbedarf;
    String standort;
    byte[] image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getWasserbedarf() {
        return wasserbedarf;
    }

    public void setWasserbedarf(String wasserbedarf) {
        this.wasserbedarf = wasserbedarf;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


