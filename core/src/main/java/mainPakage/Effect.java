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
	
	boolean finish = false;
	
	public Effect(float posX, float posY, Texture textura, int animationFrames, int textureX, int textureY,
			int textureAncho, int textureLargo) {
		sprite = new Sprite[16];
		this.posX = posX;
		this.posY = posY;
		this.textura = textura;
		this.animationFrames = animationFrames;
		this.textureX = textureX;
		this.textureY = textureY;
		this.textureAncho = textureAncho;
		this.textureLargo = textureLargo;
		createSprites(textureLargo,textureAncho);
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
		
		animationIndex++;
		if(animationIndex>animationFrames)
    	{
    		finish = true;
    	}
		
		
	}

	
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<textureY;y+=spriteWeight)
       	{
       		for(int x = 0; x<textureX;x+=spriteHeight)
           	{
       			if(i<16)
       			{
       				Frames= new TextureRegion(textura,x,y,spriteHeight,spriteWeight);	
           			sprite[i] = new Sprite(Frames);
           			sprite[i].setScale(3.0f, 3.0f);
           			sprite[i].setPosition(posX, posY);
           			           			
               		i++;
       			}
       			
           	}
       		
       	}
	}


	public boolean isFinish() {
		return finish;
	}


	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	

}
