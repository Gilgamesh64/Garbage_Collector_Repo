package com.mygdx.entities.helpers;


import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.GCStage;
import com.mygdx.dialogues.Dialogue;
import com.mygdx.hud.Hud;
import com.mygdx.messages.MSG;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public class ScriptableActor extends GameActor{
    protected Script script;
    public MSG listeningMSG;

    public void doScript(ResourceEnum s){
        this.clearActions();
        script = new Script(s);
        script.proceed(this);
    }

    public void move(float x, float y, boolean relative){
        if(relative) moveTo(getCoords().add(new Vector2(x, y)));
        else moveTo(x, y);
    }

    public void proceed(){
        script.proceed(this);
    }

    public void changeAnimation(ResourceEnum e, float time){
        animationManager.setCurrentAnimation(e);
        script.proceed(this);
    }

    public void listen(MSG msg){
        GCStage.get().subscribe(this, msg);
        this.listeningMSG = msg;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        super.handleMessage(msg);
        if(listeningMSG != null)
            if(listeningMSG.code == msg.message){
                resetListen();
            } 
        
        return true;
    }

    public void resetListen(){
        GCStage.get().unSubscribe(this, listeningMSG);
        listeningMSG = null;
        proceed();
    }
    
    public boolean hasScript(){
        return script != null;
    }

    public void tell(ResourceEnum story){
        Hud.stage().addActor(new Dialogue(RM.get().getStory(story), this));
    }

    public void say(String text){
        System.out.println(text);
    }
}