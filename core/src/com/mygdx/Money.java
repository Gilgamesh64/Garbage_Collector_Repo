package com.mygdx;

import com.mygdx.hud.Hud;

public class Money {
    private static int money;

    public static int get() {
        return money;
    }
    public static void set(int amount) {
        money = amount;
    }
    public static void gain(int amount){
        money += amount;
        Hud.get().setMoney(amount, true);
    }
    public static void lose(int amount){
        money -= amount;
        Hud.get().setMoney(amount, false);
    }
}
