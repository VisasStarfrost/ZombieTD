package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/22/2017.
 */

public class bank extends cannon {
    public bank(float x, float y){
        super (x, y);
        firedelay = 400-(ZombieTD_Matthew2.numpowercells*10);
    }
    public void Fire(){

    }
    public void inittexture(){
        cannonsprite = new Sprite(resources.banktexture);
    }
    public void update(){

        if(this.counter++>=this.firedelay){
            Money();
            this.counter = 0;
        }

    }
    public void Money(){
        UI.money += 10;
    }
}
