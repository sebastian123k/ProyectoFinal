package enemigos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import mainPakage.Effect;

public class ShadowDevil extends Enemy {
	
	private float tiempoTranscurrido;
	private float tiempoTranscurridoCompleto;
	private float tiempoTranscurridoAnimacion;
	private int animationIndex;
	private int	animationInicialRange;
	private int animationFinalRange;
	private int direction = 0;
 	private boolean move = true;
 	private boolean down = false;
 	private boolean up = false;
 	private boolean drawBody = false;
 	private boolean drawParts = true;
 	private boolean completo = false;
 	int spritePosX;
 	int spritePosY;
 	List<Effect> effects;
 	Effect efecto;
	
	ShadowPart partes[][] = new ShadowPart[4][4];
	int partesPosY[][] = new int [4][4];
	
	public ShadowDevil(int life, float posx, float posy,List<Effect> effects) {
		super(life, posx, posy);
		this.setTextura(new Texture("shadow/shadowDevilBody.png"));
		this.createSprites(256,256);
		animationInicialRange = 2;
		animationFinalRange = 2;
		animationIndex = 0;
		hitbox = new Rectangle(posX,posY,1,1);
		this.effects = effects;
		spritePosY = (int)posY;
		spritePosX = (int)posX;
		addShadowParts();
		efecto = new Effect(posX-250,posY-20,new Texture("shadow/shadowDevilEffect.png"),13,576,640,128,192,3.0f,2.0f,false);
		efecto.setFinish(true);
		tiempoTranscurridoCompleto = 0;
	
	}
	
	public void addShadowParts()
	{
		for(int i = 0;i<4;i++)
		{
			for(int j = 0;j<4;j++)
			{
				partes[i][j] = new ShadowPart(100000,posX+(i*80),posY+(j*80)-20);
				partesPosY[i][j] = (int)partes[i][j].getPosY();
			}
		}
		
	}
	
	
	public void createSprites(int spriteHeight, int spriteWeight)
	{
		TextureRegion Frames;

       	for(int y = 0,i = 0; y<512;y+=spriteWeight)
       	{
       		for(int x = 0; x<512;x+=spriteHeight)
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
	


	@Override
	public void draw(SpriteBatch batch) {
		if(drawParts)
		{
			for(int i = 0;i<4;i++)
			{
				for(int j = 0;j<4;j++)
				{

					partes[i][j].draw(batch);
				}
			}

		}
		batch.begin();
		if(drawBody && direction == 0)
		{
			sprite[animationIndex].setPosition(spritePosX-250, spritePosY);
			sprite[animationIndex].setScale(-2.0f,2.0f);
			sprite[animationIndex].draw(batch);
		}
		if(drawBody && direction == 1)
		{
			sprite[animationIndex].setPosition(spritePosX+330, spritePosY);
			sprite[animationIndex].setScale(2.0f,2.0f);
			sprite[animationIndex].draw(batch);
		}
		
		batch.end();
	}

	@Override
	public void update() {

		tiempoTranscurrido += Gdx.graphics.getDeltaTime();
		
    	if(tiempoTranscurrido>0.01)
    	{
    		for(int i = 0;i<4;i++)
    		{
    			for(int j = 0;j<4;j++)
    			{
    				partes[i][j].update();
    			}
    		}

    		if(move)
    		{
    			moveParts();
    		}
    		
    		if(down)
    		{
    			downParts();
    		}
    		
    		if(up)
    		{
    			upParts();
    		}
    		
    		if(completo && efecto.isFinish())
    		{
				drawBody = true;
				tiempoTranscurridoCompleto += Gdx.graphics.getDeltaTime();
				if(tiempoTranscurridoCompleto > 5)
				{
					completo = false;
					tiempoTranscurridoCompleto =0;
					efecto.setReverse(true);
					efecto.reset();
					effects.add(efecto);
					up = true;
					drawParts = true;
					drawBody = false;
				}
    		}
    		
    		
    		tiempoTranscurrido =0;
    	}
    	
    	tiempoTranscurridoAnimacion += Gdx.graphics.getDeltaTime();
    	if(tiempoTranscurridoAnimacion > 0.1)
    	{
    		updateAnimation();
        	tiempoTranscurridoAnimacion = 0;
        	
    	}

	}
	
	public void moveParts()
	{

		if(direction == 1)
		{
			boolean romper = false;
			for(int i = 3;i>-1;i--)
			{
				for(int j = 0;j<4;j++)
				{
					if(partes[i][j].getPosX()< posX+300 + i*80)
					{
						partes[i][j].setPosX(partes[i][j].getPosX() +10);
						partes[i][j].setAnimation("desplazar");
						romper = true;
						break;
					}
					else
					{
						partes[i][j].setAnimation("estatico");
						if(i==0 && j ==3)
						{
							System.out.println("terminado");
							move = false;
							down=true;
						}
					}
				
				}
				if(romper)
				{
					break;
				}
				
			}
			
		
		}
		
		if(direction == 0)
		{
			boolean romper = false;
			for(int i = 0;i<4;i++)
			{
				for(int j = 0;j<4;j++)
				{
					if(partes[i][j].getPosX()> posX-300 + i*80)
					{
						partes[i][j].setPosX(partes[i][j].getPosX() -10);
						partes[i][j].setAnimation("desplazar");
						romper = true;
						break;
					}
					else
					{
						partes[i][j].setAnimation("estatico");
						if(i==3 && j ==3)
						{
							System.out.println("terminado");
							move = false;
							down = true;
						}
					}
				
				}
				if(romper)
				{
					break;
				}
				
			}
		
		}
		
	}
	public void downParts()
	{
		for(int i = 0;i<4;i++)
		{
			for(int j = 0;j<4;j++)
			{
				if(partes[j][i].getPosY()> posY-50)
				{
					partes[j][i].setPosY(partes[j][i].getPosY()-2);
					partes[j][i].setAnimation("desvanecer");
					
				}
				else if(j == 3 && i ==3)
				{
					System.out.println("terminado");
					down = false;
					if(direction == 0)
					{
						efecto.setReverse(false);
						efecto.setPosX(posX -250);
						efecto.reset();
						effects.add(efecto);
						drawParts = false;
						completo =true;
						animationIndex = 0;
					}
					else
					{
						efecto.setReverse(false);
						efecto.setPosX(posX +330);
						efecto.reset();
						effects.add(efecto);
						drawParts = false;
						completo =true;
						animationIndex = 0;
					}
					
				}
			}
			
		}
		
		
	}
	
	public void upParts()
	{
		for(int i = 0;i<4;i++)
		{
			for(int j = 0;j<4;j++)
			{
				if(partes[j][i].getPosY()< partesPosY[j][i])
				{
					partes[j][i].setPosY(partes[j][i].getPosY()+2);
					partes[j][i].setAnimation("estatico");
					
				}
				else if(j == 3 && i ==3)
				{
					System.out.println("terminado");
					up = false;
					if(direction == 0)
					{
						efecto.setReverse(true);
						direction = 1;
						move = true;
					}
					else
					{
						efecto.setReverse(true);
						direction = 0;
						move = true;
					}
					
				}
			}
			
		}
		
		
	}
	
	public void updateAnimation()
	{
		
		if(drawBody && animationIndex <2)
		{
			animationIndex++;
		}
		
		
	}

}