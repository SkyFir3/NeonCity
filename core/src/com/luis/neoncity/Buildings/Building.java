package com.luis.neoncity.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.luis.neoncity.Tools.City;

/**
 * Created by Luis on 5/15/2017 and Changed by Zach on 5/23/17.
 */

public class Building extends Actor {

    protected Vector3 location;
    protected City city;
    protected int populationNeeded, pollutionCreated, powerNeeded;

    public Boolean inUse;
    public Label pop;
    public Image sprite;
    public int size;
    public int cost;

    public Building()
    {
    }

    public Building(Vector3 loc, City city, boolean inUse, int size, int cost)
    {

        Skin skin;
        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        catch (Exception e){
            skin = new Skin();
        }

        pop = new Label("", skin);

        location = loc;
        this.city = city;
        this.inUse = inUse;
        this.size = size;
        this.cost = cost;
    }

    public void setTilesUnusable(){
        for(int x = (int)location.x/16; x < (int)location.x/16 + size; x++){
            for(int y = (int)location.y/16; y < (int)location.y/16 + size; y++){
                city.tiles[x][y].setUsable(false);
            }
        }
    }
    public Vector3 getLocation()
    {
        return location;
    }

    public int getPopulationNeeded()
    {
        return populationNeeded;
    }

    public int getPollutionCreated()
    {
        return pollutionCreated;
    }

    public void addPollution()
    {
            if(inUse)
            city.setPollution(city.getPollution() + pollutionCreated);
    }

    public void addFunction()
    {

    }
}
