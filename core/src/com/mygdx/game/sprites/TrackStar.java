package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.exception.GameException;


public class TrackStar extends Star {

    private final Vector2 trackingV;

    private final Vector2 sumV = new Vector2();

    public TrackStar(TextureAtlas atlas, Vector2 trackingV) throws GameException {
        super(atlas);
        this.trackingV = trackingV;
    }

    @Override
    public void update(float delta) {
        sumV.setZero().mulAdd(trackingV, 0.2f).rotate(180).add(v);
        pos.mulAdd(sumV, delta);
        checkBounds();
    }
}