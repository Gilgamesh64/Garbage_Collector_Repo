package com.mygdx.hitboxes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.messages.LockedInfo;
import com.mygdx.messages.ObjectInfo;
import com.mygdx.movement.BaseMovement;

public class Collider extends Polygon {
    private ArrayList<Tags> tags, searchTags;
    private Consumer<Hitbox> onHit, onLeave, onFrame;
    private BaseMovement movement;
    private LockedInfo extraInfo;
    private boolean registered = false;
    public final boolean isNull;

    /**
     * Creates a Collider with specified position, size, rotation, and form.
     * 
     * @param anchor   the colliders' anchor coordinates.
     * @param degrees  specifies the colliders' rotation.
     * @param vertices an array whose elements in pairs represent the x and y of
     *                 the polygon's vertices.
     */
    public Collider(Vector2 anchor, float degrees, float[] vertices) {
        super(vertices);
        movement = new BaseMovement(getCentroid(new Vector2()));
        setOrigin(movement.center.x, movement.center.y);
        movement.anchor(anchor);
        setPosition();
        setRotation(degrees);
        this.tags = new ArrayList<>();
        this.searchTags = new ArrayList<>();
        isNull = false;
    }

    /**
     * Creates a box Collider with specified position, size, and rotation.
     * 
     * @param anchor  the colliders' anchor coordinates.
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the colliders' rotation.
     */
    public Collider(Vector2 anchor, float width, float height, float degrees) {
        this(anchor, degrees, new float[] { 0, 0, width, 0, width, height, 0, height });
    }

    /**
     * Creates a box Collider with specified position and size.
     * 
     * @param anchor the colliders' anchor coordinates.
     * @param width  as large as the sea!
     * @param height as tall as the sky!
     */
    public Collider(Vector2 anchor, float width, float height) {
        this(anchor, width, height, 0);
    }

    /**
     * Creates a voided Collider to modify with no consequences of creating the
     * actual Collider.
     */
    public Collider() {
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

    public void onHit(Hitbox h) {
        if (onHit != null)
            onHit.accept(h);
    }

    public void onLeave(Hitbox h) {
        if (onLeave != null)
            onLeave.accept(h);
    }

    public void onFrame(Hitbox h) {
        if (onFrame != null)
            onFrame.accept(h);
    }

    public boolean touching(Hitbox h) {
        return HitboxHandler.get().getContact(this, h);
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

    public boolean containsSearchTag(Tags tag) {
        return searchTags.contains(tag);
    }

    public ArrayList<Tags> getSearchTags() {
        return searchTags;
    }

    public void setSearchTags(Tags... searchTags) {
        this.searchTags.clear();
        this.searchTags.addAll(Arrays.asList(searchTags));
    }

    public LockedInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ObjectInfo extraInfo) {
        if (this.extraInfo != null)
            return;

        this.extraInfo = new LockedInfo(extraInfo);
    }

    public void setOnHit(Consumer<Hitbox> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(Consumer<Hitbox> onLeave) {
        this.onLeave = onLeave;
    }

    public void setOnFrame(Consumer<Hitbox> onFrame) {
        this.onFrame = onFrame;
    }

    /***
     * Register method to add the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been added.
     */
    public boolean register() {
        if (isNull || registered)
            return false;
        if (tags.isEmpty())
            tags.add(Tags.NONE);
        if (searchTags.isEmpty())
            tags.add(Tags.ALL);
        HitboxHandler.get().registerCollider(this);
        registered = true;
        return true;
    }

    /***
     * Register method to remove the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been removed.
     */
    public boolean unregister() {
        if (isNull || !registered)
            return false;
        HitboxHandler.get().unRegisterCollider(this);
        registered = false;
        return true;
    }
}
