package com.luis.neoncity.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.luis.neoncity.Buildings.*;
import com.luis.neoncity.Scenes.Hud;
import com.luis.neoncity.Tools.City;
import com.luis.neoncity.Tools.Tile;

/**
 * Created by Luis on 5/11/2017.
 */

public class TiledMapStage extends Stage implements InputProcessor{
	private Vector3 lastTouch;
	private Stage stage;
	private City city;
	private Hud hud;

	public TiledMapStage(Viewport viewport, TiledMap tiledMap, City city, Hud hud) {
		super(viewport);
		this.city = city;
		this.hud = hud;

		lastTouch = new Vector3();
		stage = this;

		for (MapLayer layer : tiledMap.getLayers()) {
			TiledMapTileLayer tiledLayer = (TiledMapTileLayer) layer;
			for (int x = 0; x < tiledLayer.getWidth(); x++) {
				for (int y = 0; y < tiledLayer.getHeight(); y++) {
					TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
					if (cell.getTile().getProperties().containsKey("water"))
						city.tiles[x][y] = new Tile(false);
					else
						city.tiles[x][y] = new Tile(true);
				}
			}
		}
	}

	private void moveCamera( int screenX, int screenY ) {
		Vector3 newPosition = getNewCameraPosition(screenX, screenY);
		//if(!cameraOutOfLimit( new Vector3(stage.getCamera().position.add(newPosition))))
			stage.getCamera().translate(newPosition);
		lastTouch.set(screenX, screenY, 0);
	}

	private Vector3 getNewCameraPosition(int x, int y) {
		Vector3 newPosition = lastTouch;
		newPosition.sub(x, y, 0);
		newPosition.y = -newPosition.y;
		return newPosition;
	}
	private boolean cameraOutOfLimit( Vector3 position ) {
		int x_left_limit = 0;
		int x_right_limit = 4096 - (1366 / 2);
		int y_bottom_limit = 0;
		int y_top_limit = 4096 - (768 / 2);

		if( position.x < x_left_limit || position.x > x_right_limit )
			return true;
		else if( position.y < y_bottom_limit || position.y > y_top_limit )
			return true;
		else
			return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		lastTouch.set(screenX, screenY, 0);
		Vector3 test = stage.getCamera().unproject(new Vector3(screenX, screenY, 0));
		System.out.println("(" + (int) (test.x / 16) + ", " + (int) (test.y / 16) + ")");
		Vector3 pos = new Vector3((int)test.x / 16 * 16, (int) (test.y / 16) * 16, 0);
		Building res = null;

		if (hud.currentState != Hud.State.DRAG){
			if(hud.currentState == Hud.State.BULLDOZER) {
				if (city.tiles[(int) pos.x / 16][(int) pos.y / 16].getBuilding() != 0)
					try {
						city.getBuildings().remove(city.tiles[(int) pos.x / 16][(int) pos.y / 16].getBuilding());

					} catch (Exception e) {
						System.out.println("Oh NO!!! This bug will be fixed soon!");

					}
			}
			else if ((hud.currentState == Hud.State.ROAD)&&(city.getFunds()>=10)) {
				res = new Road(pos, city, true);
				city.setFunds(city.getFunds()-res.cost);
			}
			else if (hud.currentState == Hud.State.RAIL)
				res = new Rail(pos, city, true);
			else if (hud.currentState == Hud.State.POWER)
				res = new PowerLine(pos, city, true);
			else if (hud.currentState == Hud.State.PARK)
				res = new Park(pos, city, true);
			else if (hud.currentState == Hud.State.RESIDENTIAL)
				res = new ResidentialBuilding(pos, city, true);
			else if (hud.currentState == Hud.State.COMMERCIAL)
				res = new RecreationalBuilding(pos, city, true);
			else if (hud.currentState == Hud.State.INDUSTRIAL)
				res = new IndustrialBuilding(pos, city, true);
            else if (hud.currentState == Hud.State.FIRE)
				res = new Fire(pos, city, true);
			else if (hud.currentState == Hud.State.POLICE)
				res = new Police(pos, city, true);
			else if (hud.currentState == Hud.State.STADIUM)
				res = new Stadium(pos, city, true);
			else if (hud.currentState == Hud.State.SEAPORT)
				res = new Seaport(pos, city, true);
			else if (hud.currentState == Hud.State.COAL)
				res = new Coal(pos, city, true);
			else if (hud.currentState == Hud.State.NUCLEAR)
				res = new Nuclear(pos, city, true);
			else if (hud.currentState == Hud.State.AIRPORT)
				res = new Airport(pos, city, true);
			}
			if (res != null && city.getFunds() >= res.cost && isPlaceable(pos, res.size)) {
        		res.setTilesUnusable();
				city.getBuildings().add(res);
				city.tiles[(int)pos.x/16][(int)pos.y/16].setBuilding(city.getBuildings().size() - 1);
				city.setFunds(city.getFunds() - res.cost);
				res.addFunction();
				System.out.println("added building");
			}

		return super.touchDown(screenX, screenY, pointer, button);
	}

	public boolean isPlaceable(Vector3 pos, int size){
        for (int x = (int)pos.x; x < (pos.x+(size*16)); x += 16)
            for (int y = (int)pos.y; y < (pos.y+(size*16)); y += 16)
                if(!city.tiles[(int)(x/16)][(int)(y/16)].isUsable())
                	return false;
		return true;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		moveCamera(screenX, screenY);

		return false;
	}
}
