package com.mygdx.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.movement.MovementStyle;

import java.util.HashSet;
import java.util.Set;

public class PlayerTiledMovementStyle extends MovementStyle {

    private final Set<Character> inputs;
    private final Player player;
    private String movingDir;

    public PlayerTiledMovementStyle() {
        inputs = new HashSet<>();
        this.player = Utils.getPlayer();
        movingDir = "-";
        DelayManager.registerObject(this, 13f);
    }

    public String move() {
        DelayManager.updateDelay(this);

        float angle = CameraController.getMouseAngle();
        String idleDir = "-";

        if (angle > 55 && angle <= 125)
            idleDir = "iW";
        else if (angle > 125 && angle <= 235)
            idleDir = "iA";
        else if (angle > 235 && angle <= 305)
            idleDir = "iS";
        else if (angle > 305 || angle <= 55)
            idleDir = "iD";
        movingDir = "w" + idleDir.substring(1);

        if (!DelayManager.isDelayOver(this))
            return "";

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            inputs.add('W');
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            inputs.add('S');
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            inputs.add('A');
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            inputs.add('D');

        if (inputs.isEmpty())
            return idleDir;

        float x = 0, y = 0;

        for (Character c : inputs) {
            switch (c) {
                case 'W' -> y += 32;
                case 'A' -> x -= 32;
                case 'S' -> y -= 32;
                case 'D' -> x += 32;
            }
        }

        inputs.clear();
        DelayManager.resetDelay(this);

        if (x == 0 && y == 0)
            return idleDir;

        if (TileMapCollisionsManager.canMove(player.getX() + x, player.getY() + y)) {
            MoveByAction mba = new MoveByAction();
            mba.setAmount(x, y);
            mba.setDuration(0.1f);
            player.addAction(mba);

        }

        return movingDir;
    }
}
