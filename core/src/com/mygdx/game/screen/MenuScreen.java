package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture ship, background;
    private Vector2 pos, pos2;
    private Vector2 v;

    @Override
    public void show() {

        background = new Texture("font.jpg");
        ship = new Texture("ship.png");
        pos = new Vector2(5,5);
        pos2 = new Vector2(0,0);
        v = new Vector2(0,0);
        super.show();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos2.set(screenX - ship.getWidth()/2, (Gdx.graphics.getHeight()-screenY) - ship.getHeight()/2);
        v.set(0,0);
        v.set(pos2.cpy().sub(pos));
        v.nor().scl(1.5f);
        System.out.println("v = " + v);
        System.out.println("pos = " + pos);
        System.out.println("pos2 = " + pos2);
        return false;
    }

    @Override
    public void render(float delta) {
    update(delta);
    draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        ship.dispose();
        super.dispose();
    }
    private void update(float delta){
       if  (pos2.len() - pos.len() >  -1.0f && pos2.len() - pos.len() < 1.0f){
            v.set(0,0);
            pos.set(pos2);
       } else {
            pos.add(v);}
    }
    private void draw (){
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(ship, pos.x , pos.y );
        batch.end();
    }
}
