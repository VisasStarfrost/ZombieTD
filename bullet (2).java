package com.mygdx.game;
//static=only a single instance of that can run at once
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import com.badlogic.gdx.math.*;

/**
 * Created by RP4K on 8/14/2017.
 */

public class bullet {
    public int life;

    public Texture bullettexture;
    public float xpos, ypos, width, height, speed, angle;
    public boolean active ;
    public bullet(float x, float y){
        bullettexture=resources.bullettexture;
        xpos = x;
        ypos = y;
        width = bullettexture.getWidth();
        height = bullettexture.getHeight();
        speed = 5;
        active = true;
        getangle();
        life = 100;
    }
    public void Draw(SpriteBatch batch){
        batch.draw(bullettexture, xpos - width/2, ypos - height/2);

    }
    public void Update(){
        xpos +=(float) Math.cos(angle)*speed;
        ypos +=(float) Math.sin(angle)*speed;
        if(life--<0){
            active = false;
        }
    }
    public void getangle(){
        if(!ZombieTD_Matthew2.zombielist.isEmpty()){
            float xC, yC, xZ, yZ, angle;
            xC = xpos;
            yC = ypos;
            xZ = ZombieTD_Matthew2.zombielist.get(0).xpos;
            yZ = ZombieTD_Matthew2.zombielist.get(0).ypos;
            angle = (float) Math.atan((yC=yZ)/(xC-xZ));
            if(xC>=xZ){
                angle+=Math.PI;
            }
            this.angle = angle;
        }
    }
    public Circle getcircle(){
    return new Circle(xpos, ypos, width/2);
    }
}

//Metamask





