package com.mygdx.controllers.gunControls.guns;

import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.gunControls.projectiles.UsbladeProj;
import com.mygdx.entities.Player;
import com.mygdx.resources.ResourceEnum;

public class Usblade extends BaseGun {
    public Usblade() {
        super(Utils.getTexture(ResourceEnum.USBLADE), Player.center, 45);
        setOffset(35, 0, 0);
    }

    public int leftTrigger() {
        super.leftTrigger();
        UsbladeProj proj = new UsbladeProj(
            Player.center,
            movement.position.x + movement.center.x,
            CameraController.getMouseAngle() + angleOffset,
            flipped);
        Utils.getStage().addActor(proj);

        GunController.get().setCooldown(50);
        return 1;
    }

}
