package com.mygdx.screens.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.hud.Hud;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.hitboxes.HitboxHandler;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.Screens;
import com.mygdx.screens.ScreensManager;
import com.mygdx.stage.GCStage;
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
        HitboxHandler.set(hitboxHandler);

        if (StateManager.getBoolState(StateEnum.IS_EXITING)) {
            player = new Player(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        } else
            player = new Player(SavingsManager.getPlayerCoordinates());

        stage.addActor(player);
        stage.setKeyboardFocus(player);

        hud = new Hud();
        Hud.set(hud);
        this.name = map;
    }

    @Override
    public void show() {
        super.show();

        HitboxHandler.set(hitboxHandler);
        Hud.set(hud);

        CameraController.initCamera();

        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));
        GCStage.get().subscribe(tileSetManager, MSG.BLOCK_WALLS, MSG.SWAP_FIGHT_STATE);
        GCStage.get().subscribe(player, MSG.SWAP_FIGHT_STATE);
        if(SavingsManager.isPlayerFighting()) GCStage.get().send(MSG.SWAP_FIGHT_STATE, MSG.BLOCK_WALLS); //turns on combat mode

        if (StateManager.getBoolState(StateEnum.IS_EXITING) && !player.isAutoWalking()) {
            player.setCoords(tileSetManager.getCoord().cpy().add(8, 8));
            player.moveTo(tileSetManager.getExitPoint().cpy().add(8, 8));
        }

        GCStage.get().setPlayer(player);
        

        stage.getCamera().position.set(getPlayerCoordinates(), 0);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            ScreensManager.setScreen(Screens.PAUSE_SCREEN);
            return;
        }
        if (Gdx.input.isKeyJustPressed(Keys.M)) {
            SavingsManager.save();
        }
        if (Gdx.input.isKeyJustPressed(Keys.R)) {
            GCStage.get().send(MSG.DIALOGUE_TRIGGERED);
        }
        if(Gdx.input.isKeyJustPressed(Keys.H)){
            GCStage.get().send(MSG.BLOCK_WALLS);
        }
        if(Gdx.input.isKeyJustPressed(Keys.J)){
            GCStage.get().send(MSG.SWAP_FIGHT_STATE);
        }

        stage.getActors().sort((a, b) -> Float.compare(b.getY(), a.getY())); //solves z index problem

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
