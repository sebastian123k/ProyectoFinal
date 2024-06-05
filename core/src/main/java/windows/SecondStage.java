package windows;

import java.util.ArrayList;
import enemigos.RocketRobot;
import mainPakage.Bullet;
import mainPakage.Camera;
import mainPakage.Effect;
import mainPakage.MFont;
import mainPakage.MainLoop;
import player.LifeBar;
import player.Player;
import player.PowerBar;

import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue.Triangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import enemigos.Armadillo;
import enemigos.Batbot;
import enemigos.Beebot;
import enemigos.Beecopter;
import enemigos.Enemy;
import enemigos.HammerBot;
import enemigos.MiniBot;

public class SecondStage extends MWindow {
	
	List<Rectangle> colitions = new ArrayList();
	List<Bullet> enemyBullets = new ArrayList();
	List<Bullet> bullets = new ArrayList();
	List<Bullet> bulletsControler = new ArrayList();
	List<Enemy> enemigos = new ArrayList();
	List<Enemy> enemigosAux = new ArrayList();
	List<Enemy> enemigosControler = new ArrayList();
	List<Effect> effects = new ArrayList();
	List<Effect> effectsControler = new ArrayList();
	
	Texture backGround;
	Sprite tscore;
	Texture levelTexture;
	Sprite backGroundSprite;
	int bgposX, bgposY;
	Sprite levelSprite;
	LifeBar lifeBar = new LifeBar();
	PowerBar powerBar = new PowerBar();
	Sound musicaFondo;
	Sound enemigoMuerte;
	MFont font;
	
	int score;
	
	private Player jugador1;
    private Camera mainCamera;

	public SecondStage(MainLoop ciclo)
	{
		super(ciclo);
		mainCamera = new Camera();
		font = new MFont(10,10);
		score = 0;
		tscore = new Sprite(new Texture("tittle/blueScore.png"));
		this.addBackground();
		this.addPlayer();
		this.addColitions();
		this.addEnemy();
		this.addSounds();
		windowCode = 4;
		
	}
	
	public void addSounds()
	{
		musicaFondo = Gdx.audio.newSound(Gdx.files.internal("stage2assets/stage2Music.mp3"));
		musicaFondo.loop();
		enemigoMuerte = Gdx.audio.newSound(Gdx.files.internal("enemigos/enemyDie.wav"));
		
	}
	
	public void addBackground()
	{

		backGround = new Texture("stage2assets/stage2b.png");
		levelTexture = new Texture("stage2assets/stag2.png");
		backGroundSprite = new Sprite(backGround);
		levelSprite = new Sprite(levelTexture);
		bgposX=4500;
		bgposY=-250;
		backGroundSprite.setPosition(bgposX,bgposY);
		backGroundSprite.setScale(4.0f, 4.0f);
		levelSprite.setPosition(3800, -210);
		levelSprite.setScale(2.0f,2.1f);		
	}
	
	public void addColitions()
	{
		colitions.add(new Rectangle(-350,200,100,800));
		colitions.add(new Rectangle(-350,140,700,70));
		colitions.add(new Rectangle(240,140,190,170));
		colitions.add(new Rectangle(530,260,410,50));
		colitions.add(new Rectangle(1070,260,380,50));
		colitions.add(new Rectangle(1585,0,185,310));
		colitions.add(new Rectangle(1750,0,215,180));
		colitions.add(new Rectangle(2030,260,500,50));
		colitions.add(new Rectangle(2600,260,500,50));
		colitions.add(new Rectangle(3210,130,500,50));
		colitions.add(new Rectangle(3435,-400,380,645));
		colitions.add(new Rectangle(3960,0,360,245));
		colitions.add(new Rectangle(3410,-500,1380,140));
		colitions.add(new Rectangle(4850,-400,680,100));
		colitions.add(new Rectangle(4850,-350,700,50));
		colitions.add(new Rectangle(5650,-320,610,50));
		colitions.add(new Rectangle(6340,-350,570,50));
		colitions.add(new Rectangle(7020,-350,700,50));
		colitions.add(new Rectangle(7735,-400,380,645));
		colitions.add(new Rectangle(8115,120,380,665));
		colitions.add(new Rectangle(8115,150,190,695));
		colitions.add(new Rectangle(8555,680,380,65));
		colitions.add(new Rectangle(9005,750,440,65));
		colitions.add(new Rectangle(9520,750,390,65));
		colitions.add(new Rectangle(10020,680,340,65));
		colitions.add(new Rectangle(10420,80,250,700));
		colitions.add(new Rectangle(10800,310,260,700));
		colitions.add(new Rectangle(10420,80,640,100));
		colitions.add(new Rectangle(11120,80,240,100));
		colitions.add(new Rectangle(11250,140,330,100));
		colitions.add(new Rectangle(11420,60,310,80));
		colitions.add(new Rectangle(11420,60,330,80));
		colitions.add(new Rectangle(11850,60,330,80));
		colitions.add(new Rectangle(11920,60,330,80));
		colitions.add(new Rectangle(12090,140,500,100));
		colitions.add(new Rectangle(12200,240,320,140));
		colitions.add(new Rectangle(12400,50,900,130));
		colitions.add(new Rectangle(12820,140,150,100));
		colitions.add(new Rectangle(13400,50,500,130));
		colitions.add(new Rectangle(13680,120,250,130));
		colitions.add(new Rectangle(14020,40,160,110));
		colitions.add(new Rectangle(14260,120,1000,130));
	}
	
