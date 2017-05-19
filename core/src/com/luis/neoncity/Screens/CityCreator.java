package com.luis.neoncity.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.luis.neoncity.NeonCity;
import com.luis.neoncity.Tools.City;
import com.luis.neoncity.Tools.TextInput;

import java.util.concurrent.ExecutionException;

/**
 * Created by jz367071 on 5/18/2017.
 */

public class CityCreator implements Screen {
    private Stage stage;
    protected SpriteBatch sb;
    private Image back;
    private Image options;
    protected NeonCity game;
    protected Skin skin;
    private TextInput cityName;
    private SelectBox cityDifficulty;
    private Integer cityFunds;
    public CityCreator(NeonCity g, SpriteBatch s) {
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

       // TextButton start = new TextButton("Difficulty", skin, "default");
       // start.setSize(300, 100);
       // start.setPosition(120, 550);
       // start.addListener(new ChangeListener() {
        //    @Override
       //     public void changed (ChangeEvent event, Actor actor) {
                cityDifficulty = new SelectBox(skin);
                cityDifficulty.setName("Difficulty");
                cityDifficulty.setSize(300, 100);
                cityDifficulty.setPosition(120, 550);
                String[] items = new String[3];
                items[0] = "Easy";
                items[1] = "Medium";
                items[2] = "Hard";
                cityDifficulty.setItems(items);

         //   }
       // });


        TextButton load = new TextButton("Create City Name", skin, "default");
        load.setSize(300, 100);
        load.setPosition(120, 440);
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                cityName = new TextInput();
                Gdx.input.getTextInput(cityName, "", "Dallas", "");
            }
        });

        TextButton exit = new TextButton("Continue", skin, "default");
        exit.setSize(300, 100);
        exit.setPosition(120, 330);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sb.end();

                try {

                if(cityDifficulty.getSelected().equals("Easy"))
                    cityFunds = 200000;
                else if(cityDifficulty.getSelected().equals("Medium"))
                    cityFunds = 20000;
                else if(cityDifficulty.getSelected().equals("Hard"))
                    cityFunds = 2000;
                else
                    cityFunds = 0;
                }
                catch(Exception e)
                {
                    Label error = new Label("", skin);
                    error.setText("Set a City Name AND Difficulty before starting the game.");
                    error.setPosition(60, 30);
                    stage.addActor(error);
                    sb.begin();
                }

                try {
                    game.setScreen(new PlayScreen(game, sb, new City(cityName.getInput(),cityFunds )));
                }
                catch(Exception e)
                {
                    Label error = new Label("", skin);
                    error.setText("Set a City Name AND Difficulty before starting the game.");
                    error.setPosition(60, 30);
                    stage.addActor(error);
                    sb.begin();
                }
            }
        });

        back = new Image(new Texture("black.png"));
        back.setSize(1366, 768);
        options = new Image(new Texture("GameOptions.png"));
        options.setPosition(80, 660);

        stage.addActor(back);
        stage.addActor(options);
        stage.addActor(cityDifficulty);
        //stage.addActor(start);
        stage.addActor(load);
        stage.addActor(exit);
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
        stage.dispose();
    }
}