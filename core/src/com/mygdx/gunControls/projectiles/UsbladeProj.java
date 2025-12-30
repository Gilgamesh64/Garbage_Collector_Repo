package com.mygdx.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;

import com.mygdx.resources.RM;
import com.mygdx.resources.enums.AtlasEnum;
import com.mygdx.resources.enums.TextureEnum;

public class UsbladeProj extends BaseMelee {

    public UsbladeProj(Vector2 origin, float barrel, float angle, boolean direction) {
        super(RM.get().getSpriteFromAtlas(AtlasEnum.WEAPONS, TextureEnum.USBLADE), origin, angle, 90, 500, direction);
        setOffset(barrel, 0, 0);
        attachInfo(2);
        info.integerInfo.put("damage", 1);
    }
}
