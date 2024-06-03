package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
/**
 * Created by RP4K on 8/21/2017.
 */

public class firecannon extends cannon {
    public firecannon (float x, float y){
        super (x, y);

        firedelay = 40-(ZombieTD_Matthew2.numpowercells*5);
    }
    public void inittexture(){
        cannonsprite = new Sprite(resources.firecannontexture);
    }

    public void Fire() {
        if (!ZombieTD_Matthew2.zombielist.isEmpty()) {
            ZombieTD_Matthew2.bulletlist.add(new bullet(xpos + width / 2, ypos + height / 2, 2));
            this.firesfx.play();
        }
    }
    }
