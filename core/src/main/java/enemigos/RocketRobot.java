package enemigos;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mainPakage.Bullet;

public class RocketRobot extends Enemy {
	

	private float tiempoTranscurrido;
	private float tiempoTranscurridoAnimacion;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	private boolean animationReset;
	List<Bullet> enemyBullets;
	Texture texturaBullets;
	Sprite[] spriteBullets;
	

	public RocketRobot(int life, float posx, float posy,List<Bullet> enemyBullets) {
		super(life, posx, posy);
		
		this.setTextura(new Texture("enemigos/tiramisiles.png"));
		this.createSprites(64,64);
	
		this.setAction("estatico");
		this.enemyBullets = enemyBullets;
		hitbox = new Rectangle(posX,posY,64,64);
		animationReset = false;
		texturaBullets = new Texture("proyectil.png"); 
    	spriteBullets = new Sprite[30];
    	this.createBulletSprites(48, 48);
		
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		sprite[animationIndex].setPosition(posX, posY);
		batch.begin();
		sprite[animationIndex].draw(batch);
		batch.end();
	}

	@Override
	public void update() {
		
		updateActions();
    	
    	tiempoTranscurridoAnimacion += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoAnimacion > 0.08)
    	{
    		updateAnimation();
        	tiempoTranscurridoAnimacion = 0;
    	}
	}
	
	public void updateAnimation()
	{
		if(animationReset)
		{
			animationIndex--;
		}
		
		else
		{
			animationIndex++;
			if(animationIndex>animationFinalRange)
	    	{
	    		animationIndex = animationInicialRange;
	    	}
		}
		
		if((animationIndex == 12 || animationIndex == 15) && !animationReset)
		{
			animationReset = true;
			shot();
			
		}
		
		if(animationIndex <= 0)
		{
			animationReset = false;
			this.setAction("estatico");
		}
		
    	
	}
	public void updateActions()
	{
		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>3)
    	{
    		this.setAction("disparar");
        	tiempoTranscurrido = 0;
    	}
    	
    	if(animationIndex == 11 && !animationReset)
		{
    		int random = (int)(Math.random() * 2 +1);
 
    		if(random == 1)
    		{
    			this.setAction("misilaso");
    		}
    		else
    		{
    			this.setAction("bolaElectrica");
    		}
			
		}
	
		
	}
	
	public void shot()
	{
		enemyBullets.add(new Bullet(posX,posY-10,0,10,2,8,spriteBullets,0,0,0));
	}
	
	public void createBulletSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

		for(int y = 0,i = 0; y<texturaBullets.getWidth();y+=spriteWeight)
       	{
       		for(int x = 0; x<texturaBullets.getHeight();x+=spriteHeight)
           	{
       			if(i<27)
       			{
           			
           			Frames = new TextureRegion(texturaBullets,x,y,spriteHeight,spriteWeight);	
           			spriteBullets[i] = new Sprite(Frames);
           			spriteBullets[i].setScale(2.0f, 2.0f);
           			
               		i++;
       			}
       			
           	}
       		
       	}
	}	
	
	public void setAction(String action)
	{
		switch(action)
		{
		case"estatico":
			animationInicialRange = 0;
			animationFinalRange = 1;
			break;
		case"disparar":
			animationInicialRange = 2;
			animationFinalRange = 11;
			break;
		case"misilaso":
			animationInicialRange = 12;
			animationFinalRange = 12;
			break;
		case"bolaElectrica":
			animationInicialRange = 13;
			animationFinalRange = 15;
			break;
		}
		if(animationIndex<animationInicialRange || animationIndex>animationFinalRange)
    	{
    		animationIndex = animationInicialRange;
    	}
	}
}
