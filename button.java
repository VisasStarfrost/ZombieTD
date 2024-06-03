package com.mygdx.game;
//static=only a single instance of that can run at once
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.math.*;

import java.util.ArrayList;

/**
 * Created by RP4K on 8/21/2017.
 */

public class button {
    public float xpos, ypos, width, height;
    public Texture buttontexture;
    public Rectangle rect;
    public button(float x, float y, Texture tex){
        buttontexture = tex;
        xpos = x;
        ypos = y;
        width = buttontexture.getWidth();
        height = buttontexture.getHeight();
        rect = new Rectangle(xpos, ypos, width, height);

    }
    public void draw(SpriteBatch batch){
        batch.draw(buttontexture, xpos - width/2, ypos - height/2);
    }
    public boolean isclicked(float x, float y){
        return rect.contains(x, y);
    }
}
