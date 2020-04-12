package com.mygdx.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;

public class Explosion extends Sprite {

    private static final float ANIMATE_INTERVAL = 0.017f;
    private float animateTimer;
    private Sound explosionSound;

    public Explosion(TextureAtlas atlas, Sound explosionSound) throws GameException {
        super(atlas.findRegion("explosion"), 9, 9, 74);
        this.explosionSound = explosionSound;
    }

    public void set(Vector2 pos, float height){
        this.pos.set(pos);
        setHeightProportion(height);
        explosionSound.play(0.1f);
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= ANIMATE_INTERVAL){
            animateTimer = 0;
            if (++frame == regions.length){
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}
