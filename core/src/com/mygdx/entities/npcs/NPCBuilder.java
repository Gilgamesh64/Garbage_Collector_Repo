package com.mygdx.entities.npcs;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public class NPCBuilder {
    protected Vector2 coordinates, size = new Vector2(16, 32);
    protected TextureEnum textureEnum;
    protected boolean atlas = true;
    protected ResourceEnum story;
    protected ResourceEnum autoStartedScript;
    protected ResourceEnum startingAnimation;

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

    public NPCBuilder texture(TextureEnum texture) {
        this.textureEnum = texture;
        return this;
    }

    public NPCBuilder noAtlas(){
        this.atlas = false;
        return this;
    }

    public NPCBuilder story(ResourceEnum e) {
        this.story = e;
        return this;
    }

    public NPCBuilder autoStartedScript(ResourceEnum e){
        this.autoStartedScript = e;
        return this;
    }

    public NPCBuilder startingAnimation(ResourceEnum e){
        this.startingAnimation = e;
        return this;
    }
    public NPC build(){
        return new NPC(this);
    }
}
