package com.mygdx.scripts;

import com.mygdx.entities.helpers.ScriptableActor;
import com.mygdx.messages.MSG;

public class SndAction implements ScriptAction {
    private MSG msg;

    public SndAction(MSG m) {
        msg = m;
    }


    @Override
    public void perform(ScriptableActor actor) {
        System.out.println("Sending: " + msg);
        actor.proceed();

    }
}
