package windows;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import mainPakage.MainLoop;

public abstract class MWindow {

	MainLoop cicloPrincipal;
	int windowCode;
	public MWindow(MainLoop ciclo)
	{
		this.cicloPrincipal = ciclo;
	}
	
	public abstract void draw(SpriteBatch batch,ShapeRenderer s1);
	public abstract void update();
	
	public int getWindowCode() {
		return windowCode;
	}

}
