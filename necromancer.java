package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by RP4K on 8/18/2017.
 */

public class necromancer extends zombie {
    public necromancer (float x, float y, int hp){
        super (x, y, hp);
        this.hp = 25;
        speed = 1;

    }
    public void initanimations(){
        numRows = 1;
        numcol = 1;
        TextureRegion[][]temp= TextureRegion.split(zombietexture, zombietexture.getWidth()/numcol, zombietexture.getHeight()/numRows);
        walkframes = new TextureRegion[numRows*numcol];
        int FrameIndex = 0;
        for(int I=0; I<numRows;I++){
            for(int J=0; J<numcol; J++){
                walkframes[FrameIndex++]=temp[I][J];

            }
        }
        walkanin=new Animation(0.2f, walkframes);
    }
    public void inittexture(){
        zombietexture = resources.necromancertexture;
    }
    public void takedamage(){
        if(hp--<1){
            ZombieTD_Matthew2.undeadspawnX = xpos;
            ZombieTD_Matthew2.undeadspawnY = ypos;
            active = false;
            UI.money +=15;
            ZombieTD_Matthew2.necromancerdead = true;
        }
    }
}
