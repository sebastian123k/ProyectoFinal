package mainPakage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MFont {
	
	protected float posX,posY;
	protected Texture textura;
	protected Sprite[] sprite;
	
	public MFont(int x, int y) 
	{
		posX = x;
		posY = y;
		sprite = new Sprite[32];
		textura = new Texture("font/blue.png");
		this.createSprites(15,12);
		
	}
	
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;
	
	   	for(int y = 0,i = 0; y<96;y+=spriteWeight)
	   	{
	   		for(int x = 0; x<240;x+=spriteHeight)
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

	public void draw(SpriteBatch batch,String texto) {
		batch.begin();
		for(int x = 0;x<texto.length();x++)
		{
			sprite[getLetra(texto.charAt(x))].setPosition(posX+x*12, posY);
			sprite[getLetra(texto.charAt(x))].draw(batch);
		}
		batch.end();
	}
	
	public int getLetra(char letra)
	{
		switch(letra)
		{
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		
		}
		return 0;
	}
}