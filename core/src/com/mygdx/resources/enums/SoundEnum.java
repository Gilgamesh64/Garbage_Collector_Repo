package com.mygdx.resources.enums;

public enum SoundEnum {
    REFLECTION_1("reflection_1.mp3"),
    REFLECTION_2("reflection_2.mp3"),
    REFLECTION_3("reflection_3.mp3"),
    REFLECTION_4("reflection_4.mp3"),
    REFLECTION_5;

    /*IMPORTANT!!
    * AUTO SETS PATH "assets/audio"
    * PUT AUDIOS there, if necessary specify subfolders
    */

    public String path;

    SoundEnum(String path){
        this.path = "assets/audio/" + path;
    }

    SoundEnum(){
        this.path = "assets/audio/" + name().toLowerCase() + ".mp3";
    }
}
