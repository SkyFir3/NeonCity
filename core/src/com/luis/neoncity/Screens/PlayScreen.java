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
import com.luis.neoncity.Buildings.Building;
import com.luis.neoncity.Buildings.ResidentialBuilding;
import com.luis.neoncity.Input.TiledMapStage;
import com.luis.neoncity.NeonCity;
import com.luis.neoncity.Scenes.Hud;
import com.luis.neoncity.Tools.City;

/**
 * Created by Luis on 5/9/2017.
 */

public class PlayScreen implements Screen {
    private NeonCity game;
    public City city;

    public  OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    public Stage stage;
    SpriteBatch spriteBatch;


    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(NeonCity game, SpriteBatch sb, City city) {
        this.game = game;
        this.city = city;
        spriteBatch = sb;

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(NeonCity.V_WIDTH, NeonCity.V_HEIGHT, gameCam);

        hud = new Hud(sb, city);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("TestMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getScreenWidth() / 2, gamePort.getScreenHeight() / 2, 0);

        stage = new TiledMapStage(gamePort, map, city);

        InputMultiplexer im = new InputMultiplexer(hud, stage);
        Gdx.input.setInputProcessor(im);
    }

    @Override
    public void show() {

    }

    public void update(float dt) {
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        for(ResidentialBuilding b : city.getBuildings())
        b.draw(spriteBatch);
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
