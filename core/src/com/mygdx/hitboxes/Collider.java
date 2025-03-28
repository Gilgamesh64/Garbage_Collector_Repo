package com.mygdx.hitboxes;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import com.badlogic.gdx.math.Polygon;
import com.mygdx.Utils;

public class Collider extends Polygon {
    private String[] tags;
    private String stringTags;
    private String[] searchTags;
    private boolean collided;
    private BiConsumer<Collider, Hitbox> onHit;
    private BiConsumer<Collider, Hitbox> onLeave;
    private ArrayList<String> keys;
    public final boolean isNull;

    /**
     * Creates a Collider with specified position, size, rotation, tags, and form.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param x          where am I? (horizontally speaking)
     * @param y          where am I? (vertically speaking)
     * @param width      as large as the sea!
     * @param height     as tall as the sky!
     * @param degrees    specifies the collider's rotation.
     * @param tags       collider's tags to differentiate what to do on collision.
     * @param searchTags specifies what hiboxes can the collider collide with.
     * @param vertices   an array whose elements in pairs represent the x and y of
     *                   the polygon's vertices.
     */
    public Collider(float x, float y, float width, float height, float degrees, String tags, String searchTags,
            float[] vertices) {
        super(vertices);
        setOrigin(width / 2, height / 2);
        setPosition(x, y);
        setRotation(degrees);
        this.tags = tags.split(",");
        this.searchTags = searchTags.split(",");
        stringTags = tags;
        collided = false;
        keys = new ArrayList<>();
        isNull = false;
    }

    /**
     * Creates a box Collider with specified position, size, rotation, and tags.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param x          where am I? (horizontally speaking)
     * @param y          where am I? (vertically speaking)
     * @param width      as large as the sea!
     * @param height     as tall as the sky!
     * @param degrees    specifies the collider's rotation.
     * @param tags       collider's tags to differentiate what to do on collision.
     * @param searchTags specifies what hiboxes can the collider collide with.
     */
    public Collider(float x, float y, float width, float height, float degrees, String tags, String searchTags) {
        this(x, y, width, height, degrees, tags, searchTags, new float[] { 0, 0, width, 0, width, height, 0, height });
    }

    /**
     * Creates a box Collider with specified position, size, rotation, and tags.
     * Tags are a list of names separated by a comma, the String should contain no
     * spaces.
     * 
     * @param x       where am I? (horizontally speaking)
     * @param y       where am I? (vertically speaking)
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the collider's rotation.
     * @param tags    collider's tags to differentiate what to do on collision.
     */
    public Collider(float x, float y, float width, float height, float degrees, String tags) {
        this(x, y, width, height, degrees, tags, "all");
    }

    /**
     * Creates a box Collider with specified position, size, and rotation.
     * 
     * @param x       where am I? (horizontally speaking)
     * @param y       where am I? (vertically speaking)
     * @param width   as large as the sea!
     * @param height  as tall as the sky!
     * @param degrees specifies the collider's rotation.
     */
    public Collider(float x, float y, float width, float height, float degrees) {
        this(x, y, width, height, degrees, "none");
    }

    /**
     * Creates a box Collider with specified position and size.
     * 
     * @param x      where am I? (horizontally speaking)
     * @param y      where am I? (vertically speaking)
     * @param width  as large as the sea!
     * @param height as tall as the sky!
     */
    public Collider(float x, float y, float width, float height) {
        this(x, y, width, height, 0);
    }

    /**
     * Creates a voided Collider to modify with no consequences before creating the
     * actual Collider.
     */
    public Collider() {
        super();
        isNull = true;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getOriginX(), y - getOriginY());
    }

    public void onHit(Hitbox h) {
        if (onHit != null)
            onHit.accept(this, h);
    }

    public void onLeave(Hitbox h) {
        if (onLeave != null)
            onLeave.accept(this, h);
    }

    public void register(Hitbox h) {
        keys.add(h.toString());
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void clearKeys() {
        keys.clear();
    }

    public boolean containsTag(String tag) {
        return stringTags.contains(tag);
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags.split(",");
        this.stringTags = tags;
    }

    public String[] getSearchTags() {
        return searchTags;
    }

    public void setSearchTags(String searchTags) {
        this.searchTags = searchTags.split(",");
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public void setOnHit(BiConsumer<Collider, Hitbox> onHit) {
        this.onHit = onHit;
    }

    public void setOnLeave(BiConsumer<Collider, Hitbox> onLeave) {
        this.onLeave = onLeave;
    }

    /***
     * Register method to add the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been added.
     */
    public boolean register() {
        if (isNull)
            return false;
        Utils.getHitboxHandler().registerCollider(this);
        return true;
    }

    /***
     * Register method to remove the Collider to the Event handler with a check.
     * 
     * @return {@code true} if the Collider has been removed.
     */
    public boolean unregister() {
        if (isNull)
            return false;
        Utils.getHitboxHandler().unRegisterCollider(this);
        return true;
    }
}
