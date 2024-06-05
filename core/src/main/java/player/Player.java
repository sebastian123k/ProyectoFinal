package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch; 
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import enemigos.Enemy;
import mainPakage.Bullet;

import com.badlogic.gdx.graphics.g2d.BitmapFont; 
import com.badlogic.gdx.graphics.Texture; 
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
	
	int life;
	float posX,posY;
	float speed;
	float gravity;
	float jumpForce;
	float shootDelay;
	
	float jumpHeight;
	int jumpsIndex;
	boolean isJumping;
	boolean isShoting;
	boolean hurts;
	boolean powerUp = true;
	int direction;
	int energy = 20;

	Texture texturaMegaman;
	Sprite[] spriteMegaman;
	Texture texturaShotingMegaman;
	Sprite[] spriteShotingMegaman;
	Texture texturaMegamanPower;
	Sprite[] spriteMegamanPower;
	Texture texturaShotingMegamanPower;
	Sprite[] spriteShotingMegamanPower;
	
	Texture texturaBullets;
	Sprite[] spriteBullets;
    Rectangle hitbox;
    Rectangle largeHitbox;
    Rectangle lateralHitbox;
    Rectangle upHitbox;
    
    float tiempoTranscurrido;
    float tiempoTranscurridoPower;
    float tiempoTranscurridoAnimacion;
    float tiempoTranscurridoHurt;
    int animationIndex;
    int	animationInicialRange;
    int animationFinalRange;
    
    List<Rectangle> colitions;
    List<Bullet> bullets;
    List<Enemy> enemigos;
    
    Sound sonidoSalto;
    Sound sonidoHurt;
    Sound sonidoShoot;
    Sound sonidoPowerShoot;
    
    public Player()
    {
    	this.initDefault();
    	this.createSprites();
    	
    }
    
    
    private void initDefault()
    {
    	posX = 100;
    	posY = 500;
    	gravity = 4f;
    	jumpForce = gravity*2+2;
    	jumpHeight = 3;
    	jumpsIndex = 0;
    	isJumping = false;
    	isShoting = false;
    	hurts = false;
    	life = 18;
    	direction = 1;
    	speed = 4;
    	shootDelay = 0;
    	
    	texturaMegaman = new Texture("player/playerSpritesheet.png");
    	spriteMegaman = new Sprite[33];
    	texturaShotingMegaman = new Texture("player/playerShootingSpritesheet.png");
    	spriteShotingMegaman = new Sprite[33];
    	texturaMegamanPower = new Texture("player/playerPowerSpritesheet.png");
    	spriteMegamanPower = new Sprite[33];
    	texturaShotingMegamanPower = new Texture("player/playerPowerShootingSpritesheet.png");
    	spriteShotingMegamanPower = new Sprite[33];
    	texturaBullets = new Texture("proyectil.png"); 
    	spriteBullets = new Sprite[30];
    	animationInicialRange = 0;
    	animationFinalRange = 3;
    	hitbox = new Rectangle();
    	hitbox.setSize(30,60);
    	lateralHitbox= new Rectangle();
    	lateralHitbox.setSize(30,40);
    	largeHitbox = new Rectangle();
    	largeHitbox.setSize(48,30);
    	upHitbox = new Rectangle();
    	upHitbox.setSize(26,60);
    	addSounds();
    	
    }
    
    public void addSounds()
	{
		sonidoSalto = Gdx.audio.newSound(Gdx.files.internal("Player/soundJump.wav"));
		sonidoShoot = Gdx.audio.newSound(Gdx.files.internal("Player/soundShoot.wav"));
		sonidoPowerShoot = Gdx.audio.newSound(Gdx.files.internal("Player/soundCharge.wav"));
		sonidoHurt = Gdx.audio.newSound(Gdx.files.internal("Player/soundHurt.wav"));
		
		
		
	}
    
    public void hurts(Bullet bullet)
    {
    	if(!hurts)
    	{
    		life-=bullet.getPower();
        	hurts = true;
        	sonidoHurt.play();
    	}
    }
    
    public void hurts(List<Enemy> enemigos)
    {
    	lateralHitbox.setPosition(posX+10, posY-15);
    	for (Enemy enemy : enemigos) {
    		if(lateralHitbox.overlaps(enemy.getHitbox()) && !hurts)
        	{
        		hurts = true;
        		life-=enemy.getPower();
        		sonidoHurt.play();
        		if(direction==1)
				{
					 posX-=speed*2;
				}
        		else
        		{
        			 posX+=speed*2; 
        		}
        	}
		}
    }
   
    public void createSprites()
   	{  	
       	TextureRegion megamanFrames;
        int spriteHeight = 40;
        int spriteWeight = 52;
       	
       	for(int y = 0,i = 0; y<156;y+=spriteWeight)
       	{
       		for(int x = 0; x<440;x+=spriteHeight)
           	{
       			if(i<33)
       			{
       				megamanFrames= new TextureRegion(texturaMegaman,x,y,spriteHeight,spriteWeight);	
           			spriteMegaman[i] = new Sprite(megamanFrames);
           			spriteMegaman[i].setScale(2.0f, 2.0f);
           			spriteMegaman[i].setPosition(posX, posY);
           			           			
           			megamanFrames= new TextureRegion(texturaShotingMegaman,x,y,spriteHeight,spriteWeight);	
           			spriteShotingMegaman[i] = new Sprite(megamanFrames);
           			spriteShotingMegaman[i].setScale(2.0f, 2.0f);
           			spriteShotingMegaman[i].setPosition(posX, posY);
           			
           			
               		i++;
       			}
       			
           	}
       		
       	}
       	
        spriteHeight = 48;
        spriteWeight = 48;

       	for(int y = 0,i = 0; y<256;y+=spriteWeight)
       	{
       		for(int x = 0; x<256;x+=spriteHeight)
           	{
       			if(i<27)
       			{
           			
           			megamanFrames= new TextureRegion(texturaBullets,x,y,spriteHeight,spriteWeight);	
           			spriteBullets[i] = new Sprite(megamanFrames);
           			spriteBullets[i].setScale(2.0f, 2.0f);
           			
               		i++;
       			}
       			
           	}
       		
       	}
      
   	}
    
    public void draw(SpriteBatch batch)
    {
    	batch.begin();
    	this.getSprite().draw(batch);
    	batch.end();
    }
      
    public void update()
    {
    	tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>0.01)
    	{
    		updatePosition();
        	tiempoTranscurrido = 0;
    	}
    	
    	tiempoTranscurridoAnimacion += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoAnimacion > 0.08)
    	{
    		updateAnimation();
        	tiempoTranscurridoAnimacion = 0;
    	}
    	
    }

	private void updatePosition()
    {
    	
		if(isOnFloor())
		{
			if(!isJumping && Gdx.input.isKeyJustPressed(Input.Keys.W)&& !hurts)
			{
				setAction("saltar");	
			}
			
		}
		else
		{
		
			if(touchWall() && !hurts)
			{
				posY-=gravity/2;
				if(Gdx.input.isKeyJustPressed(Input.Keys.W))
				{
					if(direction == 1)
					{
						posX-=10;
						direction = 0;
					}
					else
					{
						direction = 1;
						posX+=10;
					}
					setAction("saltar");
				}
			}
			else
			{
				posY-=gravity;
			}
			
		}
		
		if(isJumping && jumpsIndex<jumpHeight)
		{
			posY+=jumpForce;
			
			
		}
		if(touchCeiling())
		{
			posY-=jumpForce;
			isJumping = false;
			jumpsIndex = 0;
		}
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)&& !hurts)
		{
			 posX+=speed;
			 setAction("caminar");
			 if(direction==0)
			 {
				 direction = 1;
			 }
			 if(isOnWall() || isHurts())
			 {
				 posX-=speed; 
			 }
			 
		 }
		 if (Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)&& !hurts)
		 {
			 posX-=speed;
			 setAction("caminar");
			 if(direction==1)
			 {
				 direction = 0;
			 }
			 
			 if(isOnWall())
			 {
				 posX+=speed; 
			 }
		 }
		 
		 
		 if(Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.D))
		 {
			 setAction("nadota"); 
		 }
		 
		 if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.W)) {
			    setAction("nadota"); 
		 }
		 
		 if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isShoting && !hurts)
		 {
			 sonidoShoot.play();
			 isShoting = true;
			 if(touchWall() && !isOnFloor())
			 {
				 if(direction == 1)
				 {
					 bullets.add(new Bullet(posX-20,posY-10,0,10,2,8,spriteBullets,0,0,0,colitions));
				 }
				 else 
				 {
					 bullets.add(new Bullet(posX+20,posY-10,1,10,2,8,spriteBullets,0,0,0,colitions));
				 }
				 
			 }
			 else
			 {
				 bullets.add(new Bullet(posX,posY-10,direction,10,2,8,spriteBullets,0,0,0,colitions));
			 }
			 
			 
		 }
		 
		 if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !isShoting && !hurts)
		 {
			 if(powerUp && energy>0)
			 {
				 
				 tiempoTranscurridoPower += Gdx.graphics.getDeltaTime();
				 
			 }
			 
		 }
		 else
		 {
			 if(tiempoTranscurridoPower >1.0)
			 {
				 if(touchWall() && !isOnFloor())
				 {
					 if(direction == 1)
					 {
						 bullets.add(new Bullet(posX-20,posY-10,0,10,8,8,spriteBullets,5,9,0,colitions));
					 }
					 else 
					 {
						 bullets.add(new Bullet(posX+20,posY-10,1,10,8,8,spriteBullets,5,9,0,colitions));
					 }
					 
				 }
				 else
				 {
					 bullets.add(new Bullet(posX,posY-10,direction,10,8,8,spriteBullets,5,9,0,colitions));
				 }
				 tiempoTranscurridoPower = 0;
				 sonidoShoot.play();
				 isShoting = true;
				 energy -=2;
			 }
		 }
		 
		 if(isShoting)
		 {
			 shootDelay += Gdx.graphics.getDeltaTime();
			 
			 if(shootDelay>0.3)
			 {
				 isShoting = false;
				 shootDelay = 0;
			 }
		 }
		 
		 
		 if(hurts)
		 {
			 tiempoTranscurridoHurt += Gdx.graphics.getDeltaTime();
			 
			 if(tiempoTranscurridoHurt>0.5)
			 {
				 hurts=false;
				 tiempoTranscurridoHurt = 0;
				 System.out.println("mi vida es " + life);
			 }
		 }

     
    }
    
    private void updateAnimation()
    {
    	if(isJumping)
    	{
    		switch(jumpsIndex)
    		{
    		case 0:
    			animationIndex = 5;
    			break;
    		case 1:
    			animationIndex = 6;
    			break;
    		case 2:
    			animationIndex = 7;
    			break;
    		case 3:
    			animationIndex = 8;
    			break;
    		case 4:
    			animationIndex = 9;
    			isJumping = false;
    			jumpsIndex = 0;
    			
    			break;
    		
    		}
    		if(isOnFloor() || isOnWall())
    		{
    			isJumping = false;
    			jumpsIndex = 0;

    		}

    		
    		
    		jumpsIndex++;
    	}
    	else
    	{
    		
    		if (!isOnFloor() )
    		{
    			
    			animationIndex = 9;
    			
    			
    		}
    		else
    		{
    			animationIndex++;
            	
            	if(animationIndex>animationFinalRange)
            	{
            		animationIndex = animationInicialRange;
            	}
            	
    		}
    		
    	}
    	
    }
    
    public boolean touchWall()
    {
    	largeHitbox.setPosition(posX, posY+5);
    	
    	for (Rectangle colicion : colitions) {
    		if(largeHitbox.overlaps(colicion))
        	{
        		
    			animationInicialRange = 20;
    	    	animationFinalRange = 21;
    		
        		return true;
        	}
			
		}
 
    	return false;
    }
    
    public boolean isOnWall()
    {
    	lateralHitbox.setPosition(posX+10, posY-15);
    	
    	for (Rectangle colicion : colitions) {
    		if(lateralHitbox.overlaps(colicion))
        	{
        		   		
        		return true;
        	}								
    	}
    	
    	return false;
    }

    
    public boolean isOnFloor()
    {
    	hitbox.setPosition(posX+10, posY-25);
    	for (Rectangle colicion : colitions) {
    		if(hitbox.overlaps(colicion))
        	{
        		
        		return true;
        	}
    	}
    	
    	return false;
    }
    
    public boolean touchCeiling()
    {
    	upHitbox.setPosition(posX+12, posY-15);
    	for (Rectangle colicion : colitions) {
    		if(upHitbox.overlaps(colicion))
        	{
        		return true;
        	}
    	}
    	
    	return false;
    }
    
    public void setAction(String action)
    {
    	switch(action)
    	{
    		case "caminar":
    		animationInicialRange = 11;
    		animationFinalRange = 19;
    		
    			break;
    		case "nadota":
    			animationInicialRange = 0;
        		animationFinalRange = 3;
    			break;
    		case "escalar":
    			animationInicialRange = 20;
            	animationFinalRange = 21;
    			break;
    		case "saltar":
    			isJumping = true;
    			jumpsIndex = 0;
    			sonidoSalto.play();
    			break;
    		
    	}
    	if(isOnFloor() && !isJumping && (animationIndex<animationInicialRange || animationIndex>animationFinalRange))
    	{
    		animationIndex = animationInicialRange;
    	}
    }
    
    public Sprite getSprite()
    {
    	if(direction == 1)
    	{
    		spriteMegaman[animationIndex].setScale(2.0f,2.0f);
    		spriteMegaman[21].setPosition(posX, posY);
    		spriteMegaman[21].setScale(2.0f,2.0f);
    		
    		spriteShotingMegaman[animationIndex].setScale(2.0f,2.0f);
    		spriteShotingMegaman[22].setPosition(posX, posY);
    		spriteShotingMegaman[22].setScale(2.0f,2.0f);
    		
    		spriteMegaman[23].setPosition(posX, posY);
    		spriteMegaman[23].setScale(2.0f,2.0f);
    	}
    	else
    	{
    		spriteMegaman[animationIndex].setScale(-2.0f,2.0f);
    		spriteMegaman[21].setPosition(posX, posY);
    		spriteMegaman[21].setScale(-2.0f,2.0f);
    		
    		spriteShotingMegaman[animationIndex].setScale(-2.0f,2.0f);
    		spriteShotingMegaman[22].setPosition(posX, posY);
    		spriteShotingMegaman[22].setScale(-2.0f,2.0f);
    		
    		spriteMegaman[23].setPosition(posX, posY);
    		spriteMegaman[23].setScale(-2.0f,2.0f);
    	}
    	
    	spriteMegaman[animationIndex].setPosition(posX, posY);
    	spriteShotingMegaman[animationIndex].setPosition(posX, posY);
    	
    	if(hurts)
    	{
    		return spriteMegaman[23];
    	}
    	
    	if(isShoting)
    	{
    		
    		
    		if(!isOnFloor() && touchWall() && !isJumping)
        	{	
        		return spriteShotingMegaman[22];
        	}
    		else
    		{
    			return spriteShotingMegaman[animationIndex];
    		}
    		
    	}
    	else
    	{
    		if(!isOnFloor() && touchWall() && !isJumping && !hurts )
        	{	
        		return spriteMegaman[21];
        	}
    	}
    	
    	
    	return spriteMegaman[animationIndex];
    	
    }
    
    public Rectangle getHitbox()
    {
    	hitbox.setPosition(posX+10, posY-25);
    	return hitbox;
    }
    
    public Rectangle getUpHitbox()
    {
    	upHitbox.setPosition(posX+12, posY-15);
    	return upHitbox;
    }
    public Rectangle getLargeHitbox()
    {
    	largeHitbox.setPosition(posX, posY+5);
    	return largeHitbox;
    }
    
    public Rectangle getLateralHitbox()
    {
    	lateralHitbox.setPosition(posX+10, posY-15);
    	return lateralHitbox;
    }
    
    public boolean isPowerUp() {
		return powerUp;
	}


	public void setPowerUp(boolean powerUp) {
		this.powerUp = powerUp;
		if(powerUp)
		{
			spriteMegaman = spriteMegamanPower;
			spriteShotingMegaman = spriteShotingMegamanPower;
		}
	}


	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public List<Rectangle> getColitions() {
		return colitions;
	}

	public void setColitions(List<Rectangle> colitions) {
		this.colitions = colitions;
	}



	public List<Bullet> getBullets() {
		return bullets;
	}



	public void setBullets(List<Bullet> bullets) {
		this.bullets = bullets;
	}


	public List<Enemy> getEnemigos() {
		return enemigos;
	}


	public void setEnemigos(List<Enemy> enemigos) {
		this.enemigos = enemigos;
	}


	public boolean isHurts() {
		return hurts;
	}


	public void setHurts(boolean hurts) {
		this.hurts = hurts;
	}
	


	public int getEnergy() {
		return energy;
	}


	public void setEnergy(int energy) {
		this.energy = energy;
	}


	public int getLife() {
		return life;
	}


	public void setLife(int life) {
		this.life = life;
	} 
	
	
     
}
