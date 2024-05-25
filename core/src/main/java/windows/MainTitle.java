package windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

import mainPakage.MainLoop;

public class MainTitle extends MWindow{

	Texture title;
	Texture titleX;
	
	Sprite spriteTitle;
	Sprite spriteTitleX;
	
	public MainTitle(MainLoop ciclo) {
		super(ciclo);
		
		title = new Texture("tittle/title1.png");
		titleX = new Texture("tittle/titleX.png");
		spriteTitle = new Sprite(title);
		spriteTitle.setPosition(200, 300);
		spriteTitle.setScale(3.0f,3.0f);
		spriteTitleX = new Sprite(titleX);
		spriteTitleX.setPosition(520, 280);
		spriteTitleX.setScale(3.0f,3.0f);
	}

	
	public void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
		{
			cicloPrincipal.setWindow(1);
		}
	}



	public void draw(SpriteBatch batch, ShapeRenderer s1) {
		batch.begin();
		spriteTitle.draw(batch);
		spriteTitleX.draw(batch);
		batch.end();
		
	}

}
