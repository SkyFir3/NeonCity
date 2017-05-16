package com.luis.neoncity.Buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.luis.neoncity.Tools.City;

/**
 * Created by Luis on 5/15/2017 and Changed by Zach on 5/16/17.
 */

public class RecreationalBuilding extends Building { //implements Building{
    protected int happinessAdded;
    protected Image sprite;
    Batch batch;

    public RecreationalBuilding(Vector2 loc, City contains, Boolean inUse){
        super.location = loc;
        super.contains = contains;
        super.inUse = inUse;
        sprite = new Image(new Texture("res.png"));
        sprite.setPosition(loc.x, loc.y);
        populationNeeded = 1; //one person maintains the park
        pollutionCreated = -3; //specific pollution to building type
        happinessAdded = (int) Math.ceil(populationNeeded * contains.getHappiness() * 3); //The amount of people using the park is affected by city happiness
    }

    //adds happiness to the city that contains the building
    //     because the city contains the population variable
    public void addHappiness()
    {
        if(inUse)
            contains.setHappiness(contains.getHappiness() + happinessAdded);
    }

    public void draw(Batch batch){
        super.draw(batch);
    }
}