package com.mygdx.messages;

public enum MSG {
    DIALOGUE_TRIGGERED,
    DIALOGUE_DONE,
    BLOCK_WALLS,
    UNBLOCK_WALLS,
    SHOT,
    SWAP_FIGHT_STATE,
    ACT_YOU_IDIOT,

    ACK_MOV_BOTH,
    MOV_BOTH;

    static {
        int counter = 0;
        for (MSG msg : MSG.values()) {
            msg.code = counter++;
        }
    }

    public int code;

}