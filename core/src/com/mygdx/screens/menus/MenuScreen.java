package com.mygdx.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.Utils;
import com.mygdx.resources.LangEnum;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.GuiScreen;

public class MenuScreen extends GuiScreen {

    public MenuScreen() {
        super();
        Utils.setActiveLanguage(LangEnum.ITA);

        var bg = new Image(Utils.getTexture(ResourceEnum.BACKGROUND_2));
        stage.getActors().insert(0, bg);

        table.right().bottom().padRight(100).padBottom(100);

        var playButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.PLAY_BUTTON))));
        var settingsButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.SETTINGS_BUTTON))));
                var quitButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.QUIT_BUTTON))));

        table.add(playButton).padBottom(20);
        table.row();
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Utils.setScreen(SavingsManager.getLastRoom());
                return true;
            }
        });

        table.add(settingsButton).padBottom(20);
        table.row();
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Utils.setScreen(ScreensManager.getScreen("SETTINGS"));
                return true;
            }
        });

        table.add(quitButton).padBottom(20);
        table.row();
        quitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
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
