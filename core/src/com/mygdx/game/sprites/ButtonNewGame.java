package com.mygdx.game.sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.ScaledButton;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.screen.GameScreen;


public class ButtonNewGame extends ScaledButton {

    private final Game game;

    public ButtonNewGame(TextureAtlas atlas, Game game) throws GameException {
        super(atlas.findRegion("button_new_game"));
        this.game= game;

    }

    @Override
    public void resize(Rect worldBounds) {
       setHeightProportion(0.1f);
       setTop(0.15f);
    }

    @Override
    public void action() {
       game.setScreen(new GameScreen(game));
    }
}
