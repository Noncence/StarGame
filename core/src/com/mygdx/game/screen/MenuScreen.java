package com.mygdx.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.sprites.ButtonExit;
import com.mygdx.game.sprites.ButtonPlay;
import com.mygdx.game.sprites.MainShip;
import com.mygdx.game.sprites.Star;


public class MenuScreen extends BaseScreen {
    private static final int STAR_COUNT = 256;

    private final Game game;
    private MainShip mainShip;
    private TextureAtlas atlas;
    private TextureAtlas atlas2;
    private Texture bg;
    private Background background;
    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;
    private Star[] stars;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.jpg");
        atlas = new TextureAtlas(Gdx.files.internal("textures/menuAtlas.pack"));
        atlas2 = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.pack"));
        music.play();
        music.setLooping(true);

        initSprites();
    }


    @Override
    public void render(float delta) {
    update(delta);
    draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        buttonExit.touchDown(touch, pointer, button);
        buttonPlay.touchDown(touch, pointer, button);
        return false;

    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        buttonExit.touchUp(touch, pointer, button);
        buttonPlay.touchUp(touch, pointer, button);
        return false;
    }

    private void initSprites() {
        try {
            background = new Background(bg);
            mainShip = new MainShip(atlas2);
            stars = new Star[STAR_COUNT];
            for (int i = 0; i < STAR_COUNT; i++) {
                stars[i] =  new Star(atlas);
            }
            buttonExit = new ButtonExit(atlas);
            buttonPlay = new ButtonPlay(atlas, game);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }
    private void update(float delta){
        for (Star star : stars) {
            star.update(delta);
        }
    }
    private void draw (){
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        mainShip.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

}
