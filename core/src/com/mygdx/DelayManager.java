package com.mygdx;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Class used to subscribe objects to a delay and update delays
 */
public class DelayManager {
    // insert here an object and an integer, this will be the amount of delay
    // associated with the object
    private static final HashMap<Object, Float> originalDelays = new HashMap<>();
    // here you can see the current state of the delay for the given object
    private static final HashMap<Object, Float> currentDelays = new HashMap<>();
    // actions called when delay ends
    private static final HashMap<Object, Consumer<?>> actions = new HashMap<>();

    /**
     * registers an object to the delayManager
     *
     * @param o    the object you wish to register
     * @param time the amount of delay you need for the object
     */
    public static void registerObject(Object o, Float time) {
        originalDelays.put(o, time);
        currentDelays.put(o, time);
    }

    /**
     * registers an object to the delayManager
     *
     * @param o      the object you wish to register
     * @param time   the amount of delay you need for the object
     * @param action action invoked when delay ends
     */
    public static void registerObject(Object o, Float time, Consumer<?> action) {
        registerObject(o, time);
        actions.put(o, action);
    }

    /**
     * unregisters an object from the delay manager
     */
    public static void unregisterObject(Object o) {
        originalDelays.remove(o);
        currentDelays.remove(o);
        if (actions.get(o) != null)
            actions.remove(o);
    }

    /**
     * resets the delay associated with o
     */
    public static void resetDelay(Object o) {
        currentDelays.replace(o, originalDelays.get(o));
    }

    /**
     * updates the delay associated with o
     */
    public static void updateDelay(Object o) {
        Float i = currentDelays.get(o);
        if (i == null)
            return;

        if (i > 0) {
            currentDelays.replace(o, i - 100 * Gdx.graphics.getDeltaTime());
            return;
        }

        Consumer<?> c = actions.get(o);
        if (c != null) {
            c.accept(null);
            unregisterObject(o);
        }
    }

    /**
     * @return current delay associated with o
     */
    public static Float getCurrentDelay(Object o) {
        return currentDelays.get(o);
    }

    /**
     * @return true if delay is over
     */
    public static boolean isDelayOver(Object o) {
        return currentDelays.get(o) <= 0;
    }

    public static void changeDelay(Object o, float newDelay) {
        originalDelays.put(o, newDelay);
        currentDelays.put(o, newDelay);
    }
}
