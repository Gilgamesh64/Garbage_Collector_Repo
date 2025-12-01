package com.mygdx.screens.game.overworld;

import com.mygdx.Data;
import com.mygdx.entities.map.MapConstructor;
import com.mygdx.entities.npcs.NPCBuilder;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class Park extends PlayableScreen {

    public Park() {
        super(ResourceEnum.PARK);

        addAll(
                MapConstructor.getComponent(Data.TILE * 70, Data.TILE * 20, ResourceEnum.ABANDONED_TURNED_ON, ResourceEnum.ABANDONED_TURNED_OFF),

                new NPCBuilder()
                        .coordinates(Data.TILE * 40, Data.TILE * 20)
                        .texture(TextureEnum.JERKINS)
                        .story(ResourceEnum.ADEPTUS_2)
                        .autoStartedScript(ResourceEnum.MEET_1)
                        .build(),
                new NPCBuilder()
                        .coordinates(Data.TILE * 55, Data.TILE * 30)
                        .texture(TextureEnum.BLACKMARKETEER)
                        .story(ResourceEnum.ADEPTUS_1)
                        .autoStartedScript(ResourceEnum.MEET_2)
                        .build());

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}