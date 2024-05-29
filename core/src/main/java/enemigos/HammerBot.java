package enemigos;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class HammerBot extends Enemy{

	private float tiempoTranscurrido;
	private float tiempoTranscurridoAnimacion;
	private float tiempoTranscurridoEstados;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	private boolean isFlying = false;
	private int direccion = 0;
	private boolean punch = false;
	private boolean returning = false;


	public HammerBot(int life, float posx, float posy) {
		super(life, posx, posy);
		
		this.setTextura(new Texture("enemigos/robotsitoMalo.png"));
		this.createSprites(64,64);
		animationInicialRange = 4;
		animationFinalRange = 10;
		animationIndex = 0;
		hitbox = new Rectangle(posX,posY,50,50);
		
	}
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<192;y+=spriteWeight)
       	{
       		for(int x = 0; x<320;x+=spriteHeight)
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
		sprite[animationIndex].setPosition(posX, posY);
		if(direccion == 1)
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

		tiempoTranscurridoEstados += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoEstados>1.5)
    	{
    		returning = false;
    		changeAction();
    		tiempoTranscurridoEstados=0;
    		
    	}

		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurrido>0.01)
    	{
    		updatePosition();
    		tiempoTranscurrido=0;
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
		if(isFlying)
		{
			if(direccion == 1)
			{
				posX+=2;
			}
			else
			{
				posX-=2;
			}
		}
		
		if(punch)
		{
			posY-=2;
		}
		if(returning)
		{
			posY++;
		}
		
	}
	
	public void changeAction()
	{
		System.out.println((int)(Math.random() * 2));

		if((int)(Math.random() * 2) == 1)
		{
			if((int)(Math.random() * 2) == 1)
			{
				direccion = 0;
				isFlying = true;
				animationInicialRange = 2;
				animationFinalRange = 3;
				animationIndex=0;
			}
			else
			{
				direccion = 1;
				isFlying = true;
				animationInicialRange = 2;
				animationFinalRange = 3;
				animationIndex=0;
			}
		}
		else
		{
			if((int)(Math.random() * 2) == 1)
			{
				punch = true;
				isFlying = false;
				animationInicialRange = 4;
				animationFinalRange = 10;
			}
			else
			{
				punch = false;
				isFlying = false;
				animationInicialRange = 4;
				animationFinalRange = 10;
			}
			
		}
	
	}
	
	public void updateAnimation()
	{
		if(isFlying && animationIndex<2)
		{
			animationIndex++;
		}
		else if(isFlying)
		{
			animationIndex++;
			if(animationIndex>animationFinalRange)
	    	{
	    		animationIndex = animationInicialRange;
	    	}
		}
		
		if(punch && animationIndex<14)
		{
			animationIndex++;
		}
		if(animationIndex == 14)
		{
			punch = false;
			tiempoTranscurridoEstados=0;
			returning = true;
		}
		if(!isFlying && !punch)
		{
			animationIndex++;
			if(animationIndex>animationFinalRange)
	    	{
	    		animationIndex = animationInicialRange;
	    	}
			
			
		}
		

	}
	public Rectangle getHitbox() {
		if(punch)
		{
			hitbox.setPosition(posX, posY-40);
			return hitbox;
		}
		hitbox.setPosition(posX, posY);
		return hitbox;
	}
	
}

