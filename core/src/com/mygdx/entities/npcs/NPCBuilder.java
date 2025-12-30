package com.mygdx.entities.npcs;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.enums.AnimationEnum;
import com.mygdx.resources.enums.DialogueEnum;
import com.mygdx.resources.enums.ScriptEnum;
import com.mygdx.resources.enums.TextureEnum;

public class NPCBuilder {
    protected Vector2 coordinates, size = new Vector2(16, 32);
    protected AnimationEnum anim;
    protected DialogueEnum story;
    protected ScriptEnum autoStartedScript;
    protected TextureEnum startingAnimation;

    public NPCBuilder coordinates(Vector2 coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public NPCBuilder coordinates(float x, float y) {
        this.coordinates = new Vector2(x, y);
        return this;
    }

    public NPCBuilder size(Vector2 size) {
        this.size = size;
        return this;
    }

    public NPCBuilder size(float x, float y) {
        this.size = new Vector2(x, y);
        return this;
    }

    public NPCBuilder texture(AnimationEnum texture) {
        this.anim = texture;
        return this;
    }

    public NPCBuilder story(DialogueEnum e) {
        this.story = e;
        return this;
    }

    public NPCBuilder autoStartedScript(ScriptEnum e){
        this.autoStartedScript = e;
        return this;
    }

    public NPCBuilder startingAnimation(TextureEnum e){
        this.startingAnimation = e;
        return this;
    }
    public NPC build(){
        return new NPC(this);
    }
}
