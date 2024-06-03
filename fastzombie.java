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
 * Created by RP4K on 8/18/2017.
 */

public class fastzombie extends zombie {
    public fastzombie(float x, float y, int hp){
        super (x,y,hp);
        speed = 5;
        this.hp = 10;
    }
    public void inittexture(){
        zombietexture = resources.fastzombietexture;
    }
    public void takedamage(){
        if(hp--<1){
            active = false;
            UI.money +=10;
        }
    }
}
