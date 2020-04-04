package com.mygdx.game.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

public class MainShip extends Sprite {

    private static final float SHIP_HEIGHT = 0.18f;
    private static final float BOTTOM_MARGIN = 0.03f;
    private static final int INVALID_POINTER = -1;

    private Rect worldBounds;
    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV;

    private float interval = 0.14f;
    private float timer;

    private Sound bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));

    private final Vector2 v0;
    private final Vector2 v;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) throws GameException {
        super(atlas.findRegion("myShip"));
        this.bulletPool = bulletPool;
        bulletRegion = atlas.findRegion("bulletMainShip");
        bulletV = new Vector2(0, 0.5f);
        v0 = new Vector2(0.5f,0);
        v = new Vector2();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
       if (touch.x < worldBounds.pos.x){
           if (leftPointer != INVALID_POINTER){
               return false;
           }
           leftPointer = pointer;
           moveLeft();
       } else {
           if (rightPointer != INVALID_POINTER) {
               return false;
           }
           rightPointer = pointer;
           moveRight();
       }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
       if (pointer == leftPointer){
           leftPointer = INVALID_POINTER;
           if (rightPointer != INVALID_POINTER){
               moveRight();
           } else {
               stop();
           }
       } else if (pointer == rightPointer){
           rightPointer = INVALID_POINTER;
           if (leftPointer != INVALID_POINTER){
               moveLeft();
           } else {
               stop();
           }
       }
       return false;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SHIP_HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        timer += delta;
        if (timer > interval) {
            shoot();
            timer = 0f;
        }
       pos.mulAdd(v, delta);
       if (getLeft() + halfWidth < worldBounds.getLeft()){
           setLeft(worldBounds.getLeft() - this.halfWidth);
           stop();
       }
       if (getRight() - halfWidth > worldBounds.getRight()){
           setRight(worldBounds.getRight() + this.halfWidth);
           stop();
       }
    }

    public boolean keyDown(int keycode) {
   switch (keycode){
       case Input.Keys.A:
       case Input.Keys.LEFT:
           pressedLeft = true;
           moveLeft();
           break;
       case Input.Keys.D:
       case Input.Keys.RIGHT:
           pressedRight = true;
           moveRight();
           break;
       case Input.Keys.UP:
          shoot();
       }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
               if (pressedRight){
                   moveRight();
               } else {
                   stop();
               }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                 break;
        }
        return false;
    }
    public void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.028f, worldBounds,1);
        bulletSound.play(0.3f);
    }
    public void dispose(){
        bulletSound.dispose();
    }
    private void moveRight(){
        v.set(v0);
    }
    private void moveLeft(){
        v.set(v0).rotate(180);
    }
    private void stop(){
        v.setZero();
    }

}
