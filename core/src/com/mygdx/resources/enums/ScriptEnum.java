package com.mygdx.resources.enums;

public enum ScriptEnum {
    BLACKMARKETEER_1("npcs/blackmarketeer_1.gcs");

    public String path;

    ScriptEnum(String path){
        this.path = "assets/scripts/" + path;
    }
    
}
