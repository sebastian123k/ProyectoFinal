package mainPakage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Effect {
	
	float posX,posY;
	Texture textura;
	Sprite[] sprite;
	int animationIndex;
	int animationFrames;
	float tiempoTranscurridoAnimacion;
	
	int textureX;
	int textureY;
	int textureAncho;
	int textureLargo;
	boolean reverse;
	boolean finish = false;
	
	public Effect(float posX, float posY, Texture textura, int animationFrames, int textureX, int textureY,
			int textureAncho, int textureLargo,float scaleX,float scaleY,boolean reverse) {
		sprite = new Sprite[16];
		this.posX = posX;
		this.posY = posY;
		this.textura = textura;
		this.animationFrames = animationFrames;
		this.textureX = textureX;
		this.textureY = textureY;
		this.textureAncho = textureAncho;
		this.textureLargo = textureLargo;
		createSprites(textureLargo,textureAncho,scaleX,scaleY);
		this.reverse = reverse;
		if(reverse)
		{
			animationIndex = animationFrames;
		}
		else
		{
			animationIndex = 0;
		}
	
		
	}

	public void draw(SpriteBatch batch) {
		sprite[animationIndex].setPosition(posX, posY);
		batch.begin();
		sprite[animationIndex].draw(batch);
		batch.end();
	}


	public void update() {
    	
    	tiempoTranscurridoAnimacion += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoAnimacion > 0.08)
    	{
    		updateAnimation();
        	tiempoTranscurridoAnimacion = 0;
    	}

	}
	
	public void updateAnimation()
	{
		if(!reverse)
		{
			animationIndex++;
			if(animationIndex>animationFrames)
	    	{
	    		finish = true;
	    	}
		}
		else
		{
			animationIndex--;
			if(animationIndex<0)
	    	{
	    		finish = true;
	    	}
		}
		
		
		
	}

	
	public void createSprites(int spriteHeight, int spriteWeight,float scaleX,float scaleY )
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<textureY;y+=spriteWeight)
       	{
       		for(int x = 0; x<textureX;x+=spriteHeight)
           	{
       			if(i<16)
       			{
       				Frames = new TextureRegion(textura,x,y,spriteHeight,spriteWeight);	
           			sprite[i] = new Sprite(Frames);
           			sprite[i].setScale(scaleX, scaleY);
           			sprite[i].setPosition(posX, posY);
           			           			
               		i++;
       			}
       			
           	}
       		
       	}
	}



	public boolean isFinish() {
		return finish;
	}
	
	public void reset()
	{
		if(!reverse)
		{
			animationIndex =0;
			finish = false;
		}
		else
		{
			animationIndex = animationFrames;;
			finish = false;
		}
		
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
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

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
	
	
	
	

}
