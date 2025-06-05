package com.mygdx.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.resources.LangEnum;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GuiScreen;

public class SettingsScreen extends GuiScreen {

    public SettingsScreen() {
        super();

        var bg = new Image(Utils.getTexture(ResourceEnum.BACKGROUND_2));
        stage.getActors().insert(0, bg);

        table.columnDefaults(1).width(150);
        

        var langLabel = new Label("Language", Data.skin);
        table.add(langLabel).right().pad(20);

        var lang = new SelectBox<String>(Data.skin);
        lang.setItems("ITA", "ENG");
        table.add(lang).left().pad(20);
        
        table.row();

        var fullScreenLabel = new Label("Fullscreen", Data.skin);
        table.add(fullScreenLabel).right().pad(20);

        var fullScreenButton = new TextButton(Gdx.graphics.isFullscreen() ? "ON" : "OFF", Data.skin);
        table.add(fullScreenButton).left().pad(20);
        fullScreenButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setWindowedMode(Data.VIEWPORT_X, Data.VIEWPORT_Y);
                    fullScreenButton.setText("OFF");
                } else {
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    fullScreenButton.setText("ON");
                }
                return true;
            }
        });

        table.row();
        

        var back = new TextButton("BACK", Data.skin);
                
        table.add(back).center().bottom().pad(20).colspan(2);
        
        back.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Utils.setActiveLanguage(LangEnum.valueOf(lang.getSelected()));
                Utils.setScreen(ScreensManager.getScreen("MENU_SCREEN"));
                
                return true;
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
