package com.mygdx.screens.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.GCStage;
import com.mygdx.camera.CameraController;
import com.mygdx.resources.RM;

/**
 * generic abstract class for every screen
 */

public abstract class GenericScreen extends ScreenAdapter {
    protected GCStage stage;
    protected InputMultiplexer mux;

    protected OrthographicCamera camera;

    protected GenericScreen() {
        camera = new OrthographicCamera();
        stage = new GCStage(new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera));
        GCStage.set(stage);
        
        mux = new InputMultiplexer();
        mux.addProcessor(stage);
        Gdx.input.setInputProcessor(mux);
    }

    @Override
    public void show() {
        GCStage.set(stage);

        CameraController.initCamera();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);

        RM.get().update();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }
}
