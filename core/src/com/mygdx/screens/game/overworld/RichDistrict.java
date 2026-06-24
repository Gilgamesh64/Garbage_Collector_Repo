package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.GCStage;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.entities.npcs.NPCBuilder;
import com.mygdx.path.PathFinder;
import com.mygdx.resources.enums.AnimationEnum;
import com.mygdx.resources.enums.MapEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class RichDistrict extends PlayableScreen {

        private final NPC particularNPC2 = NPCBuilder.create(AnimationEnum.BLACKMARKETEER, Data.TILE * 55, Data.TILE * 25)
                        .onInteraction(npc -> npc.runPath(
                                PathFinder.find(
                                        new Vector2(Data.TILE * 55, Data.TILE * 25),
                                        PathFinder.getMarker("hall")
                                )
                        ))
                        .build();

        public RichDistrict() {
                super(MapEnum.RICH_DISTRICT);
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