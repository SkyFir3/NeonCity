package com.luis.neoncity.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.luis.neoncity.Input.TiledMapStage;
import com.luis.neoncity.NeonCity;
import com.luis.neoncity.Scenes.Hud;
import com.luis.neoncity.Tools.City;
import com.luis.neoncity.Tools.TileAndSpriteRenderer;

/**
 * Created by Luis on 5/9/2017.
 */

public class PlayScreen implements Screen {
    private NeonCity game;
    public City city;

    private Viewport gamePort;
    private Hud hud;
    public Stage stage;
    public SpriteBatch spriteBatch;
    public  OrthographicCamera gameCam;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private long startTime = System.currentTimeMillis();
    private boolean taxesCollected;

    public PlayScreen(NeonCity game, SpriteBatch sb, City city, String mapName) {
        this.game = game;
        this.city = city;
        spriteBatch = sb;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(NeonCity.V_WIDTH, NeonCity.V_HEIGHT, gameCam);

        hud = new Hud(sb, city);

        mapLoader = new TmxMapLoader();

        if(mapName.equals("islandTest"))
            map = mapLoader.load("islandTest.tmx");
        else if(mapName.equals("TestMap"))
            map = mapLoader.load("TestMap.tmx");
        else if(mapName.equals("SimpleTest"))
            map = mapLoader.load("SimpleTest.tmx");
        else
            map = mapLoader.load("SimpleTest.tmx");

        renderer = new TileAndSpriteRenderer(map, city);
        //the stage does not render the tile map, must render sprites with custom render, this renderer does not render the stage

        gameCam.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 0);

        stage = new TiledMapStage(gamePort, map, city, hud);

        taxesCollected = true;
        InputMultiplexer im = new InputMultiplexer(hud.stage, stage,hud );
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        gameCam.update();
        renderer.setView(gameCam);
        int month = (int)(System.currentTimeMillis()-startTime)/1000;
        hud.timeLabel.setText(String.format("%1d-%4d" , (System.currentTimeMillis()-startTime)/1000/12, (System.currentTimeMillis()-startTime)/1000%12));
        hud.fundsLabel.setText("$" + String.format("%07d", city.getFunds()));
        hud.popLabel.setText("" + String.format("%07d", city.getPopulation()));
        hud.timeLabel.setText(""+(System.currentTimeMillis()-startTime)/1000);

        if(month%12 == 0 && !taxesCollected) {
            taxesCollected = true;
            city.collectTaxes();
        }
        else if(month%12 == 1 && !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!taxesCollected)
            taxesCollected = false;



        //TODO: this doesnt work, find a way to make it work
        if((System.currentTimeMillis()-startTime)/1000/12 <1)
            city.collectTaxes();

        if(city.getPopulation() >= 2500)
            hud.endLabel.setText("YOU WIN!");
        if(city.getHappiness() <= 0)
            hud.endLabel.setText("YOU LOSE!");

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
