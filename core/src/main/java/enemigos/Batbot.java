package enemigos;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mainPakage.Camera;
import player.Player;

public class Batbot extends Enemy{
	private float tiempoTranscurrido;
	private float tiempoTranscurridoAnimacion;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	int direction = 1;

	Player player;
	public Batbot(int life, float posx, float posy,Player player) {
		super(life, posx, posy);
		
		this.setTextura(new Texture("enemigos/batbot.png"));
		this.createSprites(64,64);
		this.player = player;
		animationInicialRange = 0;
		animationFinalRange = 2;
		animationIndex = 0;
		hitbox = new Rectangle(posX,posY,50,50);
		
	}
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<25;y+=spriteWeight)
       	{
       		for(int x = 0; x<192;x+=spriteHeight)
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
	
	
	@Override
	public void draw(SpriteBatch batch) {
		sprite[animationIndex].setPosition(posX,posY);
		batch.begin();
		sprite[animationIndex].draw(batch);
		batch.end();
	}

	@Override
	public void update() {


		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>0.01)
    	{
    		if(player.getPosX()-350<posX)
    		{
    			
    		}
    		else
    		{
    			direction = 0;
    		}
    		if(direction == 1)
    		{
    			posX-=3;
    		}else
    		{
    			posX += 4;
    		}
    		
    		tiempoTranscurrido=0;
    	}
    	
    	tiempoTranscurridoAnimacion += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoAnimacion > 0.08)
    	{
    		updateAnimation();
        	tiempoTranscurridoAnimacion = 0;
    	}

	}

	public Rectangle getHitbox() {
		hitbox.setPosition(posX, posY+50);
		return hitbox;
	}

	
	public void updateAnimation()
	{
		
		animationIndex++;
		if(animationIndex>animationFinalRange)
    	{
    		animationIndex = animationInicialRange;
    	}
		
		
	}

}