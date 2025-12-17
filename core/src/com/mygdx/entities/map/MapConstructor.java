package com.mygdx.entities.map;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

public class MapConstructor {
    public static Building getBuilding(float x, float y, ResourceEnum... texture) {
        return new Building(new Vector2(x, y), texture);
    }

    public static Building getBuilding(float x, float y, TextureEnum texture) {
        return new Building(new Vector2(x, y), texture);
    }

    public static Component getComponent(float x, float y, ResourceEnum... texture) {
        return new Component(new Vector2(x, y), texture);
    }

    public static Component getComponent(float x, float y, ResourceEnum texture) {
        return switch (texture) {
            case SAVING_TREE -> new SavingTree(new Vector2(x, y));
            default -> new Component(new Vector2(x, y), texture);
        };
    }

    public static Component getComponent(float x, float y, TextureEnum texture) {
        return switch (texture) {
            default -> new Component(new Vector2(x, y), texture);
        };
    }
}
