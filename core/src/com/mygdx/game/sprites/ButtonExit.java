package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.ScaledButton;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;

public class ButtonExit extends ScaledButton {
    public ButtonExit(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("exit"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.10f);
       // setRight(worldBounds.getRight() - 0.05f);
        //setBottom(worldBounds.getBottom() + 0.05f);
         setBottom(worldBounds.getBottom() + 0.30f);

    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
