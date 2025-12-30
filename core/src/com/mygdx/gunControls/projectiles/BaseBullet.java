package com.mygdx.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.messages.ObjectInfo;
import com.mygdx.GCStage;
import com.mygdx.effects.Effect;
import com.mygdx.hitboxes.Collider;
import com.mygdx.hitboxes.Tags;
import com.mygdx.movement.BaseMovement;
import com.mygdx.resources.enums.TextureEnum;

public class BaseBullet extends Actor {
    protected Sprite s;
    protected BaseMovement movement;
    protected Vector2 origin;
    protected float distance;
    protected Collider collider = new Collider();
    protected ObjectInfo info;

    public BaseBullet(Sprite s, Vector2 origin, float speed, float distance, float rotation, boolean ally) {
        this.s = s;
        this.distance = distance;
        this.origin = new Vector2(origin);

        setSize(s.getWidth(), s.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);

        movement = new BaseMovement(getOriginX(), getOriginY());
        movement.offset(origin, 0);
        movement.init(speed, rotation);
        s.setOrigin(movement.origin.x, movement.origin.y);
        s.setRotation(rotation);

        collider = new Collider(movement.centerCoords, getWidth(), getHeight(), rotation);
        collider.setTags(Tags.PROJECTILE);
        collider.setSearchTags(ally ? Tags.ENEMY : Tags.PLAYER);
        collider.setOnHit(hitbox -> {
            Vector2 centerWorldCoords = movement.getCenterWorldCoords();
            GCStage.get().addActor(new Effect(TextureEnum.EXPLOSION, centerWorldCoords.x, centerWorldCoords.y));
            delete();
        });
        collider.register();

        Vector2 worldCoords = movement.getWorldCoords();
        setPosition(worldCoords.x, worldCoords.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        s.draw(batch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        movement.move(delta);
        Vector2 worldCoords = movement.getWorldCoords();
        setPosition(worldCoords.x, worldCoords.y);

        if (origin.dst(worldCoords) > distance)
            delete();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        s.setPosition(getX(), getY());
        collider.setPosition();
    }

    protected void delete() {
        this.remove();
        collider.unregister();
    }

    public void flip(boolean x, boolean y) {
        s.flip(x, y);
    }

    public void setOffset(float x, float y, float angle) {
        setOffset(new Vector2(x, y), angle);
    }

    public void setOffset(Vector2 offset, float angle) {
        movement.offset(offset, angle);
        s.setOrigin(movement.origin.x, movement.origin.y);
        
        Vector2 worldCoords = movement.getWorldCoords();
        setPosition(worldCoords.x, worldCoords.y);
        //collider.setOffset(offset, angle);
    }

    /**
     * Creates the additional info object instance with the specified maps to
     * initialize, then, attaches it to the collider.
     * 
     * <pre>
     *1: Boolean Map.
     *2: Integer Map.
     *3: Float Map.
     *4: String Map.
     * </pre>
     * 
     * @param selector array of numbers.
     */
    protected void attachInfo(int... selector) {
        info = new ObjectInfo(selector);
        collider.setExtraInfo(info);
    }
}
