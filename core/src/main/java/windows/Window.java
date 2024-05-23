package windows;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Window {
	
	
	public Window()
	{
		
	}
	
	public abstract void draw(SpriteBatch batch,ShapeRenderer s1);
	
	public abstract void update();
	

}
