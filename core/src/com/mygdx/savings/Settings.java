package com.mygdx.savings;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.Utils;
import com.mygdx.screens.ScreensManager;

public class Settings implements com.badlogic.gdx.utils.Json.Serializable {

    private String lastRoom;
    private Vector2 lastRoomCoordinates = new Vector2();

    public void updateData() {
        lastRoom = Utils.getActiveScreen().getName();
        lastRoomCoordinates = ScreensManager.getPlayableScreen(ScreensManager.getLastPlayableActiveScreen())
                .getPlayerCoordinates();
    }

    @Override
    public void write(Json json) {
        json.writeValue("LAST_ROOM", lastRoom);
        json.writeValue("PLAYER", lastRoomCoordinates);

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        lastRoom = jsonData.getString("LAST_ROOM");

        lastRoomCoordinates.x = jsonData.get("PLAYER").getFloat("x");
        lastRoomCoordinates.y = jsonData.get("PLAYER").getFloat("y");
    }

    public Vector2 getPlayerCoordinates() {
        return lastRoomCoordinates;
    }

    public String getLastRoom() {
        return lastRoom;
    }
}
