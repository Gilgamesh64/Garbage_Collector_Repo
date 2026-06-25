package com.mygdx.path;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.helpers.ScriptableActor;

import java.util.ArrayList;
import java.util.Arrays;

public class Path {
    private final ArrayList<Vector2> path;

    private Path(ArrayList<Vector2> path) {
        this.path = path;
    }

    public static Path of(ArrayList<Vector2> path) {
        return new Path(path);
    }

    public static Path of(Vector2... path) {
        return new Path(new ArrayList<>(Arrays.asList(path)));
    }

    public void advance(ScriptableActor entity) {
        if (path.isEmpty()) {
            return;
        }
        Vector2 curr = path.remove(0).cpy();
        entity.movAbs(curr);
    }

    public Vector2 peek() {
        return path.get(0);
    }

    public boolean running() {
        return !path.isEmpty();
    }

}
