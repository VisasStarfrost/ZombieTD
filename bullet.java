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
    public int cannonType;
    public bullet(float x, float y, int type){
        this.bullettexture=resources.bullettexture;
        this.xpos = x;
        this.ypos = y;
        this.width = bullettexture.getWidth();
        this.height = bullettexture.getHeight();
        this.speed = 5.0F;
        this.active = true;
        this.life = 200;
        this.cannonType = type;
        getangle();
    }
    public void Draw(SpriteBatch batch){
        batch.draw(this.bullettexture, this.xpos - this.width/2.0F, this.ypos - this.height/2.0F);

    }
    public void Update(){
        this.xpos +=(float) Math.cos((double)this.angle)*this.speed;
        this.ypos +=(float) Math.sin((double)this.angle)*this.speed;
        if(this.life--<0){
            this.active = false;
        }
    }
    public void getangle(){
        if(!ZombieTD_Matthew2.zombielist.isEmpty()){
            float xC, yC, xZ, yZ, angle;
            xC = this.xpos;
            yC = this.ypos;
            xZ = ZombieTD_Matthew2.zombielist.get(0).xpos;
            yZ = ZombieTD_Matthew2.zombielist.get(0).ypos;
            angle = (float) Math.atan((double)((yC - yZ)/(xC-xZ)));
            if(xC>=xZ){
                angle = (float)((double)angle +3.141592653589793);
            }
            this.angle = angle;
        }
    }
    public Circle getcircle(){
    return new Circle(xpos, ypos, width/2);
    }
}

//Metamask





