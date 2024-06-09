package windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import mainPakage.Camera;
import mainPakage.MFont;
import mainPakage.MainLoop;

public class NewScoreScreen extends MWindow	implements InputProcessor{

	Texture title;
	Texture titleX;
	Texture megaman;
	MFont font2 = new MFont(320,300);
	MFont font = new MFont(370,250);
	MFont font3 = new MFont(370,220);
	String nombre = "";
	Sprite spriteTitle;
	Camera mainCamera = new Camera();
	public NewScoreScreen(MainLoop ciclo) {
		super(ciclo);
		
		title = new Texture("scoreScreen/marco.png");
		spriteTitle = new Sprite(title);
		spriteTitle.setPosition(350, 150);
		spriteTitle.setScale(2.0f,2.0f);
		windowCode = 7;
		Gdx.input.setInputProcessor(this);
		
	}
	
	public void update() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
		{
			cicloPrincipal.setWindow(0);
		}
		updateText();
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer s1) {
		batch.begin();
		spriteTitle.draw(batch);
		batch.end();
		font.draw(batch,nombre);
		font2.draw(batch,"ingresa tu nombre:");
		font3.draw(batch,"__________");
		mainCamera.paint(batch);
		
	}
	
	public void updateText()
	{
		if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE))
		{
			 nombre = "";
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
	   if(nombre.length()<10)
	   {
		   nombre = nombre + character;
	   }
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}
