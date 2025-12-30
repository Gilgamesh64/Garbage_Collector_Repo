package com.mygdx.resources.enums;

import com.mygdx.resources.RM;

public enum LangEnum {
    ITA("assets/dialogues/entities/ita"),
    ENG("assets/dialogues/entities/eng");

    public String label;

    LangEnum(String string) {
        this.label = string;
    }

    private static LangEnum current = LangEnum.ITA;

    public static LangEnum getCurrent() {
        return current;
    }

    public static void setCurrent(LangEnum lang) {
        current = lang;
        RM.get().updateLang();
    }


}
