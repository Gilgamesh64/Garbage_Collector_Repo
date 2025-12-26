package com.mygdx;

import java.util.EnumMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public class AnimationManager {
    private EnumMap<ResourceEnum, Animation<TextureRegion>> animationMap = new EnumMap<>(ResourceEnum.class);

    private ResourceEnum currentAnimation;
    private TextureRegion currentFrame;

    private float stateTime = 0f;

    private boolean playOnce = false;
    private boolean finishedOnce = false;

    private boolean shouldNotDoFirstPlay = false;
    private boolean updatedOnce = false;

    private boolean paused = false;
    private boolean alreadyPausedOnce = false;
    private GameActor pauser = new GameActor();

    private float defaultDelay;
    private float currentDelay;

    /**
     * Creates an AnimationManager.
     * 
     * @param width         width of each frame
     * @param animationRate default frame duration
     * @param delay         default delay before replaying animation
     * @param playOnce      if true, the animation plays only once
     * @param textures      resource list for the animation
     */
    public AnimationManager(float animationRate, float delay, boolean playOnce, ResourceEnum... textures) {
        this.playOnce = playOnce;
        for (ResourceEnum e : textures) {
            Texture texture = RM.get().getTexture(e);
            int FRAME_COLS = texture.getWidth() / e.frameCount * 32;

            TextureRegion[][] matrix = TextureRegion.split(
                    texture,
                    texture.getWidth() / FRAME_COLS,
                    texture.getHeight());

            Animation<TextureRegion> animation = new Animation<>(
                    e.animationRate != -1 ? e.animationRate : 0.2f,
                    matrix[0]);
            animation.setPlayMode(playOnce ? PlayMode.NORMAL : PlayMode.LOOP);
            animationMap.put(e, animation);
        }

        currentAnimation = textures[0];
        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime);

        defaultDelay = delay;
        currentDelay = currentAnimation.delay != -1 ? currentAnimation.delay : defaultDelay;

        GCStage.get().addActor(pauser);
    }

    public AnimationManager(TextureEnum textures) {
        this(textures.getAnimationRate(), textures.getDelay(), false, textures.getResourceList());
    }

    public AnimationManager(ResourceEnum atlas, TextureEnum textures) {
        this(atlas, textures.getAnimationRate(), textures.getDelay(), false, textures.getResourceList());
    }

    public AnimationManager(ResourceEnum atlas, float animationRate, float delay, boolean playOnce, ResourceEnum... textures) {
        this.playOnce = playOnce;
        for (ResourceEnum e : textures) {
            TextureAtlas.AtlasRegion region = RM.get().getAtlas(atlas).findRegion(e.label);
            if (region == null) {
                throw new RuntimeException("Region not found: " + e.label);
            }

            int frameWidth = region.getRegionWidth() / e.frameCount;
            int frameHeight = region.getRegionHeight();

            // split horizontally into frames
            TextureRegion[] frames = new TextureRegion[e.frameCount];
            for (int i = 0; i < e.frameCount; i++) {
                frames[i] = new TextureRegion(region, i * frameWidth, 0, frameWidth, frameHeight);
            }

            Animation<TextureRegion> anim = new Animation<>(e.animationRate != -1 ? e.animationRate : animationRate,
                    frames);
            anim.setPlayMode(playOnce ? PlayMode.NORMAL : PlayMode.LOOP);

            animationMap.put(e, anim);

        }

        currentAnimation = textures[0];
        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime);

        defaultDelay = delay;
        currentDelay = currentAnimation.delay != -1 ? currentAnimation.delay : defaultDelay;

        GCStage.get().addActor(pauser);

    }

    /** @return current frame in the animation */
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    /** updates currentFrame state */
    public void updateAnimation(float delta) {
        pauser.act(delta);

        if (paused || finishedOnce || (shouldNotDoFirstPlay && !updatedOnce))
            return;

        stateTime += delta; // Advances the animation
        Animation<TextureRegion> ani = animationMap.get(currentAnimation);

        // Handle delayed pause at first frame
        if (currentDelay != 0 && ani.getKeyFrameIndex(stateTime) == 0 && !alreadyPausedOnce) {
            pauser.addAction(
                    Actions.sequence(
                            Actions.delay(currentDelay),
                            Actions.run(() -> {
                                paused = false;
                                stateTime = 0;
                            })));
            alreadyPausedOnce = true;
            paused = true;
        }

        // Reset flag when we move off frame 0
        if (ani.getKeyFrameIndex(stateTime) != 0) {
            alreadyPausedOnce = false;
        }

        // Stop if animation should only play once and has finished
        if (playOnce && ani.isAnimationFinished(stateTime)) {
            finishedOnce = true;
            currentFrame = ani.getKeyFrame(ani.getAnimationDuration() - 0.0001f); // freeze on last frame
            return;
        }

        currentFrame = ani.getKeyFrame(stateTime, playOnce);
    }

    public void setCurrentAnimation(ResourceEnum ani) {

        
        currentAnimation = ani;
        currentDelay = ani.delay != -1 ? ani.delay : defaultDelay;
        pauser.clearActions();
        stateTime = 0;
        paused = false;
        alreadyPausedOnce = false;
        finishedOnce = false;
        updatedOnce = true;

        Animation<TextureRegion> animation = animationMap.get(currentAnimation);
        animation.setPlayMode(playOnce ? PlayMode.NORMAL : PlayMode.LOOP);
    }

    public void shouldNotDoFirstPlay(){
        shouldNotDoFirstPlay = true;
    }

    /**
     * @return true if the animation has completed (only relevant if playOnce =
     *         true)
     */
    public boolean isFinishedOnce() {
        return finishedOnce;
    }

    public float getWidth(){
        return animationMap.get(currentAnimation).getKeyFrame(stateTime).getRegionWidth();
    }

    public float getHeight(){
        return animationMap.get(currentAnimation).getKeyFrame(stateTime).getRegionHeight();
    }
}
