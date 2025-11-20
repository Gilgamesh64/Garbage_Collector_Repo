package com.mygdx.screens.menus;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.Data;

import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.Screens;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GuiScreen;

public class PauseScreen extends GuiScreen {

    
    public PauseScreen() {
        super();

        var bg = new Image(RM.get().getTexture(ResourceEnum.BACKGROUND_2));
        stage.getActors().insert(0, bg);

        var pauseLabel = new Label("PAUSED", RM.get().skin());
        table.add(pauseLabel).top().row();

        var playButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(RM.get().getTexture(ResourceEnum.PLAY_BUTTON))));
        var settingsButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(RM.get().getTexture(ResourceEnum.SETTINGS_BUTTON))));
        var quitButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(RM.get().getTexture(ResourceEnum.QUIT_BUTTON))));

        table.add(playButton).padBottom(20);
        table.row();
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScreensManager.setScreen(SavingsManager.getSavings().getLastRoom());
                return true;
            }
        });

        table.add(settingsButton).padBottom(20);
        table.row();
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScreensManager.setScreen(Screens.SETTINGS);
                return true;
            }
        });

        table.add(quitButton).padBottom(20);
        table.row();
        quitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScreensManager.setScreen(Screens.MENU_SCREEN);
                return true;
            }
        });
        
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    
}
