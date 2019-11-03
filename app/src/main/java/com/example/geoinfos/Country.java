package com.example.geoinfos;

import android.graphics.Movie;

import java.util.List;

public class Country {

    private String name;
    private String nativeName;
    private String capital;
    private String region;
    private int population;
    private String flag;

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

    public String getNativeName() {
        return nativeName;
    }

    public String getFlag() {
        return flag;
    }

    public Country(String name, String nativeName, String capital, String region, int population, String flag) {
        this.name = name;
        this.nativeName = nativeName;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.flag = flag;
    }
}