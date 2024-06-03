package com.mygdx.game;

/**
 * Created by RP4K on 8/18/2017.
 */

public class shieldzombie extends zombie {
    public shieldzombie (float x, float y, int hp) {
        super(x, y, hp);
        this.hp = 200;
        speed = 1;
    }
    public void inittexture(){
        zombietexture = resources.Shieldzombietexture;
    }
}
