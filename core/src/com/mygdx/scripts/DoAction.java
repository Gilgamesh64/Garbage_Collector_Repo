package com.mygdx.scripts;

import com.mygdx.entities.ScriptableActor;
import com.mygdx.resources.ResourceEnum;

public class DoAction implements ScriptAction{
    private ResourceEnum script;

    public DoAction(ResourceEnum script) {
        this.script = script;
    }


    @Override
    public void perform(ScriptableActor actor) {
        System.out.println("Doing Action");
    }
}
