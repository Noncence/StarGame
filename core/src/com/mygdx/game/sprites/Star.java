package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class Star extends Sprite {
    private static final  float HEIGHT = 0.03f;
    protected final Vector2 v;
    private float animatedInterval = 0.6f;
    private float animatedTimer;
    private Rect worldBounds;

    public Star(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("star"));
        float vx = Rnd.nextFloat(-0.005f, 0.005f);
        float vy = Rnd.nextFloat(-0.05f, -0.1f);
        v = new Vector2(vx,vy);
        animatedTimer = Rnd.nextFloat(0, 0.5f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        float posX = Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = Rnd.nextFloat(worldBounds.getBottom(),worldBounds.getTop());
        this.pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        checkBounds();
        animatedTimer += delta;
        if (animatedTimer >= animatedInterval){
            animatedTimer = 0;
            setHeightProportion(HEIGHT);
        } else {
            setHeightProportion(getHeight() + 0.0001f);
        }

    }
    public void checkBounds() {
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop());
        }
        if (getBottom() > worldBounds.getTop()) {
            setTop(worldBounds.getBottom());
        }
    }
}
