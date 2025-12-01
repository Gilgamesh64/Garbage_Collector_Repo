package com.mygdx.resources;

public enum TextureEnum {

    PLAYER(ResourceEnum.PLAYER_IDLE_DOWN, ResourceEnum.PLAYER_IDLE_UP, ResourceEnum.PLAYER_IDLE_LEFT, ResourceEnum.PLAYER_IDLE_RIGHT, ResourceEnum.PLAYER_WALK_DOWN, ResourceEnum.PLAYER_WALK_UP, ResourceEnum.PLAYER_WALK_LEFT, ResourceEnum.PLAYER_WALK_RIGHT),
    JERKINS(ResourceEnum.JERKINS_IDLE_DOWN, ResourceEnum.JERKINS_IDLE_UP, ResourceEnum.JERKINS_IDLE_LEFT, ResourceEnum.JERKINS_IDLE_RIGHT, ResourceEnum.JERKINS_WALK_DOWN, ResourceEnum.JERKINS_WALK_UP, ResourceEnum.JERKINS_WALK_LEFT, ResourceEnum.JERKINS_WALK_RIGHT),
    BLACKMARKETEER(ResourceEnum.BLACKMARKETEER_IDLE_DOWN, ResourceEnum.BLACKMARKETEER_IDLE_UP, ResourceEnum.BLACKMARKETEER_IDLE_LEFT, ResourceEnum.BLACKMARKETEER_IDLE_RIGHT, ResourceEnum.BLACKMARKETEER_WALK_DOWN, ResourceEnum.BLACKMARKETEER_WALK_UP, ResourceEnum.BLACKMARKETEER_WALK_LEFT, ResourceEnum.BLACKMARKETEER_WALK_RIGHT, ResourceEnum.BLACKMARKETEER_SLEEPING),
    TEST(ResourceEnum.TEST);

    private ResourceEnum[] rl;
    private float animationRate = 0.2f;
    private float delay = 0;

    TextureEnum(float animationRate, int delay, ResourceEnum ... resourceList){
        rl = resourceList;
        this.animationRate = animationRate;
        this.delay = delay;
    }

    TextureEnum(ResourceEnum ... resourceList){
        rl = resourceList;
    }

    public ResourceEnum[] getResourceList(){
        return rl;
    }
    public float getAnimationRate() {
        return animationRate;
    }
    public float getDelay() {
        return delay;
    }
}
