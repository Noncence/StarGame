package com.mygdx.game.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.ScaledButton;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.screen.GameScreen;

public class ButtonPlay extends ScaledButton {

    private final Game game;


    public ButtonPlay(TextureAtlas atlas, Game game) throws GameException {
        super(atlas.findRegion("play"));
        this.game = game;
    }
    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.222f);
        setLeft(worldBounds.getLeft() + 0.05f);
        setBottom(worldBounds.getBottom() + 0.05f);
    }
    @Override
    public void action() {
    game.setScreen(new GameScreen());
    }
}
