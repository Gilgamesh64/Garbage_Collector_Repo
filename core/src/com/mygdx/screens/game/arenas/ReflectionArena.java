package com.mygdx.screens.game.arenas;

import com.mygdx.Data;
import com.mygdx.GCStage;
import com.mygdx.entities.npcs.NPCBuilder;
import com.mygdx.entities.npcs.Reflection;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.messages.MSG;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.screens.generic.PlayableScreen;

public class ReflectionArena extends PlayableScreen {

    public ReflectionArena() {
        super(ResourceEnum.REFLECTION_ARENA);
        addAll(new Reflection(new NPCBuilder().coordinates(Data.TILE * 10, Data.TILE * 10)
                .texture(TextureEnum.BLACKMARKETEER)));
    }

    @Override
    public void show() {
        super.show();
        if (Math.random() < 0.5f)
            RM.get().playAudio(ResourceEnum.REFLECTION_1);
        else
            RM.get().playAudio(ResourceEnum.REFLECTION_3);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if (TileMapCollisionsManager.changeFightingState()) {
            GCStage.get().send(MSG.BLOCK_WALLS);
            GCStage.get().send(MSG.SWAP_FIGHT_STATE);

        }
    }
}
