package enemigos;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MiniBot extends Enemy {
	
	private float tiempoTranscurrido;
	private float tiempoTranscurridoAnimacion;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	private int direction = 0;
	
	private Rectangle verificador;
	
	List<Rectangle> colitions;

	public MiniBot(int life, float posx, float posy,List<Rectangle> colitions) {
		super(life, posx, posy);
		this.setTextura(new Texture("enemigos/monito.png"));
		this.createSprites(32,32);
		this.colitions = colitions;
		animationInicialRange = 0;
		animationFinalRange = 4;
		animationIndex = 0;
		hitbox = new Rectangle(posX,posY,32,32);
		verificador = new Rectangle(posX,posY,16,32);
		
	}
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<32;y+=spriteWeight)
       	{
       		for(int x = 0; x<260;x+=spriteHeight)
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
	
	public boolean isOnFloor()
    {
		if(direction == 0)
		{
			verificador.setPosition(posX-16, posY-16);
		}
		else
		{
			verificador.setPosition(posX+32, posY-16);
		}
    	
    	for (Rectangle colicion : colitions) {
    		if(verificador.overlaps(colicion))
        	{
        		
        		return true;
        	}
    	}
    	
    	return false;
    }

	@Override
	public void draw(SpriteBatch batch) {
		sprite[animationIndex].setPosition(posX, posY);
		if(direction == 0)
		{
			sprite[animationIndex].setScale(2.0f,2.0f);
		}
		else
		{
			sprite[animationIndex].setScale(-2.0f,2.0f);	
		}
		batch.begin();
		sprite[animationIndex].draw(batch);
		batch.end();
	}

	@Override
	public void update() {


		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>0.01)
    	{
    		if(direction == 0)
    		{
    			posX--;	
    		}
    		else
    		{
    			posX++;
    		}
    		
    		if(!isOnFloor())
    		{
    			if(direction == 0)
        		{
        			direction = 1;	
        		}
        		else
        		{
        			direction = 0;	
        		}
        		
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
	
	public void updateAnimation()
	{
		
		animationIndex++;
		if(animationIndex>animationFinalRange)
    	{
    		animationIndex = animationInicialRange;
    	}
		
		
	}

}