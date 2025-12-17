package com.mygdx.entities.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.AnimationManager;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public class Component extends GameActor {

    protected final AnimationManager animationManager;

    public Component(Vector2 coords, ResourceEnum... textures) {
        super();
        setX(coords.x);
        setY(coords.y);

        animationManager = new AnimationManager(ResourceEnum.COMPONENTS, 0.2f, 0, false, textures);
    }

    public Component(Vector2 coords, TextureEnum textures) {
        super();
        setX(coords.x);
        setY(coords.y);

        animationManager = new AnimationManager(ResourceEnum.COMPONENTS,textures);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }
}
