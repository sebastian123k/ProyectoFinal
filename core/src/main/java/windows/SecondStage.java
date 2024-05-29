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
		musicaFondo = Gdx.audio.newSound(Gdx.files.internal("stage1assets/introStageMusic.mp3"));
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
		colitions.add(new Rectangle(-30,200,100,800));
		colitions.add(new Rectangle(0,170,1570,100));
	}
	
	public void addPlayer()
	{
		jugador1 = new Player();
		jugador1.setPosX(400);
		jugador1.setPosY(300);
		jugador1.setColitions(colitions);
		jugador1.setBullets(bullets);
		jugador1.setEnemigos(enemigos);
		mainCamera.setPosY(jugador1.getPosY()+15);
		mainCamera.setPosX(jugador1.getPosX());
	}
	
	public void addEnemy()
	{
		//enemigos.add(new MiniBot(10,7646,278,colitions));
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
		font.setPosX((int)mainCamera.getPosX()-150);
		font.setPosY((int)mainCamera.getPosY()+242);
		tscore.setPosition((int)mainCamera.getPosX()-250,(int)mainCamera.getPosY()+225);
		tscore.setScale(2.0f,2.0f);
	}
	
	public void drawLifeBar(SpriteBatch batch)
	{
		lifeBar.draw(batch);
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
				effects.add(new Effect(enemy.getPosX(),enemy.getPosY(),new Texture("efffects/explocion.png"),6,640,64,64,64));
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