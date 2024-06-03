package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by RP4K on 8/22/2017.
 */

public class mine extends cannon {
    public mine (float x, float y){
        super (x, y);
        firedelay = 99;
    }
    public void Fire(){

    }
    public void inittexture(){
        cannonsprite = new Sprite(resources.minetexture);
    }
}
