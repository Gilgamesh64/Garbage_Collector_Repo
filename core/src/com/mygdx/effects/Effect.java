package com.mygdx.effects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.AnimationManager;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.resources.ResourceEnum;

public class Effect extends GameActor {

    private boolean shouldDoOnce;

    /**
     * creates an effect that lasts for a certain time
     * @param texture
     * @param x
     * @param y
     * @param duration
     */
    public Effect(ResourceEnum texture, float x, float y, float duration){
        setCoords(x, y);
        float animationRate = texture.animationRate != -1 ? texture.animationRate : 0.2f;
        float delay = texture.delay != -1 ? texture.delay : 0f;
        animationManager = new AnimationManager(ResourceEnum.EFFECTS, animationRate, delay, true, texture);

        this.addAction(Actions.sequence(
            Actions.delay(duration),
            Actions.run(this::remove)
        ));
    }

    /**
     * creates an effect that plays its animation just one time
     * @param texture
     * @param x
     * @param y
     */
    public Effect(ResourceEnum texture, float x, float y){
        setCoords(x, y);
        float animationRate = texture.animationRate != -1 ? texture.animationRate : 0.2f;
        float delay = texture.delay != -1 ? texture.delay : 0f;
        animationManager = new AnimationManager(ResourceEnum.EFFECTS, animationRate, delay, true, texture);

        shouldDoOnce = true;
    }
    

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());

        if(shouldDoOnce && animationManager.isFinishedOnce()) remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animationManager.updateAnimation(delta);
    }
}
