package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.sprites.MainShip;
import com.mygdx.game.sprites.Star;


public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private Star[] stars;
    private MainShip ship;
    private BulletPool bulletPool;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.jpg");
        atlas = new TextureAtlas(Gdx.files.internal("textures/mainAtlas.pack"));
        bulletPool = new BulletPool();
        initSprites();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        ship.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        ship.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch,pointer,button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        ship.touchUp(touch,pointer,button);
        return false;
    }
    private void initSprites() {
        try {
            background = new Background(bg);
            ship = new MainShip(atlas, bulletPool);
            stars = new Star[STAR_COUNT];
            for (int i = 0; i < STAR_COUNT; i++) {
                stars[i] =  new Star(atlas);
            }
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
    }
    private void update(float delta){
        for (Star star : stars) {
            star.update(delta);
        }
        ship.update(delta);
        bulletPool.updateActiveSprites(delta);
    }
    public void freeAllDestroyed(){
        bulletPool.freeAllDestroyActiveObject();
    }
    private void draw(){
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        ship.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }
}
