package com.mygdx.screens.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.hitboxes.HitboxHandler;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.Player;
import com.mygdx.hud.Hud;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.ScreensManager;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

/**
 * generic abstract class for every playable screen
 */
public abstract class PlayableScreen extends GenericScreen {

    protected boolean stopGame = false;

    protected Hud hud;

    protected TileSetManager tileSetManager;

    protected HitboxHandler hitboxHandler;

    protected Player player;

    private ResourceEnum name;

    protected PlayableScreen(ResourceEnum map) {
        super();

        tileSetManager = new TileSetManager(map);
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));

        hitboxHandler = new HitboxHandler();
        

        if (StateManager.getBoolState(StateEnum.IS_EXITING)) {
            player = new Player(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        } else
            player = new Player(SavingsManager.getPlayerCoordinates());

        stage.addActor(player);
        stage.setKeyboardFocus(player);

        hud = new Hud();
        Utils.setCurrentHud(hud);

        this.name = map;
    }

    @Override
    public void show() {
        super.show();

        CameraController.initCamera();

        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));
        subscribe(tileSetManager, MSG.BLOCK_WALLS, MSG.CHANGE_MOV_STYLE);
        subscribe(player, MSG.CHANGE_MOV_STYLE);

        if (StateManager.getBoolState(StateEnum.IS_EXITING) && !player.isAutoWalking()) {
            player.setCoords(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        }

        Utils.setPlayer(player);
        Utils.setHitboxHandler(hitboxHandler);

        stage.getCamera().position.set(getPlayerCoordinates(), 0);

        player.setMovementStyle(Player.Styles.REALTIME);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Utils.setScreen(ScreensManager.getScreen("PAUSE_SCREEN"));
            return;
        }
        if (Gdx.input.isKeyPressed(Keys.M)) {
            SavingsManager.save();
        }
        if (Gdx.input.isKeyPressed(Keys.R)) {
            stageMsg.dispatchMessage(0);
        }
        if(Gdx.input.isKeyJustPressed(Keys.H)){
            stageMsg.dispatchMessage(MSG.BLOCK_WALLS.code);
        }
        if(Gdx.input.isKeyJustPressed(Keys.J)){
            stageMsg.dispatchMessage(MSG.CHANGE_MOV_STYLE.code);
        }

        if (Utils.getActiveScreen() != this)
            return;

        stage.getActors().sort((a, b) -> Float.compare(b.getY(), a.getY()));

        if (!StateManager.getBoolState(StateEnum.PAUSE))
            stage.act(Gdx.graphics.getDeltaTime());

        CameraController.updateCamera();
        hitboxHandler.checkRegistered();

        tileSetManager.render(camera);
        
        stage.draw();
        
        hud.update();
        hud.draw();
    }

    protected void stopGame() {
        stopGame = true;
    }

    protected void resumeGame() {
        stopGame = false;
    }

    public Vector2 getPlayerCoordinates() {
        return new Vector2(player.getX(), player.getY());
    }

    public String getName(){
        return this.name.name();
    }
}
