package mainPakage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Camera extends OrthographicCamera {

	float posX,posY;
	 
	public Camera()
	{
		 this.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public void update()
	{
		super.update();
		this.position.set(posX,posY, 0);
	}
	
	public void paint(SpriteBatch batch)
	{
		batch.begin();
		batch.setProjectionMatrix(this.combined);
		batch.end();
	}
	
	public void setPosition(float posX ,float posY) {
		this.posX = posX;
		this.posY = posY;
	}


	
}
