package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LifeBar {
	
	int life;
	int posX;
	int posY;
	
	Sprite lifeBar;
	Sprite lifePoint;
	
	public LifeBar()
	{
		lifeBar = new Sprite(new Texture("Player/Hud.png"));
		lifeBar.setScale(2.0f,2.0f);
		lifePoint = new Sprite(new Texture("Player/vitPoint.png"));
		lifePoint.setScale(2.0f,2.0f);
	}
	
	public void draw(SpriteBatch batch)
	{
		lifeBar.setPosition(posX,posY);
		batch.begin();
		lifeBar.draw(batch);
		batch.end();
		drawlifeBar(batch);
	}
	
	public void setPosition(int x, int y)
	{
		posX = x;
		posY = y;
	}
	
	public void drawlifeBar(SpriteBatch batch)
	{
		for(int i = 0; i<life; i++)
		{
			lifePoint.setPosition(posX+5, (posY+i*4)+5);
			batch.begin();
			lifePoint.draw(batch);
			batch.end();
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	
	
}
