package com.mygdx.resources.enums;

public enum DialogueEnum {
    ADEPTUS_1("adeptus1_JSON.json"),
    ADEPTUS_2("adeptus2_JSON.json"),
    ADEPTUS_3("adeptus3_JSON.json"),
    ADEPTUS_4("adeptus4_JSON.json"),
    SAVE("save_JSON.json");


    //TODO: make better
    //I guess ?

    public String path;

    DialogueEnum(String path){
        this.path = path;
    }
}
