package com.mygdx.dialogues;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

public class TypewriterEffect {

    private final Label label;
    private String fullText;
    private float charInterval = 0.04f;
    private boolean isRunning = false;
    private Runnable onFinished;

    public TypewriterEffect(Label label) {
        this.label = label;
    }

    public void start(String text) {
        start(text, charInterval, null);
    }

    @SuppressWarnings("deprecation")
    public void start(String text, float interval, Runnable onFinished) {
        this.fullText = text;
        this.charInterval = interval;
        this.onFinished = onFinished;
        isRunning = true;

        label.setText("");
        Array<Action> actions = new Array<>();
        final StringBuilder displayed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            actions.add(Actions.delay(charInterval));
            actions.add(Actions.run(() -> {
                displayed.append(fullText.charAt(index));
                label.setText(displayed.toString());
            }));
        }

        
        actions.add(Actions.run(() -> {
            isRunning = false;
            if (onFinished != null) onFinished.run();
        }));

        label.clearActions();
        label.addAction(Actions.sequence(actions.toArray(Action.class)));
    }

    public void skip() {
        if (isRunning) {
            label.clearActions();
            label.setText(fullText);
            isRunning = false;
            if (onFinished != null)
                onFinished.run();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
