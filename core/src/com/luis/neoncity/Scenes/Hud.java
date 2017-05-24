package com.luis.neoncity.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.luis.neoncity.NeonCity;
import com.luis.neoncity.Tools.City;

import java.util.ArrayList;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created by Luis and Jacob on 5/9/2017.
 */

public class Hud implements InputProcessor {
    public enum State {
        BULLDOZER, ROAD,
        RAIL, POWER,
        PARK, RESIDENTIAL,
        COMMERCIAL, INDUSTRIAL,
        FIRE, POLICE,
        STADIUM, SEAPORT,
        COAL, NUCLEAR,
        AIRPORT, DRAG
    }

    ;
    public State currentState;

    private SpriteDrawable icon;
    private Sprite sprite;

    private Viewport viewport;
    public Stage stage;
    public Skin skin;

    City city;

    private Label fundsLabel;
    private Label popLabel;
    private Label nameLabel;
    public Label timeLabel;
    public Label endLabel;

    private Image cursor;


    private long startTime = System.currentTimeMillis();

    public Hud(SpriteBatch sb, City city) {
        this.city = city;

        viewport = new FitViewport(NeonCity.V_WIDTH, NeonCity.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        try {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        } catch (Exception e) {
            skin = new Skin();
        }
        cursor = new Image(new Texture("cursor.png"));

        currentState = State.DRAG;

        sideButtons();

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("pixel.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        Label.LabelStyle style = new Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.WHITE);

        fundsLabel = new Label("$" + String.format("%07d", city.getFunds()), style);
        popLabel = new Label(String.format("%08d", city.getPopulation()), style);
        nameLabel = new Label(city.getCityName(), style);

        timeLabel = new Label("00:00", style);
        endLabel = new Label("", style);
        endLabel.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2);

        table.add(timeLabel).expandX().padTop(10);
        table.add(nameLabel).expandX().padTop(10);
        table.add(fundsLabel).expandX().padTop(10);
        table.add(popLabel).expandX().padTop(10);


        stage.addActor(table);
        stage.addActor(cursor);
        stage.addActor(endLabel);
    }

    public void sideButtons() {
        int count = 0;
        ArrayList<Texture> list = new ArrayList<Texture>();
        list.add(new Texture("bulldozer.png"));
        list.add(new Texture("road.bmp"));
        list.add(new Texture("park.png"));
        list.add(new Texture("res.png"));
        list.add(new Texture("com.png"));
        list.add(new Texture("ind.png"));
        list.add(new Texture("fire.png"));
        list.add(new Texture("police.png"));
        list.add(new Texture("stadium.png"));
        list.add(new Texture("seaport.png"));
        list.add(new Texture("coal.png"));
        list.add(new Texture("nuclear.png"));
        list.add(new Texture("airport.png"));
        for (Texture t : list) {
            ImageButton button = new ImageButton(new SpriteDrawable(new Sprite(new Texture("ui.png"))));

            sprite = new Sprite(t);
            sprite.setSize(48, 48);
            icon = new SpriteDrawable(sprite);
            button = new ImageButton(icon);

            if (t.equals("bulldozer.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.BULLDOZER;
                    }
                });
            if (t.equals("road.bmp"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.ROAD;
                    }
                });
            if (t.equals("park.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.PARK;
                    }
                });
            if (t.equals("res.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.RESIDENTIAL;
                    }
                });
            if (t.equals("com.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.COMMERCIAL;
                    }
                });
            if (t.equals("ind.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.INDUSTRIAL;
                    }
                });
            if (t.equals("fire.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.FIRE;
                    }
                });
            if (t.equals("police.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.POLICE;
                    }
                });
            if (t.equals("stadium.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.STADIUM;
                    }
                });
            if (t.equals("seaport.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.SEAPORT;
                    }
                });
            if (t.equals("coal.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.COAL;
                    }
                });
            if (t.equals("nuclear.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.NUCLEAR;
                    }
                });
            if (t.equals("airport.png"))
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        currentState = State.AIRPORT;
                    }
                });
            button.setSize(50, 50);
            button.setColor(Color.DARK_GRAY);
            stage.addActor(button);
            //button.setPosition(c*50,r*50+100);    FIX
            // The currentState variable will be checked in TiledMapStage and will
            // affect the type of building placed
        }


    }
}


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println(currentState.toString());
        fundsLabel.setText("$" + String.format("%07d", city.getFunds()));
        popLabel.setText("" + String.format("%07d", city.getPopulation()));
        timeLabel.setText(""+(System.currentTimeMillis()-startTime)/1000);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //cursor.setPosition((screenX/16) * 16, ((Gdx.graphics.getHeight() - screenY)/16)*16);
        timeLabel.setText(""+(System.currentTimeMillis()-startTime)/1000);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
