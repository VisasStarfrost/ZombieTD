package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/21/2017.
 */

public class xbow extends cannon {
    public xbow (float x, float y){
        super (x, y);

        firedelay = 100-(ZombieTD_Matthew2.numpowercells*7);
    }
    public void inittexture(){
        cannonsprite = new Sprite(resources.xbowtexture);
    }
    public void Fire() {
        if (!ZombieTD_Matthew2.zombielist.isEmpty()) {
            ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, 3));
            this.firesfx.play();
        }
    }
}
