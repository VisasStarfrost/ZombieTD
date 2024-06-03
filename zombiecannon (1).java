package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/24/2017.
 */

public class zombiecannon extends cannon {
    public zombiecannon(float x, float y) {
        super(x, y);
        firedelay = 30 - (ZombieTD_Matthew2.numpowercells * 4);
    }

    public void inittexture() {
        cannonsprite = new Sprite(resources.zombiecannontexture);
    }

    public void Fire() {
        if (!ZombieTD_Matthew2.zombielist.isEmpty()) {
            if (ZombieTD_Matthew2.level <= 7) {
                ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, 2));
                this.firesfx.play();
            } else if (ZombieTD_Matthew2.level >= 7) {
                ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, -2));
                this.firesfx.play();
            }
            else {
                ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, 2));
                this.firesfx.play();
            }
        }
    }
}
