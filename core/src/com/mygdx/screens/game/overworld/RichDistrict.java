package com.mygdx.screens.game.overworld;

import com.mygdx.Data;
import com.mygdx.GCStage;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.entities.npcs.NPCBuilder;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class RichDistrict extends PlayableScreen {

        private NPC particularNPC2 = new NPCBuilder()
                        .coordinates(Data.TILE * 55, Data.TILE * 25)
                        .texture(TextureEnum.BLACKMARKETEER)
                        .story(ResourceEnum.ADEPTUS_1)
                        .autoStartedScript(ResourceEnum.BLACKMARKETEER_1)
                        .build();

        public RichDistrict() {
                super(ResourceEnum.RICH_DISTRICT);
                GCStage.get().addAll(particularNPC2);
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