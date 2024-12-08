package com.mygdx.screens;

import java.util.HashMap;
import com.mygdx.screens.game.arenas.SandstoneArena;
import com.mygdx.screens.game.overworld.CityScreen;
import com.mygdx.screens.game.overworld.MainScreen;
import com.mygdx.screens.game.overworld.SecondRoomTest;
import com.mygdx.screens.generic.GenericScreen;
import com.mygdx.screens.generic.playable.PlayableScreen;
import com.mygdx.screens.menus.MenuScreen;
import com.mygdx.screens.menus.PauseScreen;
public class ScreensManager {

    private static HashMap<String, GenericScreen> map = new HashMap<>();
    private static String lastPlayableActiveScreen;

    public static GenericScreen getScreen(String screenName){
        if(map.get(screenName) == null){
            switch (screenName) {
                case "MAIN_SCREEN" -> {
                    map.put("MAIN_SCREEN", new MainScreen());
                }
                case "MENU_SCREEN" -> {
                    map.put("MENU_SCREEN", new MenuScreen());
                }
                case "PAUSE_SCREEN" -> {
                    map.put("PAUSE_SCREEN", new PauseScreen());
                }
                case "SECOND_SCREEN" -> {
                    map.put("SECOND_SCREEN", new SecondRoomTest());
                }
                case "SANDSTONE_ARENA" -> {
                    map.put("SANDSTONE_ARENA", new SandstoneArena());
                }
                case "CITY_SCREEN" -> {
                    map.put("CITY_SCREEN", new CityScreen());
                }
            }
        }
        if(map.get(screenName) instanceof PlayableScreen) lastPlayableActiveScreen = screenName;
        return map.get(screenName);
    }
    public static PlayableScreen getPlayableScreen(String screenName){
        return (PlayableScreen) getScreen(screenName);
    }

    public static boolean isNull(String screenName){
        return map.get(screenName) == null ? true : false;

    }
    public static String getLastPlayableActiveScreen() {
        return lastPlayableActiveScreen;
    }
}
