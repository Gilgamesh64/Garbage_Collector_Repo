package com.mygdx.resources;

import com.mygdx.game.GarbageCollection;

public class RM {
    public static ResourceManager get() {
        return GarbageCollection.getInstance().getManager();
    }
}
