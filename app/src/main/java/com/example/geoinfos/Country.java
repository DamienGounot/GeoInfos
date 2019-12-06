package com.example.geoinfos;

public class Country {

    private String name;
    private String capital;
    private String region;
    private int population;

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public int getPopulation() {
        return population;
    }

    public Country(String name, String capital, String region, int population) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
    }
}