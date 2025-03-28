package com.mygdx.savings;

import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class SavingsManager {
    private static Json json;
    private static Settings s;

    public static void save() {
        if (json == null) {
            json = new Json();
            json.setOutputType(OutputType.json);
        }
        s.updateData();
        writeFile(json.prettyPrint(s));
    }

    public static void load() {
        if (json == null) {
            json = new Json();
            json.setOutputType(OutputType.json);
        }
        FileHandle file = Gdx.files.local("savings/savings.json");
        String jsonFile = file.readString();
        s = json.fromJson(Settings.class, jsonFile);
    }

    private static void writeFile(String s) {
        try {
            FileWriter myWriter = new FileWriter("savings/savings.json");
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector2 getPlayerCoordinates() {
        return s.getPlayerCoordinates();
    }

    public static PlayableScreen getLastRoom() {
        return ScreensManager.getPlayableScreen(s.getLastRoom());
    }
}
