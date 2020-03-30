package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;


public class Ship extends Sprite {

    private static final float V_LEN = 0.01f;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 buff;

    public Ship(Texture region) throws GameException {
        super(new TextureRegion(region));
        touch = new Vector2();
        v = new Vector2();
        buff = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public void update(float delta) {
        buff.set(touch);
        if (buff.sub(pos).len() > V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
    }
}