package com.mygdx.screens.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.controllers.messages.MsgManager;

/**
 * generic abstract class for every screen
 */

public abstract class GenericScreen extends ScreenAdapter {
    protected Stage stage;
    protected OrthographicCamera camera;

    protected MessageDispatcher stageMsg;

    protected GenericScreen() {
        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera));

        stageMsg = new MessageDispatcher();
        MsgManager.setCurrentStageMsg(stageMsg);

        Utils.setActiveScreen(this);
    }

    @Override
    public void show() {
        Utils.setStage(stage);
        CameraController.initCamera();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        stageMsg.update();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void subscribe(Telegraph a, MSG... msgs) {
        for (MSG msg : msgs) {
            stageMsg.addListener(a, msg.code);
        }
    }

    public void addAll(Actor... actors) {
        for (Actor actor : actors) {
            stage.addActor(actor);
        }
    }
}
