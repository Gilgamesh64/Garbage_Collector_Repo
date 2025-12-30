package com.mygdx.resources.enums;

import java.util.stream.Stream;

public enum AnimationEnum {
    PLAYER,
    JERKINS,
    BLACKMARKETEER;


    public TextureEnum[] frameList;
    public float animationRate = 0.2f;
    public float delay = 0;

    AnimationEnum(float animationRate, int delay){
        frameList = Stream.of(TextureEnum.values())
            .filter(texture -> texture.name().contains(name()))
            .toArray(TextureEnum[]::new);

        this.animationRate = animationRate;
        this.delay = delay;
    }

    AnimationEnum(){
        this(0.2f, 0);
    }
}
