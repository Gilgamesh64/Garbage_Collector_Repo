package com.mygdx.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.entities.TestActor;
import com.mygdx.hitboxes.HitboxHandler;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.player.camera.CameraController;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.screens.GenericScreen;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.ScreensManager.ScreenEnum;

public class MainScreen extends GenericScreen {

    private OrthographicCamera hudCamera;

    private TileSetManager tileSetManager;

    HitboxHandler hitboxHandler = new HitboxHandler();
    
    Player player = new Player(160, 160);
    TestActor testActor = new TestActor(160, 160);

    public MainScreen(){
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        
        stage.setViewport(viewport);
        stage.getCamera().translate(player.getX(),player.getY(), 0);
    }

    @Override
    public void show() {
        Utils.setStage(stage);
        CameraController.initCamera();

        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera.position.set(hudCamera.viewportWidth / 2.0f, hudCamera.viewportHeight / 2.0f, 1.0f);

        GunController.get();

        Gdx.input.setInputProcessor(stage);

        tileSetManager = new TileSetManager();
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));

        player.setMovementStyle(Player.Styles.TILED);
        stage.addActor(player);
        stage.setKeyboardFocus(player);
        stage.addActor(testActor);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        ScreenUtils.clear(1, 1, 1, 0);

        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            Utils.getGame().setScreen(ScreensManager.getScreen(ScreenEnum.PAUSE_SCREEN));
            return;
        } 
        if(Gdx.input.isKeyPressed(Keys.R)){
            CameraController.applyShakeEffect();
        } 
        tileSetManager.render((OrthographicCamera) stage.getCamera());

        stage.act(Gdx.graphics.getDeltaTime());
        CameraController.updateCamera();
        hitboxHandler.checkHitboxes();

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height); 
    }
}