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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.luis.neoncity.NeonCity;
import com.luis.neoncity.Tools.City;

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
    private City city;

    public Stage stage;
    public Skin skin;

    public Label fundsLabel;
    public Label popLabel;
    public Label nameLabel;
    public Label timeLabel;
    public Label endLabel;

    private Label powLabel;
    private  Label hapLabel;
    private  Label polLabel;
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

        endLabel = new Label("", style);
        endLabel.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2);

        timeLabel = new Label("00:00",style);
        powLabel = new Label(String.format("%04d", city.getPower()), style);
        hapLabel = new Label("%03d" + city.getHappiness(), style);
        polLabel = new Label(String.format("%03d", city.getPollution()), style);


        table.add(timeLabel).expandX().padTop(10);
        table.add(nameLabel).expandX().padTop(10);
        table.add(fundsLabel).expandX().padTop(10);
        table.add(popLabel).expandX().padTop(10);
        table.row();
        table.add(powLabel).expandX();
        table.add(hapLabel).expandX();
        table.add(polLabel).expandX();
        
      stage.addActor(cursor);
        stage.addActor(endLabel);
        stage.addActor(table);
    }

    public void sideButtons() {
        int count = 0;

        for(int r = 1; r <= 8; r++) {
            for(int c = 1; c <= 2; c++)
            {
                count++;
                ImageButton button = new ImageButton(new SpriteDrawable(new Sprite(new Texture("ui.png"))));

                if(count==1) {
                    sprite = new Sprite(new Texture("bulldozer.png"));
                    sprite.setSize(48,48);
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {

                        }
                    });

                }
                if(count==2) {
                    sprite = new Sprite(new Texture("road.bmp"));
                    sprite.setSize(48,48);
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.ROAD;
                        }
                    });

                }

                if(count==3) {
                    sprite = new Sprite(new Texture("rail.png"));
                    sprite.setSize(48,48);
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.RAIL;
                        }
                    });

                }

                if(count==4) {
                    sprite = new Sprite(new Texture("road.bmp"));
                    sprite.setSize(48,48);
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.POWER;
                        }
                    });

                }

                if(count==5) {
                    sprite = new Sprite(new Texture("park.png"));
                    sprite.setSize(48,48);
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.PARK;
                        }
                    });

                }

                if(count==6) {
                    sprite = new Sprite(new Texture("res.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.RESIDENTIAL;
                        }
                    });

                }

                if(count==7) {
                    sprite = new Sprite(new Texture("com.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.COMMERCIAL;
                        }
                    });

                }

                if(count==8) {
                    sprite = new Sprite(new Texture("ind.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.INDUSTRIAL;
                        }
                    });

                }
                if(count==9) {
                    sprite = new Sprite(new Texture("fire.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.FIRE;
                        }
                    });

                }
                if(count==10) {
                    sprite = new Sprite(new Texture("police.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.POLICE;
                        }
                    });

                }
                if(count==11) {
                    sprite = new Sprite(new Texture("stadium.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.STADIUM;
                        }
                    });

                }
                if(count==12) {
                    sprite = new Sprite(new Texture("seaport.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.SEAPORT;
                        }
                    });

                }
                if(count==13) {
                    sprite = new Sprite(new Texture("coal.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            currentState = State.COAL;
                        }
                    });

                }

                if(count==14) {
                    sprite = new Sprite(new Texture("nuclear.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.NUCLEAR;
                        }
                    });

                }
                if(count==15) {
                    sprite = new Sprite(new Texture("airport.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.AIRPORT;
                        }
                    });

                }
                if(count==16) {
                    sprite = new Sprite(new Texture("cursor.png"));
                    icon = new SpriteDrawable(sprite);
                    button = new ImageButton(icon);
                    button.addListener(new ClickListener(){
                        @Override public void clicked(InputEvent event, float x, float y) {
                            currentState = State.DRAG;
                        }
                    });

                }
                button.setSize(50,50);
                button.setColor(Color.DARK_GRAY);
                stage.addActor(button);
                button.setPosition(c*50,r*50+100);
                // The currentState variable will be checked in TiledMapStage and will
                // affect the type of building placed
            }
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
        powLabel.setText(String.format("%04d", city.getPower()));
        hapLabel.setText("" + city.getHappiness());
        polLabel.setText(String.format("%03d", city.getPollution()));

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
        timeLabel.setText(""+(System.currentTimeMillis()-startTime)/1000);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
