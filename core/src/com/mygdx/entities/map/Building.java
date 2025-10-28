package com.mygdx.entities.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.AnimationManager;
import com.mygdx.Data;
import com.mygdx.GCStage;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.hitboxes.Tags;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public class Building extends GameActor {

    private Hitbox hitbox = new Hitbox();
    private float fade = 1;

    private final AnimationManager animationManager;

    public Building(Vector2 coords, ResourceEnum... textures) {
        super();
        setX(coords.x);
        setY(coords.y);

        animationManager = new AnimationManager(ResourceEnum.BUILDINGS, 0.2f, 0, false, textures);

        hitbox = new Hitbox(
                new Vector2(getX() + animationManager.getWidth() * 0.5f,
                        getY() + 16 + animationManager.getHeight() * 0.5f),
                animationManager.getWidth(), animationManager.getHeight() - 32, true);
        hitbox.setTags(Tags.BUILDING);
        hitbox.setOnHit((collider) -> {
            fade = 0.2f;
        });
        hitbox.setOnLeave((collider) -> {
            fade = 1;
        });
        hitbox.register();
        if(Data.debug) debug();
    }

    public Building(Vector2 coords, TextureEnum textures) {
        super();
        setX(coords.x);
        setY(coords.y);

        animationManager = new AnimationManager(ResourceEnum.BUILDINGS, textures);

        hitbox = new Hitbox(
                new Vector2(getX() + animationManager.getWidth() * 0.5f,
                        getY() + 16 + animationManager.getHeight() * 0.5f),
                animationManager.getWidth(), animationManager.getHeight() - 32, true);
        hitbox.setTags(Tags.BUILDING);
        hitbox.setOnHit((collider) -> {
            fade = 0.2f;
        });
        hitbox.setOnLeave((collider) -> {
            fade = 1;
        });
        hitbox.register();
        if(Data.debug) debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1, 1, 1, fade);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
        batch.setColor(1, 1, 1, 1);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }

    public void door(float x, float y, ResourceEnum door) {
        Component tmp = MapConstructor.getComponent(getX() + x, getY() + y, door);
        GCStage.get().addActor(tmp);
    }
}
