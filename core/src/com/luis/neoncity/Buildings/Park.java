package com.luis.neoncity.Buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.luis.neoncity.Tools.City;

/**
 * Created by Luis on 5/18/17.
 */

public class Park extends Building { //implements Building{
    protected int happyAdded;
    protected Vector3 loc;
    protected Skin skin;

    public Park(Vector3 local, City city, Boolean inUse){
        super(local, city, inUse, 1, 10);

        loc = local;

        sprite = new Image(new Texture("park.png"));
        sprite.setPosition(loc.x, loc.y);

        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        catch (Exception e){
            skin = new Skin();
        }

        populationNeeded = (int)(Math.random()*4+3); //random requirement of workers
        pollutionCreated = 2; //specific pollution to building type
        happyAdded = (int) Math.ceil(populationNeeded * (city.getHappiness()/10)); //The amount of happiness made is affected by city happiness and people working
    }

    //adds funds to the city that city the building
    //     because the city city the population variable
    public void addFunction()
    {
        if(city.getPopulation() >  populationNeeded && city.getPower() > powerNeeded)
        {
            city.setPollution(city.getPollution() + pollutionCreated);
            city.setPopulation(city.getPopulation()-populationNeeded);
            city.setPower(city.getPower()-powerNeeded);
            pop = new Label(""+happyAdded, skin);
            pop.setPosition(loc.x+1, loc.y+1);
            pop.setColor(Color.BLACK);
        }
        city.setHappiness(city.getHappiness() + happyAdded);
    }
}
