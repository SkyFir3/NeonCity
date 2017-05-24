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
 * Created by Zach on 5/16/17.
 */

public class IndustrialBuilding extends Building {
    protected double fundsAdded;
    protected Skin skin;
    protected Vector3 loc;

    public IndustrialBuilding(Vector3 local, City city, Boolean inUse){
        super(local, city, inUse, 4, 5000);

        loc = local;

        sprite = new Image(new Texture("ind.png"));
        sprite.setPosition(loc.x, loc.y);



        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        catch (Exception e){
            skin = new Skin();
        }

        populationNeeded = (int)(Math.random()*15+30); //random requirement of residents
        pollutionCreated = 15; //specific pollution to building type
        powerNeeded = 50;
        fundsAdded = populationNeeded*city.getHappiness() + 0.0 ;


    }

    public void addFunction()
    {
        if(city.getPopulation() >  populationNeeded && city.getPower() > powerNeeded)
        {
            city.setPollution(city.getPollution() + pollutionCreated);
            city.setPopulation(city.getPopulation()-populationNeeded);
            city.setPower(city.getPower()-powerNeeded);
            pop = new Label(""+fundsAdded/1000, skin);
            pop.setPosition(loc.x+1, loc.y+1);
            pop.setColor(Color.BLACK);
        }
        city.setFunds(city.getFunds()+(int)(fundsAdded));
    }
}
