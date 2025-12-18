package com.mygdx.entities.npcs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.AnimationManager;
import com.mygdx.Data;
import com.mygdx.dialogues.Dialogue;
import com.mygdx.entities.helpers.ScriptableActor;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.hitboxes.Tags;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public class NPC extends ScriptableActor {

    protected int lf = 2;

    protected Hitbox hitbox = new Hitbox();
    public Script script;
    protected String name;
    private Dialogue currentDialogue = null;

    public NPC(NPCBuilder npcBuilder) {
        super();
        setTouchable(Touchable.enabled);

        setSize(npcBuilder.size.x, npcBuilder.size.y);
        setOrigin(getWidth() / 2, getHeight() / 2);

        if(npcBuilder.atlas){
            animationManager = new AnimationManager(ResourceEnum.NPCS, npcBuilder.textureEnum);
        }
        else {
            animationManager = new AnimationManager(npcBuilder.textureEnum);
        }

        
        if (npcBuilder.startingAnimation != null)
            animationManager.setCurrentAnimation(npcBuilder.startingAnimation);

        name = npcBuilder.textureEnum.toString();

        hitbox = new Hitbox(center, npcBuilder.size.x, npcBuilder.size.y, true);
        hitbox.setTags(Tags.NPC, Tags.ENEMY);
        hitbox.setOnFrame(collider -> {
            if (!hitbox.touching(collider))
                return;
            if (!collider.containsTag(Tags.PLAYER))
                return;

            boolean leftPressed = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);

            if (leftPressed && npcBuilder.story != null && !Data.dialogueActive) {
                tell(npcBuilder.story);
                return;
            }
        });
        hitbox.register();
        setPosition(npcBuilder.coordinates.x, npcBuilder.coordinates.y);

        if (npcBuilder.autoStartedScript != null)
            doScript(npcBuilder.autoStartedScript);

        if(Data.debug) debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (currentDialogue == null) {
            autoMovementManager.update();
            if (!autoMovementManager.hasFinished())
                animationManager
                        .setCurrentAnimation(ResourceEnum.valueOf(name + "_" + autoMovementManager.getOrientation()));

        }

        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }
}
