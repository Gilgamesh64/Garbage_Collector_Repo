package com.mygdx.resources.enums;

public enum MapEnum {
    SLUMS("slums/slums.tmx"),
    RICH_DISTRICT("rich_disctirct/rich_district.tmx"),
    PARK("park/park.tmx"),
    REFLECTION_ARENA("reflection_arena/reflection_arena.tmx"),
    INSIDE("inside/inside.tmx");

    /*IMPORTANT!!
    * AUTO SETS PATH "assets/map"
    * PUT MAPS there, if necessary specify subfolders
    */

    //TODO: i guess try compact path declaration

    public String path;
    MapEnum(String path){
        this.path = "assets/map/" + path;
    }
}
