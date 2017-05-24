package com.luis.neoncity.Tools;

import com.luis.neoncity.Buildings.Building;
import java.util.ArrayList;

/**
 * Created by Luis on 5/16/2017 and Changed by Zach on 5/20/17.
 */

public class City {
    private String cityName;
    private Integer funds;
    private Integer population;
    private Integer pollution;
    private Integer happiness;
    private Integer power;
    public Tile[][] tiles;
    public int months;

    public ArrayList<Building> buildings;

    public City(String cityName, Integer funds){
        this.cityName = cityName;

        this.funds = funds;
        this.population = 500;
        pollution = 0;
        happiness = 100;
        power = 100;
        buildings = new ArrayList<Building>();
        tiles = new Tile[256][256];
        months = 0;

    }

    public void collectTaxes(){

        setFunds (Integer.sum((int)Math.ceil(population * 20), funds.intValue()));
        happiness = happiness - (int)(happiness/10);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getPollution()
    {
        return pollution;
    }

    public void setPollution(Integer pollution)
    {
        this.pollution = pollution;
    }

    public Integer getHappiness()
    {
        return happiness;
    }

    public void setHappiness(Integer happiness)
    {
        this.happiness = happiness;
    }

    public Integer getPower()
    {
        return power;
    }

    public void setPower(int pow)
    {
        power = pow;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }
    //buildings are initialized with Building(Vector2 location, this, Boolean IfBuildingIsInUse);
    //buildings are the superclass to all other non-road structures.
}
