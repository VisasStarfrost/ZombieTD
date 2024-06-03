package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
/**
 * Created by RP4K on 8/14/2017.
 */

public class cannon {
    public Sound firesfx;
    public static Sound buildsfx;

    public int firedelay;
    //public int cannonhp;
    public int counter;
    public Sprite cannonsprite;
    public float xpos,ypos,width,height,angle;
    public cannon(float x,float y) {
        cannonsprite=new Sprite(resources.cannontexture);
        width = resources.cannontexture.getWidth();
        height = resources.cannontexture.getHeight();
        xpos = locktogrid(x-width/2);
        ypos = locktogrid(y-height/2);
        cannonsprite.setPosition(locktogrid(xpos), locktogrid(ypos));
        xpos = x;
        ypos = y;
        width = resources.cannontexture.getWidth();
        height = resources.cannontexture.getHeight();
        cannonsprite.setPosition(xpos - width/2, ypos - height/2);
        firedelay = 30;
        counter = 0;
        //cannonhp = 5;
        firesfx = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
        buildsfx = Gdx.audio.newSound(Gdx.files.internal("Building.wav"));
    }
    public void Draw(SpriteBatch batch)  {
        cannonsprite.draw(batch);

    }
    public void Fire(){
        if(!ZombieTD_Matthew2.zombielist.isEmpty()){
            ZombieTD_Matthew2.bulletlist.add(new bullet(xpos+width/2,ypos+height/2));
            firesfx.play();
        }

    }
    public void update(){
        getangle();
        cannonsprite.setRotation(angle);
        if(counter++>=firedelay){
            Fire();
            counter = 0;
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
            this.angle = (float)Math.toDegrees(angle);
        }
    }
    public float locktogrid(float pos){
        return((int)(pos+25)/50)*50;
    }
}
