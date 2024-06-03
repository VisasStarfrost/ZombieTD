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
    public  boolean active;
    public int firedelay;
    //public int cannonhp;
    public int counter;
    public Sprite cannonsprite;
    public float xpos,ypos,width,height,angle;
    static boolean iscannon = false;
    public cannon(float x,float y) {
        inittexture();

       // this.cannonsprite=new Sprite(resources.cannontexture);
        this.width = (float)resources.cannontexture.getWidth();
        this.height = (float)resources.cannontexture.getHeight();
        xpos = this.locktogrid(x-width/2.0F);
        ypos = this.locktogrid(y-height/2.0F);
        cannonsprite.setPosition(xpos,ypos);
        active = true;
        firedelay = 50-(ZombieTD_Matthew2.numpowercells*5);
        counter = 0;

        firesfx = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
        buildsfx = Gdx.audio.newSound(Gdx.files.internal("Building.wav"));
    }

    public void inittexture(){

        cannonsprite = new Sprite(resources.cannontexture);
    }
    public void Draw(SpriteBatch batch)  {
        cannonsprite.draw(batch);

    }
    public void Fire(){
        if(!ZombieTD_Matthew2.zombielist.isEmpty()){
            ZombieTD_Matthew2.bulletlist.add(new bullet(xpos +width/2, ypos + height/2,1));
            this.firesfx.play();
        }

    }
    public void update(){
        this.getangle();
        this.cannonsprite.setRotation(this.angle);
        if(this.counter++>=this.firedelay){
            Fire();
            this.counter = 0;
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
            this.angle = (float)Math.toDegrees((double)angle);
        }
    }
    public float locktogrid(float pos){
        return (float)((int)(pos+25.0F)/50*50);
    }

  //  public Circle getcircle(){return new Circle(xpos, ypos, width/2);}
    // public Rectangle getrectangle(){return new Rectangle (xpos, ypos, width, height);}


}
