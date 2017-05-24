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

public class Nuclear extends Building {

    protected int powerAdded;
    protected Vector3 loc;
    protected Skin skin;

    public Nuclear(Vector3 local, City city, Boolean inUse){
        super(local, city, inUse, 4, 5000);

        loc = local;

        sprite = new Image(new Texture("nuclear.png"));
        sprite.setPosition(loc.x, loc.y);



        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        catch (Exception e){
            skin = new Skin();
        }

        populationNeeded = (int)(Math.random()*15+30); //random requirement of residents
        pollutionCreated = 15; //specific pollution to building type
        powerAdded = populationNeeded*5;


    }

    public void addFunction()
    {
        if(city.getPopulation() >  populationNeeded)
        {
            inUse = true;
            city.setPollution(city.getPollution() + pollutionCreated);
            city.setPopulation(city.getPopulation()-populationNeeded);
            pop = new Label(""+powerAdded, skin);
            pop.setPosition(loc.x+1, loc.y+1);
            pop.setColor(Color.BLACK);
        }
        if(inUse)
        city.setPower(city.getPower()+powerAdded);
    }
}
