package com.mygdx.gunControls.guns;

import com.mygdx.GCStage;
import com.mygdx.gunControls.GunController;
import com.mygdx.gunControls.projectiles.Stone;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;

public class Slingshot extends BaseGun {

    public Slingshot() {
        super(RM.get().getTexture(ResourceEnum.DEFAULT), GCStage.get().getPlayer().center, 0);
        flip(false, true);
    }

    public int leftTrigger() {
        GCStage.get().addActor(new Stone(GCStage.get().getPlayer().center, getWidth(), true));
        GunController.get().setCooldown(10);
        GunController.get().resetCooldown();
        return 1;
    }

    public int rightTrigger() {
        GCStage.get().addActor(new Stone(GCStage.get().getPlayer().center, getWidth(), true));
        GunController.get().setCooldown(20);
        return 2;
    }

    public int middleTrigger() {
        return 0;
    }
}
