package com.mygdx.stage;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.Player;

public class GCStage extends Stage {
    private MessageDispatcher stageMsg;
    
    private Player player;

    private static GCStage instance;
    public static GCStage get(){
        return instance;
    }

    public static void set(GCStage stage){
        instance = stage;
    }

    public GCStage(Viewport v) {
        super(v);
        stageMsg = new MessageDispatcher();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stageMsg.update();
    }

    public void subscribe(Telegraph a, MSG... msgs) {
        for (MSG msg : msgs) {
            stageMsg.addListener(a, msg.code);
        }
    }

    public void unSubscribe(Telegraph a, MSG... msgs) {
        for (MSG msg : msgs) {
            stageMsg.removeListener(a, msg.code);
        }
    }

    public void send(MSG... msgs) {
        for (MSG msg : msgs) {
            stageMsg.dispatchMessage(msg.code);
        }
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}