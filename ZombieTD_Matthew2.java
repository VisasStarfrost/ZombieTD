package com.mygdx.game;
//static=only a single instance of that can run at once
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.math.*;

import java.sql.Array;
import java.util.ArrayList;

import sun.rmi.runtime.Log;

public class ZombieTD_Matthew2 extends ApplicationAdapter {
	public OrthographicCamera camera;
	public enum towertype{CANNON, FIRECANNON, XBOW, BANK, MINE, POWERCELL, CIRCUSCANNON, HEAVYCANNON, ZOMBIECANNON}
	public static towertype selectedtower;
	SpriteBatch batch;
	public static Sound Level1;
	public static Sound Level2;

	public static button cannonbutton;
	public static button firecannonbutton;
	public static button xbowbutton;
	public static button bankbutton;
	public static button minebutton;
	public static button powercellbutton;
	public static button circuscannonbutton;
    public static button heavycannonbutton;
	public static button zombiecannonbutton;

	public boolean minekilled = false;
	public static Sound ZombieNoise;
	public static ArrayList<cannon> cannonlist = new ArrayList<cannon>();
	public static ArrayList<bullet> bulletlist = new ArrayList<bullet>();
	public static ArrayList<zombie> zombielist = new ArrayList<zombie>();
	public static int[] location  = new int [21];
	public int mineloc ;
	public static int numMines = 0;
	public static int level = 0;
	public static ArrayList<explosion> explosionlist = new ArrayList<explosion>();
	public static float undeadspawnX;
	public static float undeadspawnY;
	public static boolean necromancerdead;
	public static int CannonDamage;
	public static float minexpos = -20;
	public static float mineypos;
	public static boolean mineplaced = false;
	public static int minelocy;
	public static int numpowercells;

	@Override
	public void create() {
		batch = new SpriteBatch();
		Spawnzombies();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		cannonbutton = new button(465, 550, resources.cannontexture);
		firecannonbutton = new button(535, 550, resources.firecannontexture);
		xbowbutton = new button (785, 550, resources.xbowtexture);
		bankbutton = new button (280, 550, resources.banktexture);
		minebutton = new button (400, 550, resources.minetexture);
		powercellbutton = new button (345, 550, resources.Powercelltexture);
        circuscannonbutton = new button (595, 550, resources.circuscannontexture);
        heavycannonbutton = new button (660, 550, resources.heavycannontexture);
		zombiecannonbutton = new button (725, 550, resources.zombiecannontexture);

		Level1 = Gdx.audio.newSound(Gdx.files.internal("World1.mp3"));
		Level2 = Gdx.audio.newSound(Gdx.files.internal("World2.mp3"));

		ZombieNoise = Gdx.audio.newSound(Gdx.files.internal("Zombienoise.wav"));
		necromancerdead = false;
		zombie.walkingsfx.loop();
		ZombieNoise.loop();
		Music();

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Update();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
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
		//buttons
		cannonbutton.draw(batch);
		firecannonbutton.draw(batch);
		xbowbutton.draw(batch);
		bankbutton.draw(batch);
		minebutton.draw(batch);
		powercellbutton.draw(batch);
		circuscannonbutton.draw(batch);
        heavycannonbutton.draw(batch);
		zombiecannonbutton.draw(batch);

		//other stuff
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
			if(minebuildable(x, y)){
			if(selectedtower==towertype.MINE&&UI.money >=10) {
				if (mineplaced == false){

					cannonlist.add(new mine(x, y));
			//	location[numMines] = x;
			//	numMines = numMines + 1;
					mineloc = x;
					//	cannon.buildsfx.play();
					UI.money -= 10;
					removetowerstack();
					mineplaced = true;
					minelocy = y;
				}
				}
			}
			Vector3 touchpos = new Vector3();
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchpos); //scales phone screen with fingers

