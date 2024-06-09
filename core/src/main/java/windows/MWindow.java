package windows;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import mainPakage.MainLoop;

public abstract class MWindow {

	MainLoop cicloPrincipal;
	int windowCode;
	int seccion = 0;
	int score;
	public MWindow(MainLoop ciclo)
	{
		this.cicloPrincipal = ciclo;
	}
	
	
	public int getSeccion() {
		return seccion;
	}


	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}


	public abstract void draw(SpriteBatch batch,ShapeRenderer s1);
	public abstract void update();
	
	public int getWindowCode() {
		return windowCode;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	

}
