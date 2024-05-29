package enemigos;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import mainPakage.Camera;
import player.Player;

public class Beecopter extends Enemy {
	
	private float tiempoTranscurrido;
	private float tiempoTranscurridoAnimacion;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	private Camera camara;
	private Player player;
	private boolean isDestroyed;
	private List<Enemy> enemigos;

	public Beecopter(int life, float posx, float posy,Camera camara,Player jugador,List<Enemy> enemigos) {
		super(life, posx, posy);
		this.enemigos = enemigos;
		this.camara = camara;
		this.player = jugador;
		
		this.setTextura(new Texture("enemigos/beecopter.png"));
		this.createSprites(150,120);
		
		animationInicialRange = 0;
		animationFinalRange = 2;
		isDestroyed = false;
		hitbox = new Rectangle(posX,posY,150,150);
		
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

		if(life<5)
		{
			isDestroyed = true;
		}
		if(!isDestroyed && posX< player.getPosX()+500)
		{
			camara.lock();
		}
		if(isDestroyed)
		{
			camara.unlock();
		}
		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>3)
    	{
    		if(isDestroyed)
    		{
    			posY--;
    		}
    		else
    		{
    			if((int)(Math.random() * 100) == 1)
    			{
    				enemigos.add(new Beebot(4,posX,posY+(int)(Math.random() * 150)));
    			}
    			posX--;
    		}
    	}
    	
    	tiempoTranscurridoAnimacion += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoAnimacion > 0.06)
    	{
    		updateAnimation();
        	tiempoTranscurridoAnimacion = 0;
    	}

	}
	
	public void updateAnimation()
	{
		if(!isDestroyed)
		{
			animationIndex++;
			if(animationIndex>animationFinalRange)
	    	{
	    		animationIndex = animationInicialRange;
	    	}
		}
		else
		{
			animationIndex = 3;
		}
		
	}

}
