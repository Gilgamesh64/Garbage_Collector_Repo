package com.mygdx.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.GCStage;

/**
 * manages collisions inside a tilemap
 */
public class TileMapCollisionsManager {
    public static TiledMapTileLayer layer;
    public static boolean transitionInCooldown = false;

    /**
     * @param incomingX
     * @param incomingY
     * @return true if player can move in the incoming position
     */
    public static boolean canMove(float incomingX, float incomingY) {
        TiledMapTileLayer.Cell cellLeft = layer.getCell(
                (int) (incomingX) / 32,
                (int) (incomingY) / 32);
        if (cellLeft == null)
            return false;

        TiledMapTile tileLeft = cellLeft.getTile();
        if (tileLeft.getProperties().get("blocked") != null) return false;

        TiledMapTileLayer.Cell cellRight = layer.getCell(
                (int) (incomingX + GCStage.get().getPlayer().getWidth()) / 32,
                (int) (incomingY) / 32);
        if (cellRight == null)
            return false;

        TiledMapTile tileRight = cellRight.getTile();

        return tileRight.getProperties().get("blocked") == null;
    }

    public static MapProperties getCurrentTileProprieties() {
        TiledMapTile tile = layer.getCell(
                        (int) (GCStage.get().getPlayer().getX() + GCStage.get().getPlayer().getWidth() / 2) / 32,
                        (int) (GCStage.get().getPlayer().getY()) / 32)
                .getTile();
        return tile.getProperties();
    }

    public static boolean changeFightingState() {
        if (!transitionInCooldown && getCurrentTileProprieties().containsKey("transition")) {
            transitionInCooldown = true;
            return true;
        }

        if (!getCurrentTileProprieties().containsKey("transition") && transitionInCooldown) {
            transitionInCooldown = false;
        }
        return false;
    }
}
