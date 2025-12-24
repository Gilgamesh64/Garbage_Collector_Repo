package com.mygdx.entities.map;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Data;
import com.mygdx.GCStage;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.hitboxes.Tags;
import com.mygdx.screens.Screens;
import com.mygdx.screens.ScreensManager;

public class InvisibleDoor extends GameActor {
    private String name;
    private String dst;
    private String dir;
    private Vector2 insideCoords, outsideCoords;

    protected Hitbox hitbox = new Hitbox();

    public InvisibleDoor(String name, String dst, String dir, float x, float y, float width,
            float height) {
        super();
        this.name = name;
        this.dst = dst;
        this.dir = dir;
        setX(x);
        setY(y);

        setTouchable(Touchable.enabled);

        setSize(width, height);
        setOrigin(getWidth() / 2, getHeight() / 2);
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();

        insideCoords = center.cpy().sub(16, 8);
        outsideCoords = switch (dir) {
            case "u" -> center.cpy().add(0, -32);
            case "d" -> center.cpy().add(0, 32);
            case "l" -> center.cpy().add(32, 0);
            case "r" -> center.cpy().add(-32, 0);
            default -> center.cpy();
        };
        outsideCoords.sub(16, 8);


        hitbox = new Hitbox(new Vector2(x + width / 2, y + height / 2), width, height, true);
        hitbox.setTags(Tags.DOOR);
        hitbox.setOnHit((collider) -> {
            if(Data.exiting) return;

            GCStage.get().getPlayer().moveTo(insideCoords, () -> {
                ScreensManager.setScreen(Screens.valueOf(dst.split("-")[0]), dst);
                Data.exiting = true;
            });
        });
        hitbox.register();
        setPosition(x, y);

        if (Data.debug)
            debug();
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return dst;
    }

    public String getDirection() {
        return dir;
    }

    public Vector2 getInsideCoords() {
        return insideCoords;
    }

    public Vector2 getOutsideCoords() {
        return outsideCoords;
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public String toString() {
        return "InvisibleDoor{" + '\n' +
            "\tname=" + name + '\n' +
            "\tdst=" + dst + '\n' +
            "\tdir=" + dir + '\n' +
            "\tinsideCoords=" + insideCoords + '\n' +
            "\toutsideCoords=" + outsideCoords + '\n' + 
            "}";

    }
}
