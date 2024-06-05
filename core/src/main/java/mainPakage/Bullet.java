package mainPakage;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import enemigos.Enemy;
import player.Player;

public class Bullet {
	
	float posX,posY;
	int direction;
	int speed;
	int power;
	int size;
	
	Sprite[] bulletSprite;
	Rectangle hitbox;
	int spawnFramesRange;
	int	animationFrameRange;
	int hitFrameRange;
	int animationIndex;
	float tiempoTranscurridoAnimacion;
	float tiempoTranscurrido;
	List<Rectangle> colitions;
	
	public Bullet(float posX, float posY, int direction, int speed, int power, int size, Sprite[] bulletSprite,
			int spawnFramesRange, int animationFrameRange, int hitFrameRange,List<Rectangle> colitions) {

		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.speed = speed;
		this.power = power;
		this.size = size;
		this.bulletSprite = bulletSprite;
		this.spawnFramesRange = spawnFramesRange;
		this.animationFrameRange = animationFrameRange;
		this.hitFrameRange = hitFrameRange;
		this.colitions = colitions;
		
		animationIndex = spawnFramesRange;
		hitbox = new Rectangle(posX+size,posY+size,size,size);
	}
	
	public Bullet(float posX, float posY, int direction, int speed, int power, int size, Sprite[] bulletSprite,
			int spawnFramesRange, int animationFrameRange, int hitFrameRange) {

		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		this.speed = speed;
		this.power = power;
		this.size = size;
		this.bulletSprite = bulletSprite;
		this.spawnFramesRange = spawnFramesRange;
		this.animationFrameRange = animationFrameRange;
		this.hitFrameRange = hitFrameRange;
		this.colitions = colitions;
		
		animationIndex = spawnFramesRange;
		hitbox = new Rectangle(posX+size,posY+size,size,size);
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
	
	public void updatePosition()
	{
		if(direction == 1)
		{
			posX+=speed;
		}
		else
		{
			posX-=speed;
		}
		
		hitbox.setPosition(posX+size*2,posY+size*2);
	}
	
	public void updateAnimation()
	{

		animationIndex++;
    	if(animationIndex>animationFrameRange)
    	{
    		animationIndex = spawnFramesRange;
    	}
		
	}
	
	public boolean intersects(List<Enemy> enemigos) 
	{
		hitbox.setPosition(posX+size*2,posY+size*2);
    	
		if(colitions!=null)
		{
			for (Rectangle colicion : colitions) {
	    		if(hitbox.overlaps(colicion))
	        	{
	        		   
	        		return true;
	        	}	
	    	}
		}
    	
    	for (Enemy enemy : enemigos) {
    		if(hitbox.overlaps(enemy.getHitbox()))
        	{
        		   
        		return true;
        	}	
    	}
    	
    	return false;
	}
	
	public boolean intersects(Player jugador) 
	{
		hitbox.setPosition(posX+size*2,posY+size*2);	
    
    		if(hitbox.overlaps(jugador.getHitbox()))
        	{
        		return true;
        	}	
 
    	
    	return false;
	}
	
	
	public void draw(SpriteBatch batch)
	{
		if(direction == 1)
		{
			bulletSprite[animationIndex].setScale(2.0f,2.0f);
		}
		else
		{
			bulletSprite[animationIndex].setScale(-2.0f,2.0f);
		}
		hitbox.setPosition(posX+size*2,posY+size*2);
		bulletSprite[animationIndex].setPosition(posX, posY);
		batch.begin();
		bulletSprite[animationIndex].draw(batch);
		batch.end();
	}

	public Rectangle getHitbox() {
		hitbox.setPosition(posX+size*2,posY+size*2);
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	
}
