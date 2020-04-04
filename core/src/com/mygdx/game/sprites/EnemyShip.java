package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.math.Rnd;

public class EnemyShip extends Sprite {

    private static final float SHIP_HEIGHT = 0.3f;

    private final float moveInterval = 8f;
    private float moveTimer;
    private Rect worldBounds;
    private Vector2 v;


    public EnemyShip(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("enemyShip1"));
        float vx = Rnd.nextFloat(-0.005f, 0.005f);
        float vy = Rnd.nextFloat(-0.05f, -0.1f);
        v = new Vector2(vx,vy);
        moveTimer = Rnd.nextFloat(0.01f, 0.08f);
    }
    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SHIP_HEIGHT);
        float posX = Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight());
        float posY = worldBounds.getTop() + getHalfHeight();
        this.pos.set(posX,posY);
    }

    @Override
    public void update(float delta) {
        moveTimer += delta;
        if (moveTimer > moveInterval){
            pos.mulAdd(v, delta);

        }
        if (getTop() < worldBounds.getBottom()) {
            resize(worldBounds);
            moveTimer = Rnd.nextFloat(0.01f, 0.08f);
        }
    }
}

