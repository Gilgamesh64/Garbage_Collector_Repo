package com.mygdx.movement;

import com.badlogic.gdx.math.Vector2;

public class BaseMovement {
    public Vector2 position = new Vector2(),
            angle = new Vector2(1, 0),
            velocity = new Vector2(),
            origin = new Vector2(),
            center = new Vector2(),
            coords = new Vector2(),
            centerCoords = new Vector2(),
            anchor = new Vector2();

    /**
     * Used for movement each frame.
     */
    public float speed = 0f;

    private boolean anchored = false;

    public BaseMovement(Vector2 center) {
        this(center.x, center.y);
    }

    public BaseMovement(float centerX, float centerY) {
        center.x = centerX;
        center.y = centerY;
        align();
        unAnchor();
    }

    public BaseMovement() {
    }

    public void init(float speed, float angle) {
        this.angle.setAngleDeg(angle);
        this.speed = speed;
        velocity.set(speed, 0).setAngleDeg(angle);
    }

    /*
    public void link(BaseMovement bm) {
        position = bm.position;
        angle = bm.angle;
        velocity = bm.velocity;
        anchor(bm.anchor);
    }
    */

    /**
     * Align the position to be centered.
     */
    public void align() {
        position.sub(center);
        recalcOrigin();
        updateCoords();
    }

    public void move(float delta) {
        velocity.setAngleDeg(angle.angleDeg());
        Vector2 movement = velocity.cpy().scl(delta);
        position.add(movement);
        recalcOrigin();
        updateCoords();
    }

    public void mutiply(float delta) {
        velocity.setAngleDeg(angle.angleDeg());
        Vector2 movement = velocity.scl(speed).cpy().scl(delta);
        position.add(movement);
        recalcOrigin();
        updateCoords();
    }

    public void offset(Vector2 offset, float angle) {
        /*Vector2 angledOff = new Vector2(offset.x, 0).setAngleDeg(angle);
        position.add(angledOff);*/
        Vector2 angledX = new Vector2(offset.x, 0).setAngleDeg(angle);
        Vector2 angledY = new Vector2(offset.y, 0).setAngleDeg(angle + 90);
        position.add(angledX).add(angledY);
        recalcOrigin();
        updateCoords();
    }

    private void updateCoords() {
        getCenterWorldCoords();
        getWorldCoords();
    }

    public Vector2 getCenterWorldCoords() {
        centerCoords.set(anchor.x + position.x + center.x, anchor.y + position.y + center.y);
        return centerCoords;
    }

    public Vector2 getWorldCoords() {
        coords.set(anchor.x + position.x, anchor.y + position.y);
        return coords;
    }

    /**
     * Anchors this movement to an anchor's world position.
     *
     * @param anchor The anchor's world position.
     */
    public void anchor(Vector2 anchor) {
        this.anchor = anchor;
        anchored = true;
        recalcOrigin();
        updateCoords();
    }

    public void recalcOrigin() {
        if (!anchored)
            return;

        origin.set(-position.x, -position.y);
    }

    public void unAnchor() {
        origin.set(center);
        anchor = new Vector2(0, 0);
        anchored = false;
        updateCoords();
    }
}
