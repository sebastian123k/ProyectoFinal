package enemigos;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mainPakage.Bullet;

public abstract class Enemy {
	
	protected int life;
	protected float posX,posY;
	protected Rectangle hitbox;
	protected Texture textura;
	protected Sprite[] sprite;
	
	public Enemy(int life, float posx, float posy) {
		this.life = life;
		this.posX = posx;
		this.posY = posy;
		
		sprite = new Sprite[16];
	}
	
	public abstract void draw(SpriteBatch batch);
	public abstract void update();
	
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<textura.getWidth();y+=spriteWeight)
       	{
       		for(int x = 0; x<textura.getHeight();x+=spriteHeight)
           	{
       			if(i<16)
       			{
       				Frames= new TextureRegion(textura,x,y,spriteHeight,spriteWeight);	
           			sprite[i] = new Sprite(Frames);
           			sprite[i].setScale(2.0f, 2.0f);
           			sprite[i].setPosition(posX, posY);
           			           			
               		i++;
       			}
       			
           	}
       		
       	}
	}	

	public boolean hurt(List<Bullet> bullets)
	{
		hitbox.setPosition(posX, posY);
		for (Bullet bullet : bullets) {
			if(hitbox.overlaps(bullet.getHitbox()))
			{
				life-=bullet.getPower();
				System.out.println(life);
				return true;
			}
		}
		
		return false;
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Rectangle getHitbox() {
		hitbox.setPosition(posX, posY);
		return hitbox;
	}


	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public Texture getTextura() {
		return textura;
	}

	public void setTextura(Texture textura) {
		this.textura = textura;
	}

	public Sprite getSpriteIndex(int index) {
		return sprite[index];
	}	

}
