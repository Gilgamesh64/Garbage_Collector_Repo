package com.mygdx.dialogues;

import com.bladecoder.ink.runtime.Story;
import com.mygdx.Money;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.entities.helpers.ScriptableActor;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;

public class GameStory {
    private Story story;
    private GameActor currentActor;

    /**
     * Initializes the story, binding the external functions
     * @param inkJsonText
     */
    public GameStory(String inkJsonText) {
        try {
            story = new Story(inkJsonText);
            story.bindExternalFunction("DO", (Object[] args) -> {
                if (currentActor == null)
                    return null;
                if(currentActor instanceof ScriptableActor s){
                    String name = String.valueOf(args[0]);
                    s.doScript(ResourceEnum.valueOf(name));
                }
                
                return null;
            });
            story.bindExternalFunction("GAIN", (Object[] args) -> {
                Money.gain(((int)args[0]));
                return null;
            });
            story.bindExternalFunction("SAVE", (Object[] args) -> {
                SavingsManager.save();
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * assigns the story
     * @param actor
     */
    public void setActor(GameActor actor) {
        currentActor = actor;
    }

    public GameActor getCurrentActor() {
        return currentActor;
    }

    public Story getStory() {
        return story;
    }

}
