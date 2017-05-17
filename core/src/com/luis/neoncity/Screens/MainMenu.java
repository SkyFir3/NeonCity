package com.luis.neoncity.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.neoncity.NeonCity;
import com.luis.neoncity.Tools.City;
import com.luis.neoncity.Tools.TextInput;

/**
 * Created by jz367071 on 5/12/2017.
 */

public class MainMenu implements Screen {
    private Stage stage;
    Image sprite;
    Image title;
    SpriteBatch sb;
    NeonCity game;
    Skin skin;
    TextInput cityName;
    public MainMenu(NeonCity g, SpriteBatch s) {
        this.game = g;
        this.sb = s;
        sb.begin();

        //removed create(); and inserted its code where it was called
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        catch(Exception e){
            skin = new Skin();
        }

        TextButton start = new TextButton("New Game", skin, "default");
        start.setSize(300, 100);
        start.setPosition(900, 550);
        start.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                sb.end();

                game.setScreen(new PlayScreen(game, sb, new City(cityName.getInput())));
            }
        });


        TextButton load = new TextButton("Change City Name", skin, "default");
        load.setSize(300, 100);
        load.setPosition(900, 440);
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                cityName = new TextInput();
                Gdx.input.getTextInput(cityName, "Change Name", "Dallas", "City");
            }
        });

        TextButton options = new TextButton("Options", skin, "default");
        options.setSize(300, 100);
        options.setPosition(900, 330);

        //sprite is the background image for the title
        sprite = new Image(new Texture("8bitCity.png"));
        sprite.setSize(1366, 768);

        title = new Image(new Texture("menu.png"));
        title.setPosition(30, 550);

        //adding image and button to display in order
        stage.addActor(sprite);
        stage.addActor(title);
        stage.addActor(start);
        stage.addActor(load);
        stage.addActor(options);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}