	public void addPlayer()
	{
		jugador1 = new Player();
		jugador1.setPosX(300);
		jugador1.setPosY(500);
		jugador1.setColitions(colitions);
		jugador1.setBullets(bullets);
		jugador1.setEnemigos(enemigos);
		mainCamera.setPosY(jugador1.getPosY()+15);
		mainCamera.setPosX(jugador1.getPosX());
	}
	
	public void addEnemy()
	{
		//enemigos.add(new MiniBot(10,7646,278,colitions));
		enemigos.add(new Batbot(4,500,500));
		enemigos.add(new Armadillo(4,500,500,colitions));
	}
	
	
	public void update()
	{
		updatelifeBar();
		updateBackground();
		updateEnemy();
		updatePlayer();
		updateEnemyBullets();
		updateBullets();
		updateEffects();
		updateCamera();
	}
	
	public void draw(SpriteBatch batch,ShapeRenderer s1)
	{
		mainCamera.paint(batch);
		batch.begin();
		backGroundSprite.draw(batch);
		levelSprite.draw(batch);
		batch.end();
		jugador1.draw(batch);
		drawEnemy(batch);
		drawBullets(batch);
		drawLifeBar(batch);
		drawEffects(batch);
		
		drawHitbox(s1);
		
	}
	
	public void updateEffects()
	{
		for (Effect effect: effects) {
			
			effect.update();
		}
		
		for (Effect effect: effects) {
			
			if(effect.isFinish())
			{
				effectsControler.add(effect);
			}
			
		}
		
		effects.removeAll(effectsControler);
		effectsControler.removeAll(effectsControler);
	}
	public void drawEffects(SpriteBatch batch)
	{
		for (Effect effect: effects) {
			
			effect.draw(batch);
		}
	}
	
	public void updateCamera()
	{
		if(jugador1.getPosY() < 270)
		{
			mainCamera.setPosY(jugador1.getPosY()+15);
		}
		if(jugador1.getPosX() >400)
		{
			mainCamera.setPosX(jugador1.getPosX());
		}
		mainCamera.setPosX(jugador1.getPosX());
		
		mainCamera.setPosY(jugador1.getPosY()+30);
    	mainCamera.update();
	}
	
	public void updatePlayer()
	{

 		jugador1.hurts(enemigos);
 		jugador1.update();
 		System.out.println("x" + jugador1.getPosX() + "  y" + jugador1.getPosY());
 		
 		if(jugador1.getPosY()<-400)
 		{
 			jugador1.setPosX(400);
 			jugador1.setPosY(300);
 			
			addBackground();
			jugador1.setLife(jugador1.getLife()-2);
			mainCamera.unlock();
			mainCamera.setPosX(jugador1.getPosX());
			mainCamera.setPosY(jugador1.getPosY()+15);
 		}
 		
 		if(jugador1.getLife() <= 0)
 		{
 			musicaFondo.stop();
 			cicloPrincipal.setWindow(2);
 			
 		}
	}
	
	public void updatelifeBar()
	{
		lifeBar.setLife(jugador1.getLife());
		lifeBar.setPosition((int)mainCamera.getPosX()-400,(int)mainCamera.getPosY()+100);
		powerBar.setLife(jugador1.getEnergy());
		powerBar.setPosition((int)mainCamera.getPosX()-400,(int)mainCamera.getPosY()-20);
		font.setPosX((int)mainCamera.getPosX()-150);
		font.setPosY((int)mainCamera.getPosY()+242);
		tscore.setPosition((int)mainCamera.getPosX()-250,(int)mainCamera.getPosY()+225);
		tscore.setScale(2.0f,2.0f);
	}
	
