package windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import mainPakage.MainLoop;

public class ScoreScreen extends MWindow {

	Texture title;
	Texture titleX;
	Texture megaman;
	
	Sprite spriteTitle;
	
	public ScoreScreen(MainLoop ciclo) {
		super(ciclo);
		
		title = new Texture("scoreScreen/marco.png");
		spriteTitle = new Sprite(title);
		spriteTitle.setPosition(300, 150);
		spriteTitle.setScale(2.0f,2.0f);

		
		windowCode = 3;
		
	}
	
	public void update() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
		{
			cicloPrincipal.setWindow(0);
		}
		
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer s1) {
		batch.begin();
		spriteTitle.draw(batch);
		batch.end();
		
	}

}
