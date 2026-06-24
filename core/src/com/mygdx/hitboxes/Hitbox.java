package com.mygdx.hitboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.messages.LockedInfo;
import com.mygdx.messages.ObjectInfo;
import com.mygdx.movement.BaseMovement;

public class Hitbox extends Polygon {
    private ArrayList<Tags> tags = new ArrayList<>();
    private Consumer<Collider> onHit, onLeave, onFrame;
    private BaseMovement movement;
    private LockedInfo extraInfo;
    private boolean active, registered = false;
    public final boolean isNull;

    /**
     * Creates a Hitbox with specified position, size, rotation, and form.
     * 
     * @param anchor   the hitbox's anchor coordinates.
     * @param degrees  specifies the hitbox's rotation.
     * @param vertices an array whose elements in pairs represent the x and y of the
     *                 polygon's vertices.
     */
    public Hitbox(Vector2 anchor, int degrees, float[] vertices, boolean active) {
        super(vertices);
        movement = new BaseMovement(getCentroid(new Vector2()));
        setOrigin(movement.center.x, movement.center.y);
        movement.anchor(anchor);
        setPosition();
        setRotation(degrees);
        this.tags = new ArrayList<>();
        this.active = active;
        isNull = false;
    }

    /**
     * Creates a box Hitbox with specified position, size, and rotation.
     * 
     * @param anchor  the hitbox's anchor coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the hitbox's rotation.
     */
    public Hitbox(Vector2 anchor, float width, float height, int degrees, boolean active) {
        this(anchor, degrees, new float[] { 0, 0, width, 0, width, height, 0, height }, active);
    }

    /**
     * Creates a box Hitbox with specified position and size.
     * 
     * @param anchor  the hitbox's anchor coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     */
    public Hitbox(Vector2 anchor, float width, float height, boolean active) {
        this(anchor, width, height, 0, active);
    }

    /**
     * Creates a voided Hitbox to modify with no consequences before creating the
     * actual Hitbox.
     */
    public Hitbox() {
        super();
        isNull = true;
    }

    public void setPosition() {
        Vector2 worldCoords = movement.getWorldCoords();
        super.setPosition(worldCoords.x, worldCoords.y);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        movement.angle.setAngleDeg(degrees);
    }

    @Override
    public void rotate(float degrees) {
        super.rotate(degrees);
        movement.angle.rotateDeg(degrees);
    }

    public void setOffset(float x, float y, float angle) {
        setOffset(new Vector2(x, y), angle);
    }

    public void setOffset(Vector2 offset, float angle) {
        movement.offset(offset, angle);
        setOrigin(movement.origin.x, movement.origin.y);
        setPosition();
    }

    public boolean isHit(Collider r, boolean activate) {
        if (!active) {
            HitboxHandler.get().setContact(r, this, false);
            return false;
        }
        boolean collision = Intersector.overlapConvexPolygons(r, this);
        if (!activate)
            return collision;
        if (!collision)
            HitboxHandler.get().removeContact(r, this);
        else {
            HitboxHandler.get().storeContact(r, this);
            HitboxHandler.get().setContact(r, this, true);
        }
        
        onFrame(r);
        r.onFrame(this);
        return collision;
    }

    public void onHit(Collider r) {
        if (onHit != null)
            onHit.accept(r);
    }

    public void onLeave(Collider r) {
        if (onLeave != null)
            onLeave.accept(r);
    }

    public void onFrame(Collider r) {
        if (onFrame != null)
            onFrame.accept(r);
    }

    public boolean touching(Collider r) {
        return HitboxHandler.get().getContact(r, this);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean containsTag(Tags tag) {
        return tags.contains(tag);
    }

    public ArrayList<Tags> getTags() {
        return tags;
    }

    public void setTags(Tags... tags) {
        this.tags.clear();
        this.tags.addAll(Arrays.asList(tags));
    }

    public LockedInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ObjectInfo extraInfo) {
        if (this.extraInfo != null)
            return;

        this.extraInfo = new LockedInfo(extraInfo);
    }

    public void setOnHit(Consumer<Collider> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(Consumer<Collider> onLeave) {
        this.onLeave = onLeave;
    }

    public void setOnFrame(Consumer<Collider> onFrame) {
        this.onFrame = onFrame;
    }

    /***
     * Register method to add the HitBox to the Event handler with a check.
     * 
     * @return {@code true} if the HitBox has been added.
     */
    public boolean register() {
        if (isNull || registered)
            return false;
        if (tags.isEmpty())
            tags.add(Tags.ALL);
        HitboxHandler.get().registerHitbox(this);
        registered = true;
        return true;
    }

    /***
     * Register method to remove the HitBox to the Event handler with a check.
     * 
     * @return {@code true} if the HitBox has been removed.
     */
    public boolean unregister() {
        if (isNull || !registered)
            return false;
        HitboxHandler.get().unRegisterHitbox(this);
        registered = false;
        return true;
    }
}
