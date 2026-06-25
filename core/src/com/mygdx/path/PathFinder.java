package com.mygdx.path;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.screens.ScreensManager;

public class PathFinder {
    public static Path find(Vector2 start, Vector2 end) {
        return Path.of(
                new Vector2(Data.TILE * 50, Data.TILE * 25),
                new Vector2(Data.TILE * 40, Data.TILE * 40),
                new Vector2(Data.TILE * 60, Data.TILE * 30),
                new Vector2(end.x, end.y)
        );
    }

    public static Vector2 getMarker(String name) {
        return ScreensManager.getCurrentPlayableScreen().tileSetManager.markersMap.get(name);
    }
}
