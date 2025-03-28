package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Bullet extends Projectile {

    public Bullet(Vector2 origin, float barrel, float angle) {
        super(Utils.getTexture(ResourceEnum.BULLET), origin, barrel, 1000f, 1500f, angle);
        s.rotate90(true);
        collider.rotate(-90);
        flip(false, true);
        this.debug();
    }
}