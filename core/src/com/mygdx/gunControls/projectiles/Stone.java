package com.mygdx.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;

public class Stone extends BaseBullet {

    public Stone(Vector2 origin, float barrel, boolean ally) {
        super(RM.get().getFromAtlas(ResourceEnum.WEAPONS, ResourceEnum.STONE), origin, 500f, 150f, 0f, ally);
        setOffset(barrel, 0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        s.rotate(10);
    }
}
