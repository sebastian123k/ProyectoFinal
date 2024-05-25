package mainPakage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Camera extends OrthographicCamera {

	float posX,posY;
	 
	boolean isLocked;
	public Camera()
	{
		 this.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 isLocked = false;
	}
	
	public void update()
	{
		super.update();
		if(!isLocked)
		{

			this.position.set(posX,posY, 0);
		}
	}
	
	
	public void paint(SpriteBatch batch)
	{
		batch.begin();
		batch.setProjectionMatrix(this.combined);
		batch.end();
	}
	
	public void lock()
	{
		isLocked = true;
	}
	
	public void unlock()
	{
		isLocked = false;
	}
	
	
	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public void setPosition(float posX ,float posY) {
		this.posX = posX;
		this.posY = posY;
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
	


	
}
