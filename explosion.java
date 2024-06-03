package com.mygdx.game;
//static=only a single instance of that can run at once
        import com.badlogic.gdx.ApplicationAdapter;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Animation;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.utils.I18NBundle;
        import com.badlogic.gdx.math.*;

        import java.util.ArrayList;
/**
 * Created by RP4K on 8/16/2017.
 */

public class explosion {
    public Texture explosiontexture;
    public float xpos, ypos, width, height;
    public boolean active;
    public int hp;
    public int numcol, numRows;
    public Animation explodeanim;
    public TextureRegion[] explodeframes;

    public TextureRegion currentframe;
    public float frametime;
    public explosion(float x, float y){
        explosiontexture = resources.explosiontexture;
        xpos = x;
        ypos = y;
        width = explosiontexture.getWidth()/6;
        height = explosiontexture.getHeight();
        initanimations();
        active = true;
        hp = 25;
    }
    public void initanimations(){
        numRows = 1;
        numcol = 6;
        TextureRegion[][]temp= TextureRegion.split(explosiontexture, explosiontexture.getWidth()/numcol, explosiontexture.getHeight()/numRows);
        explodeframes = new TextureRegion[numRows*numcol];
        int FrameIndex = 0;
        for(int I=0; I<numRows;I++){
            for(int J=0; J<numcol; J++){
                explodeframes[FrameIndex++]=temp[I][J];

            }
        }
        explodeanim=new Animation(0.2f, explodeframes);
    }
    public void Draw(SpriteBatch batch){
        frametime+=Gdx.graphics.getDeltaTime();
        currentframe=(TextureRegion)explodeanim.getKeyFrame(frametime, true);
        batch.draw(currentframe, xpos - width/2, ypos - height/2);

    }
    public void update(){
        if(hp--==0){
            active = false;
        }
    }
}