	public void drawLifeBar(SpriteBatch batch)
	{
		lifeBar.draw(batch);
		if(jugador1.isPowerUp())
		{
			powerBar.draw(batch);
		}
		batch.begin();
		tscore.draw(batch);
		batch.end();
		font.draw(batch,Integer.toString(score));
	}
	
	
	public void drawHitbox(ShapeRenderer s1)
	{
		s1.setProjectionMatrix(mainCamera.combined);
		s1.begin(ShapeRenderer.ShapeType.Line);
		for (Rectangle rectangle : colitions) {
			
			  s1.rect(rectangle.getX(),rectangle.getY(),rectangle.getWidth(),rectangle.getHeight());
		}
		
		for (Bullet bullet : bullets) {
			
			s1.rect(bullet.getHitbox().getX(),bullet.getHitbox().getY(),bullet.getHitbox().width,bullet.getHitbox().height);
		}
		
		for (Enemy enemy : enemigos) {
			s1.rect(enemy.getHitbox().getX(),enemy.getHitbox().getY(),enemy.getHitbox().width,enemy.getHitbox().height);
		}
        
        s1.rect(jugador1.getHitbox().getX(),jugador1.getHitbox().getY(),jugador1.getHitbox().width,jugador1.getHitbox().height);
        s1.rect(jugador1.getLargeHitbox().getX(),jugador1.getLargeHitbox().getY(),jugador1.getLargeHitbox().width,jugador1.getLargeHitbox().height);
        s1.rect(jugador1.getLateralHitbox().getX(),jugador1.getLateralHitbox().getY(),jugador1.getLateralHitbox().width,jugador1.getLateralHitbox().height);
        s1.rect(jugador1.getUpHitbox().getX(),jugador1.getUpHitbox().getY(),jugador1.getUpHitbox().width,jugador1.getUpHitbox().height);
        s1.end();
	}
	
	public void updateBackground()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.D) && !jugador1.isOnWall() && !jugador1.touchWall() && !jugador1.isHurts() && !mainCamera.isLocked() && jugador1.getPosX() >400)
		{
			bgposX++;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.A) && !jugador1.isOnWall() && !jugador1.touchWall() && !jugador1.isHurts() && !mainCamera.isLocked() && jugador1.getPosX() >400)
		{
			bgposX--;
		}
		backGroundSprite.setPosition(bgposX,bgposY);
	}
	
	public void updateEnemyBullets()
	{
		for (Bullet bullet : enemyBullets) {
			bullet.update();
		}
		
		for (Bullet bullet : enemyBullets) {
			if(bullet.intersects(jugador1))
			{
				jugador1.hurts(bullet);
				bulletsControler.add(bullet);
			}
		}
		
		enemyBullets.removeAll(bulletsControler);
		bulletsControler.removeAll(bulletsControler);
	}
	
	public void updateBullets()
	{
		for (Bullet bullet : bullets) {
			bullet.update();
		}
		
		for (Enemy enemy : enemigos) {
			if(enemy.hurt(bullets))
			{
				System.out.println("hola");
			}
		}
		
		for (Bullet bullet : bullets) {
			if(bullet.intersects(enemigos))
			{
				bulletsControler.add(bullet);
			}
		}
		
		bullets.removeAll(bulletsControler);
		bulletsControler.removeAll(bulletsControler);
	}
	
	public void drawBullets(SpriteBatch batch)
	{
		for (Bullet bullet : bullets) {
			bullet.draw(batch);
			
		}
		
		for (Bullet bullet : enemyBullets) {
			bullet.draw(batch);
			
		}
	}
	
	public void updateEnemy()
	{
		for (Enemy enemy : enemigos) {
			if(enemy.getPosX()< jugador1.getPosX()+800)
			{
				enemy.update();
			}
			
		}
		
		for (Enemy enemy : enemigos) {
			if(enemy.getLife() <=0)
			{
				enemigosControler.add(enemy);
				enemigoMuerte.play();
				effects.add(new Effect(enemy.getPosX(),enemy.getPosY(),new Texture("efffects/explocion.png"),6,640,64,64,64,3.0f,3.0f,false));
				score+=100;
			}	
		}
		enemigos.addAll(enemigosAux);
		enemigosAux.removeAll(enemigosAux);
		enemigos.removeAll(enemigosControler);
		enemigosControler.removeAll(enemigosControler);
	}
	
	public void drawEnemy(SpriteBatch batch)
	{
		for (Enemy enemy : enemigos) {
			enemy.draw(batch);
		}
	}



}