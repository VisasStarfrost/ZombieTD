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

    static int life = 100;
    static int life1 = 100;
    static int life2 = 100;

    static boolean active = true;
    static boolean active1 = true;
    static boolean active2 = true;

    public static int money = 50;
    public static BitmapFont font= new BitmapFont();
    public static void draw(SpriteBatch batch){
        if(zombie.GameOver == true){
            font.setColor(Color.YELLOW);
            font.draw(batch,"GAME OVER:",250,350);

        }
        font.setColor(Color.YELLOW);
        font.draw(batch,"Money:"+money,65,550);
        font.setColor(Color.YELLOW);
        font.draw(batch,"Level:"+ZombieTD_Matthew2.level,130,550);
        font.setColor(Color.WHITE);
        font.draw(batch, "10", 400, 570);
        font.draw(batch, "20",465, 570);
        font.draw(batch,"100", 345, 570);
        font.draw(batch, "50",280, 570);
        font.draw(batch, "50",535, 570);
        font.draw(batch, "50",595, 570);
        font.draw(batch, "50",660, 570);
        font.draw(batch, "20",725, 570);
        font.draw(batch, "90",785, 570);




        font.setColor(Color.YELLOW);

        for (int I=0; I<ZombieTD_Matthew2.zombielist.size(); I++) {


            font.draw(batch, "" + ZombieTD_Matthew2.zombielist.get(I).hp, ZombieTD_Matthew2.zombielist.get(I).xpos-ZombieTD_Matthew2.zombielist.get(I).width/2, ZombieTD_Matthew2.zombielist.get(I).ypos-ZombieTD_Matthew2.zombielist.get(I).height/2);
        }
        if (ZombieTD_Matthew2.level == 5 && active == true){
           // for(int I=0; I<40000001; I++){
                Update();
         //   }


                font.draw(batch, "!New Enemy!", 500, 350);
                font.draw(batch, "!Fast Zombie!", 500, 250);
                font.draw(batch, "!Faster than your average zombie!", 500, 150);
                font.setColor(Color.YELLOW);

        }
        if (ZombieTD_Matthew2.level == 10 && active1 == true){
            // for(int I=0; I<40000001; I++){
            Update1();
            //   }


            font.draw(batch, "!New Enemy!", 500, 350);
            font.draw(batch, "!Necromancer!", 500, 250);
            font.draw(batch, "!Calls a cursed dead being when he dies!", 500, 150);
            font.setColor(Color.YELLOW);

        }
        if (ZombieTD_Matthew2.level == 15 && active2 == true){
            // for(int I=0; I<40000001; I++){
            Update2();
            //   }


            font.draw(batch, "!New Enemy!", 500, 350);
            font.draw(batch, "!Protector Zombie!", 500, 250);
            font.draw(batch, "!Protects the other zombies with his massive shield!", 500, 150);
            font.setColor(Color.YELLOW);

        }

}
    public static void Update(){
        if(life--<0){
            active = false;
        }

    }
    public static void Update1(){

        if(life1--<0){
            active1 = false;
        }

    }
    public static void Update2(){

        if(life2--<0){
            active2 = false;
        }

    }
}