			if(isbuildable((int)touchpos.x,(int)touchpos.y)){


				if(selectedtower==towertype.CANNON&& UI.money>=20) {
					cannonlist.add(new cannon((int)touchpos.x,(int)touchpos.y));
					//cannon.buildsfx.play();
					UI.money -= 20;
					removetowerstack();
				}
				if(selectedtower==towertype.FIRECANNON&& UI.money>=50) {
					cannonlist.add(new firecannon((int)touchpos.x,(int)touchpos.y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();
				}
				if(selectedtower==towertype.XBOW&& UI.money>=90) {
					cannonlist.add(new xbow((int)touchpos.x,(int)touchpos.y));
					//	cannon.buildsfx.play();
					UI.money -= 90;
					removetowerstack();
				}
				if(selectedtower==towertype.BANK&& UI.money>=50) {
					cannonlist.add(new bank((int)touchpos.x,(int)touchpos.y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();
				}
				if(selectedtower==towertype.POWERCELL&& UI.money>=100) {
					cannonlist.add(new powercell((int)touchpos.x,(int)touchpos.y));
					//	cannon.buildsfx.play();
					UI.money -= 100;
					removetowerstack();
					numpowercells += 1;
				}
				if(selectedtower==towertype.CIRCUSCANNON&& UI.money>=50) {
					cannonlist.add(new circuscannon((int)touchpos.x,(int)touchpos.y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();

				}
                if(selectedtower==towertype.HEAVYCANNON&& UI.money>=50) {
                    cannonlist.add(new heavycannon((int)touchpos.x,(int)touchpos.y));
                    //	cannon.buildsfx.play();
                    UI.money -= 50;
                    removetowerstack();

                }
				if(selectedtower==towertype.ZOMBIECANNON&& UI.money>=20) {
					cannonlist.add(new zombiecannon((int)touchpos.x,(int)touchpos.y));
					//	cannon.buildsfx.play();
					UI.money -= 20;
					removetowerstack();

				}
			}
			if(cannonbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
				selectedtower = towertype.CANNON;
			}
			if(firecannonbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
				selectedtower = towertype.FIRECANNON;
			}
			if(xbowbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
				selectedtower = towertype.XBOW;
			}
			if(bankbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
				selectedtower = towertype.BANK;
			}
            if(minebutton.isclicked((int)touchpos.x,(int)touchpos.y)){
                selectedtower = towertype.MINE;
            }
			if(powercellbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
				selectedtower = towertype.POWERCELL;
			}
			if(circuscannonbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
                selectedtower = towertype.CIRCUSCANNON;
            }
            if(heavycannonbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
                selectedtower = towertype.HEAVYCANNON;
            }
			if(zombiecannonbutton.isclicked((int)touchpos.x,(int)touchpos.y)){
				selectedtower = towertype.ZOMBIECANNON;
			}
		}


	}
	/*public void controls() {
		if (Gdx.input.justTouched()) {
			int x, y;
			x = Gdx.input.getX();
			y = Gdx.graphics.getHeight() - Gdx.input.getY();
			if(minebuildable(x, y)){
				if(selectedtower==towertype.MINE&&UI.money >=10) {
					if (mineplaced == false){

						cannonlist.add(new mine(x, y));
						//	location[numMines] = x;
						//	numMines = numMines + 1;
						mineloc = x;
						//	cannon.buildsfx.play();
						UI.money -= 10;
						removetowerstack();
						mineplaced = true;
						minelocy = y;
					}
				}
			}
			Vector3 touchpos = new Vector3();
			touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchpos); //scales phone screen with fingers

			if(isbuildable(x, y)){


				if(selectedtower==towertype.CANNON&& UI.money>=20) {
					cannonlist.add(new cannon(x, y));
					//cannon.buildsfx.play();
					UI.money -= 20;
					removetowerstack();
				}
				if(selectedtower==towertype.FIRECANNON&& UI.money>=50) {
					cannonlist.add(new firecannon(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();
				}
				if(selectedtower==towertype.XBOW&& UI.money>=90) {
					cannonlist.add(new xbow(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 90;
					removetowerstack();
				}
				if(selectedtower==towertype.BANK&& UI.money>=50) {
					cannonlist.add(new bank(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();
				}
				if(selectedtower==towertype.POWERCELL&& UI.money>=100) {
					cannonlist.add(new powercell(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 100;
					removetowerstack();
					numpowercells += 1;
				}
				if(selectedtower==towertype.CIRCUSCANNON&& UI.money>=50) {
					cannonlist.add(new circuscannon(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();

				}
				if(selectedtower==towertype.HEAVYCANNON&& UI.money>=50) {
					cannonlist.add(new heavycannon(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 50;
					removetowerstack();

				}
				if(selectedtower==towertype.ZOMBIECANNON&& UI.money>=20) {
					cannonlist.add(new zombiecannon(x, y));
					//	cannon.buildsfx.play();
					UI.money -= 20;
					removetowerstack();

				}
			}
			if(cannonbutton.isclicked(x, y)){
				selectedtower = towertype.CANNON;
			}
			if(firecannonbutton.isclicked(x, y)){
				selectedtower = towertype.FIRECANNON;
			}
			if(xbowbutton.isclicked(x, y)){
				selectedtower = towertype.XBOW;
			}
			if(bankbutton.isclicked(x, y)){
				selectedtower = towertype.BANK;
			}
			if(minebutton.isclicked(x, y)){
				selectedtower = towertype.MINE;
			}
			if(powercellbutton.isclicked(x, y)){
				selectedtower = towertype.POWERCELL;
			}
			if(circuscannonbutton.isclicked(x, y)){
				selectedtower = towertype.CIRCUSCANNON;
			}
			if(heavycannonbutton.isclicked(x, y)){
				selectedtower = towertype.HEAVYCANNON;
			}
			if(zombiecannonbutton.isclicked(x, y)){
				selectedtower = towertype.ZOMBIECANNON;
			}
		}


	}
*/
	public void Spawnzombies() {

		if(zombielist.isEmpty()) {
			level+=1;

			for (int I = 0; I < 1*level; I++) {
                zombielist.add(new zombie(1280 + I * 50, 275, 15+level));

                if(level == 5 || level == 7 || level == 9 || level > 10){
					zombielist.add(new fastzombie(1280 + I * 50, 275, 10+level));

				}
                if(level > 14){
					zombielist.add(new shieldzombie(1280 + I * 50, 275, 100+level));

				}
				if(level > 9){
					zombielist.add(new necromancer(1280 + I * 50, 275, 25+level));
					if(necromancerdead == true){
						zombielist.add(new undead(undeadspawnX, undeadspawnY, 10+level));

					}
				}else {
				}
			}
		}
	}

	public void Update() {
		if(zombie.GameOver == false) {
			controls();
			checkcollisions();
			checkMineCol();
			this.Spawnzombies();
			for (int I = 0; I < zombielist.size(); I++) {
				zombielist.get(I).Update();
			}
			for (int I = 0; I < bulletlist.size(); I++) {
				bulletlist.get(I).Update();
			}
			for (int I = 0; I < cannonlist.size(); I++) {
				cannonlist.get(I).update();
			}
			for (int I = 0; I < explosionlist.size(); I++) {
				explosionlist.get(I).update();
			}
			if (zombielist.isEmpty()) {
				zombie.walkingsfx.stop();
				ZombieNoise.stop();
			}
			if (minekilled) {
				killmine();
			}

			Removesprites();
		}
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
		if(!zombielist.isEmpty()) {
			for (int I = 0; I < bulletlist.size(); I++) {
				for (int J = 0; J < zombielist.size(); J++) {
					if (Intersector.overlaps(bulletlist.get(I).getcircle(), zombielist.get(J).getrectangle())) {
						System.out.print(bulletlist.get(I).cannonType + " " + CannonDamage);

						if (bulletlist.get(I).cannonType == 1) {
							CannonDamage = 1;
						} else if (bulletlist.get(I).cannonType == 2) {
							CannonDamage = 2;
						} else if (bulletlist.get(I).cannonType == 3) {
							CannonDamage = 15;
						} else if (bulletlist.get(I).cannonType == 4) {
							CannonDamage = 4;
						} else if (bulletlist.get(I).cannonType == -2) {
							CannonDamage = -2;
						} else {
							CannonDamage = 5;
						}
						zombielist.get(J).takedamage(CannonDamage);
						explosionlist.add(new explosion(bulletlist.get(I).xpos, bulletlist.get(I).ypos));


						bulletlist.get(I).active = false;

					}
				}
			}

		}
	}
	public void checkMineCol(){

			for (int J = 0; J < zombielist.size(); J++) {

				if (zombielist.get(J).xpos < ( mineloc + 80 ) && zombielist.get(J).xpos > ( mineloc- 80 ) ) {
					zombielist.get(J).hitmine();

					minekilled = true;
				}
				}

		}

		public void killmine(){

				for (int J = 0; J < cannonlist.size(); J++) {

					if (cannonlist.get(J).xpos < ( mineloc + 80 ) && cannonlist.get(J).xpos > ( mineloc- 80 ) ) {
						if (cannonlist.get(J).ypos < ( minelocy + 60  ) && cannonlist.get(J).ypos > ( minelocy - 60 ) ) {


							cannonlist.remove(J);
							mineloc = -100;
							mineplaced = false;
							minekilled = false;
						}
					}
			}
		}

	public boolean isbuildable(float x,float y){
		return(y<500&&y>360||y<140);
	}
	public boolean minebuildable(float x, float y){return(y>250 && y <300); }
	public void removetowerstack(){
		if(cannonlist.size()>1){
			for(int I=0; I<cannonlist.size()-1;I++){
				if(cannonlist.get(I).xpos==cannonlist.get(cannonlist.size()-1).xpos
						&&cannonlist.get(I).ypos==cannonlist.get(cannonlist.size()-1).ypos){
					cannonlist.remove(cannonlist.size()-1);
					UI.money+=5;
				}
			}
		}
	}
	public void Music(){

		if(level <=3) {
			Level1.loop();
		}
		else if(level>3){
			Level2.loop();
			Level1.stop();

		}
	}
}
