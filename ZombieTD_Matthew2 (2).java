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

public class ZombieTD_Matthew2 extends ApplicationAdapter {
	SpriteBatch batch;
	public static Sound Level1;
	public static Sound ZombieNoise;
	public static ArrayList<cannon> cannonlist = new ArrayList<cannon>();
	public static ArrayList<bullet> bulletlist = new ArrayList<bullet>();
	public static ArrayList<zombie> zombielist = new ArrayList<zombie>();
	public static ArrayList<explosion> explosionlist = new ArrayList<explosion>();

	@Override
	public void create() {
		batch = new SpriteBatch();
		Spawnzombies();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Update();
		batch.begin();
		batch.draw(resources.backgroundtexture, 0, 0);
		for (int I = 0; I < cannonlist.size(); I++) {
			cannonlist.get(I).Draw(batch);
		}
		for (int I = 0; I < zombielist.size(); I++) {
			zombielist.get(I).Draw(batch);
		}
		for (int I = 0; I < bulletlist.size(); I++) {
			bulletlist.get(I).Draw(batch);
		}
		for (int I = 0; I < explosionlist.size(); I++) {
			explosionlist.get(I).Draw(batch);
		}
		UI.draw(batch);
		batch.end();

	}

	@Override
	public void dispose() {
	}

	public void controls() {
		if (Gdx.input.justTouched()) {
			int x, y;
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight() - Gdx.input.getY();
			if(isbuildable(x, y)&& UI.money>=10){
				cannonlist.add(new cannon(x, y));
				cannon.buildsfx.play();
				UI.money-=10;
				removetowerstack();

			}
		}

	}

	public void Spawnzombies() {
		Level1 = Gdx.audio.newSound(Gdx.files.internal("World1.mp3"));
		ZombieNoise = Gdx.audio.newSound(Gdx.files.internal("Zombienoise.wav"));
		for (int I = 0; I < 2; I++) {
			zombielist.add(new zombie(1024 + I * 50, 275));

		}
		zombie.walkingsfx.loop();
		ZombieNoise.loop();
		Level1.loop();
	}

	public void Update() {
		controls();
		checkcollisions();

		for (int I = 0; I < zombielist.size(); I++) {
			zombielist.get(I).Update();
		}
		for (int I = 0; I < bulletlist.size(); I++) {
			bulletlist.get(I).Update();
		}
		for(int I = 0; I < cannonlist.size(); I++){
			cannonlist.get(I).update();
		}
		for(int I = 0; I < explosionlist.size(); I++){
			explosionlist.get(I).update();
		}
		if(zombielist.isEmpty()){
			zombie.walkingsfx.stop();
			ZombieNoise.stop();
		}

		Removesprites();
	}
	public void Removesprites(){
		for(int I = 0; I< zombielist.size(); I++){
			if (!zombielist.get(I).active) {
				zombielist.remove(I);
			}
		}
        for(int I = 0; I< bulletlist.size(); I++){
            if (!bulletlist.get(I).active) {
                bulletlist.remove(I);
            }
        }
		for(int I = 0; I< explosionlist.size(); I++){
			if (!explosionlist.get(I).active) {
				explosionlist.remove(I);
			}
		}
	}
	public void checkcollisions(){
		if(!zombielist.isEmpty()){
			for (int I = 0; I < bulletlist.size(); I++){
				for (int J = 0; J < zombielist.size(); J++ ){
					if (Intersector.overlaps(bulletlist.get(I).getcircle(), zombielist.get(J).getrectangle())){
						zombielist.get(J).takedamage();
						explosionlist.add(new explosion(bulletlist.get(I).xpos, bulletlist.get(I).ypos));


						bulletlist.get(I).active = false;

					}
				}
			}
		}
	}
	public boolean isbuildable(float x,float y){
		return(y<500&&y>300||y<200);
	}
	public void removetowerstack(){
		if(cannonlist.size()>1){
			for(int I=0; I<cannonlist.size()-1;I++){
				if(cannonlist.get(I).xpos==cannonlist.get(cannonlist.size()-1).xpos
						&&cannonlist.get(I).ypos==cannonlist.get(cannonlist.size()-1).ypos){
					cannonlist.remove(cannonlist.size()-1);
					UI.money+=10;
				}
			}
		}
	}
}
