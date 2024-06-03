package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/24/2017.
 */

public class heavycannon extends cannon {
    public heavycannon (float x, float y){
        super (x, y);
        firedelay = 80-(ZombieTD_Matthew2.numpowercells*5);
    }
    public void inittexture(){
        cannonsprite = new Sprite(resources.heavycannontexture);
    }

    public void Fire() {
        if (!ZombieTD_Matthew2.zombielist.isEmpty()) {
            ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, 4));
            this.firesfx.play();
        }
    }
}
