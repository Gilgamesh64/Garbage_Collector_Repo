package com.mygdx.entities.map.doors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.hitboxes.Hitbox;

public class Door extends GameActor{

    protected String name;
    protected String dst;
    protected String dir;
    protected Vector2 insideCoords, outsideCoords;
    protected Hitbox hitbox = new Hitbox();

    public Door(String name, String dst, String dir, float x, float y){
        super();
        this.name = name;
        this.dst = dst;
        this.dir = dir;

        setX(x);
        setY(y);

        setTouchable(Touchable.enabled);
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
}
