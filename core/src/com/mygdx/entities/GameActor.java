package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.animations.AnimationManager;
import com.mygdx.movement.AutoMovementManager;

public class GameActor extends Actor implements Telegraph {

    protected final AutoMovementManager autoMovementManager = new AutoMovementManager(this);
    protected AnimationManager animationManager;

    public Vector2 center = new Vector2();


    @Override
    protected void positionChanged() {
        super.positionChanged();
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
    }

    public void setCoords(Vector2 coords) {
        setX(coords.x);
        setY(coords.y);
    }

    public void setCoords(float x, float y) {
        setX(x);
        setY(y);
    }

    public Vector2 getCoords() {
        return new Vector2(getX(), getY());
    }

    public void moveTo(Vector2 coords) {
        autoMovementManager.goTo(coords);
    }

    public void moveTo(float x, float y) {
        autoMovementManager.goTo(new Vector2(x, y));
    }

    public boolean isAutoWalking() {
        return autoMovementManager.isAnimationInProgress();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return true;
    }
}
