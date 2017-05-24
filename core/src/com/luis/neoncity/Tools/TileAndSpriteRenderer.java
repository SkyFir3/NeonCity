package com.luis.neoncity.Tools;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.luis.neoncity.Buildings.Building;

/**
 * Created by Jacob on 5/17/2017.
 */

public class TileAndSpriteRenderer extends OrthogonalTiledMapRenderer {
    City city;
    public TileAndSpriteRenderer(TiledMap map, City city) {
        super(map);
        this.city = city;
    }

    @Override
    public void render() {
        super.render();

        this.getBatch().begin();
        for(Building b : city.getBuildings()) {
            b.sprite.draw(this.getBatch(), 1f);
            b.pop.draw(this.getBatch(), 1f);
        }
        this.getBatch().end();
    }
}
