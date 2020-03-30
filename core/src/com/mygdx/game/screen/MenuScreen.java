package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.sprites.Ship;


public class MenuScreen extends BaseScreen {
    private Texture bg;
    private Background background;
    private Vector2 pos;
    private Texture ships;
    private Ship ship;

    @Override
    public void show() {
        super.show();
        bg = new Texture("font.jpg");
        try {
            background = new Background(bg);
        } catch (GameException e) {
            e.printStackTrace();
        }
        ships = new Texture("ship.png");
        try {
            ship = new Ship((ships));
        } catch (GameException e) {
            e.printStackTrace();
        }
        pos = new Vector2();


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
        super.dispose();
    }
    private void update(float delta){
    ship.update(delta);
    }
    private void draw (){
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        ship.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        ship.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch,pointer,button);
        return false;

    }
}
