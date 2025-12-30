package com.mygdx.entities.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.dialogues.Dialogue;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.hitboxes.Tags;
import com.mygdx.hud.Hud;
import com.mygdx.resources.RM;
import com.mygdx.resources.enums.DialogueEnum;
import com.mygdx.resources.enums.TextureEnum;

public class SavingTree extends Component {

    private Hitbox hitbox = new Hitbox();
    private Dialogue currentDialogue = null;

    public SavingTree(Vector2 coords) {
        super(coords, TextureEnum.SAVING_TREE);

        setTouchable(Touchable.enabled);

        setSize(64, 64);
        setOrigin(getWidth() / 2, getHeight() / 2);

        Gdx.app.postRunnable(this::setupHitbox); //Does this the next frame
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (currentDialogue != null)
            if (!currentDialogue.isRunning())
                currentDialogue = null;
    }

    private void setupHitbox() {
        hitbox = new Hitbox(
                new Vector2(getX() + animationManager.getWidth() / 2, getY() + animationManager.getHeight() / 2),
                animationManager.getWidth(), animationManager.getHeight(), true);
        hitbox.setTags(Tags.BUILDING);
        hitbox.setOnFrame(collider -> {
            if (!hitbox.touching(collider))
                return;
            if (!collider.containsTag(Tags.PLAYER))
                return;

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && currentDialogue == null) {
                currentDialogue = new Dialogue(RM.get().getStory(DialogueEnum.SAVE), this);
                Hud.stage().addActor(currentDialogue);
            }
        });

        hitbox.register();
    }

}
