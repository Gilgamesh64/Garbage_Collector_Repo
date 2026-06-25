package com.mygdx.scripts;

import com.badlogic.gdx.Gdx;
import com.mygdx.entities.helpers.ScriptableActor;

public class SkipAction implements ScriptAction {

    @Override
    public void perform(ScriptableActor actor) {
        Gdx.app.postRunnable(actor::proceed);
    }
}