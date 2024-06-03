package com.mygdx.game;

/**
 * Created by RP4K on 8/18/2017.
 */

public class undead extends zombie {
    public undead(float x, float y, int hp){
        super (x, y, hp);
        this.hp = 10;
        speed = 3;
    }
    public void inittexture(){
        zombietexture = resources.undeadtexture;
    }
    public void takedamage(){
        if(hp--<1){
            active = false;
            UI.money +=0;
        }
    }
}
