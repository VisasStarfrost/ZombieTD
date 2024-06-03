package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/24/2017.
 */

public class circuscannon extends cannon {
    public circuscannon (float x, float y){
        super (x, y);
        firedelay = 20-(ZombieTD_Matthew2.numpowercells*4);
    }
    public void Fire() {
        if (!ZombieTD_Matthew2.zombielist.isEmpty()) {
            ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, 1));
            this.firesfx.play();
        }
    }
    public void inittexture(){
        cannonsprite = new Sprite(resources.circuscannontexture);
    }

}
