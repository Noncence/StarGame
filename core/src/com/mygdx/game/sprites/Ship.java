package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;

public class Ship extends Sprite {

    private static final float V_LEN = 0.01f;
    private Vector2 touch;
    private Vector2 v;
    private final Vector2 v1 = new Vector2(0.01f, 0);
    private final Vector2 v2 = new Vector2(0, 0.01f);
    private Vector2 buff;
    private boolean flag;
    private int keycode;
    private Rect worldBounds;

    public Ship(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("my_ship"));
        touch = new Vector2();
        v = new Vector2();
        buff = new Vector2();
        pos.set(0,-0.35f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.2f);
    }

    /**
     * 21 - left
     * 22 - right
     * 19 - up
     * 20 - down
     */

    @Override
    public void update(float delta) {
        buff.set(touch);
        if (buff.sub(pos).len() > V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch);
        }
        // реализация движения кнопками до краёв экрана(криво работает одновременно с мышью)

        if (flag && keycode == 21){
            if (getLeft() + halfWidth < worldBounds.getLeft()) {
               flag = false;
            } else {
             pos.sub(v1);}
        }
        if (flag && keycode == 22){
            if (getRight() - halfWidth > worldBounds.getRight()) {
                flag = false;
            } else {
            pos.add(v1);}
        }
        if (flag && keycode == 19){
            if (getTop()  > worldBounds.getTop()) {
                flag = false;
            } else {
            pos.add(v2);}
        }
        if (flag && keycode == 20){
            if (getBottom() + halfHeight < worldBounds.getBottom()) {
                flag = false;
            } else {
            pos.sub(v2);}
        }

    }

    public boolean keyDown(int keycode) {
        flag = true;
        this.keycode = keycode;
        return false;
    }

    public boolean keyUp(int keycode) {
        flag = false;
        return false;
    }
}
