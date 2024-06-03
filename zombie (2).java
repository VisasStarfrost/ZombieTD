package com.mygdx.game;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.audio.Sound;

//static=only a single instance of that can run at once
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
/**
 * Created by RP4K on 8/14/2017.
 */

public class zombie {
    public Texture zombietexture;
    public int  numcol, numRows;
    public Animation walkanin;
    public TextureRegion[]walkframes;
    public TextureRegion currentframe;
    public float frametime;
    public int hp;
    public float xpos, ypos, width, height, speed;
    public boolean active ;
    public static Sound walkingsfx;
    public zombie(float x, float y){
        zombietexture=resources.zombietexture;
        xpos = x;
        ypos = y;
        width = zombietexture.getWidth();
        height = zombietexture.getHeight();
        initanimations();
        speed = 1;
        active = true;
        hp = 10;
        walkingsfx = Gdx.audio.newSound(Gdx.files.internal("Zombiewalk.wav"));
    }
    public void Draw(SpriteBatch batch){
        frametime+=Gdx.graphics.getDeltaTime();
        currentframe=(TextureRegion)walkanin.getKeyFrame(frametime, true);
        batch.draw(currentframe, xpos - width/2, ypos - height/2);

    }
    public void Update(){
        xpos -=speed;
        if(xpos<-20){
            active = false;
        }

    }
    public Rectangle getrectangle(){
        return new Rectangle (xpos, ypos, width, height);
    }

    public void takedamage(){
        if(hp--<0){
            active = false;
            UI.money +=5;
        }
    }
    public void initanimations(){
        numRows = 1;
        numcol = 4;
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

}
