package com.mygdx.scripts;

import com.mygdx.entities.helpers.ScriptableActor;
import com.mygdx.resources.enums.TextureEnum;

public class AniAction implements ScriptAction{
    TextureEnum texture;


    public AniAction(TextureEnum texture) {
        this.texture = texture;
    }


    @Override
    public void perform(ScriptableActor actor) {
        actor.changeAnimation(texture, 0.3f);
    }
}
