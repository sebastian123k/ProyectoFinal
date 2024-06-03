package enemigos;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mainPakage.Effect;

public class ShadowPart extends Enemy {
	private float tiempoTranscurrido;
	private float tiempoTranscurridoAnimacion;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	private int direction = 0;

	public ShadowPart(int life, float posx, float posy) {
		super(life, posx, posy);
		this.setTextura(new Texture("shadow/shadowDevilPart.png"));
		this.createSprites(64,64);
		animationInicialRange = 3;
		animationFinalRange = 6;
		animationIndex = 0;
		hitbox = new Rectangle(posX,posY,64,64);

	}
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<129;y+=spriteWeight)
       	{
       		for(int x = 0; x<700;x+=spriteHeight)
           	{
       			if(i<16)
       			{
       				Frames= new TextureRegion(textura,x,y,spriteHeight,spriteWeight);	
           			sprite[i] = new Sprite(Frames);
           			sprite[i].setPosition(posX, posY);
           			sprite[i].setScale(2.0f,2.0f);  
           			           			
               		i++;
       			}
       			
           	}
       		
       	}
	}	
	
	public void setAnimation(String animation)
	{
		switch(animation)
		{
		case("estatico"):
			animationInicialRange = 3;
			animationFinalRange = 6;
			break;
		case("desplazar"):
			animationInicialRange = 0;
			animationFinalRange = 2;
			break;
		case("desvanecer"):
			animationInicialRange = 7;
			animationFinalRange = 14;
			break;
		}
	}


	@Override
	public void draw(SpriteBatch batch) {
		sprite[animationIndex].setPosition(posX, posY);
		batch.begin();
		sprite[animationIndex].draw(batch);
		batch.end();
	}

	@Override
	public void update() {


		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>0.01)
    	{

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