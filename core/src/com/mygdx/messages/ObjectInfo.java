package com.mygdx.messages;

import java.util.HashMap;

public class ObjectInfo {
    public HashMap<String, Boolean> boolInfo;
    public HashMap<String, Integer> integerInfo;
    public HashMap<String, Float> floatInfo;
    public HashMap<String, String> stringInfo;

    /**
     * Creates an additional info object instance with the specified maps to
     * initialize.
     * 
     * <pre>
     *1: Boolean Map.
     *2: Integer Map.
     *3: Float Map.
     *4: String Map.
     * </pre>
     * 
     * @param selector array of numbers.
     */
    public ObjectInfo(int... selector) {
        for (int i : selector) {
            switch (i) {
                case 1 -> {
                    boolInfo = new HashMap<>();
                }
                case 2 -> {
                    integerInfo = new HashMap<>();
                }
                case 3 -> {
                    floatInfo = new HashMap<>();
                }
                case 4 -> {
                    stringInfo = new HashMap<>();
                }
                default -> {
                }
            }
        }
    }
}
