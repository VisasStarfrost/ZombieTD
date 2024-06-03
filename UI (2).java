package com.mygdx.game;
//static=only a single instance of that can run at once
        import com.badlogic.gdx.ApplicationAdapter;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.utils.I18NBundle;
        import com.badlogic.gdx.math.*;
/**
 * Created by RP4K on 8/16/2017.
 */

public class UI {
    public static int money = 80;
    public static BitmapFont font= new BitmapFont();
    public static void draw(SpriteBatch batch){
        font.setColor(Color.YELLOW);
        font.draw(batch,"Money:"+money,300,550);
    }
}
