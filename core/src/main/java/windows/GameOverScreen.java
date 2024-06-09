package windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import mainPakage.Camera;
import mainPakage.MainLoop;

public class GameOverScreen extends MWindow{
	Texture title;
	Texture titleX;
	Texture megaman;
	
	Sprite spriteTitle;
	Sprite spriteTitleX;
	Sprite spriteMegaman;
	Sprite spriteScore;
	Sprite spriteStart;
	
	Camera camara = new Camera();
	public GameOverScreen(MainLoop ciclo) {
		super(ciclo);
		
		title = new Texture("gameOverScreen/GameOverFont.png");
		titleX = new Texture("gameOverScreen/gameOver.png");
		megaman = new Texture("tittle/megaman1.png");
		spriteTitle = new Sprite(title);
		spriteTitle.setPosition(520, 300);
		spriteTitle.setScale(6.0f,6.0f);
		spriteTitleX = new Sprite(titleX);
		spriteTitleX.setPosition(690, 50);
		spriteTitleX.setScale(2.0f,2.0f);
		spriteMegaman = new Sprite(megaman);
		spriteMegaman.setPosition(50, 150);
		spriteMegaman.setScale(2.0f,2.0f);
		spriteScore = new Sprite(new Texture("gameOverScreen/menu.png"));
		spriteScore.setPosition(440, 50);
		spriteScore.setScale(2.0f,2.0f);
		spriteStart = new Sprite(new Texture("gameOverScreen/retry.png"));
		spriteStart.setPosition(420, 150);
		spriteStart.setScale(2.0f,2.0f);
		
		windowCode = 2;
		
	}
	
	public void update() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
		{
			if(spriteMegaman.getY()==150)
			{
				cicloPrincipal.setWindow(cicloPrincipal.getLevel());
			}
			
			if(spriteMegaman.getY()==50)
			{
				cicloPrincipal.setWindow(0);
			}
			
		}
		if((Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) && spriteMegaman.getY()==150)
		{
			spriteMegaman.setY(spriteMegaman.getY()- 100);
			spriteScore.setTexture(new Texture("gameOverScreen/menuGold.png"));
			spriteStart.setTexture(new Texture("gameOverScreen/blueRetry.png"));

		}
		if((Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) && spriteMegaman.getY()==50)
		{	
			spriteMegaman.setY(spriteMegaman.getY()+ 100);
			spriteScore.setTexture(new Texture("gameOverScreen/menu.png"));
			spriteStart.setTexture(new Texture("gameOverScreen/retry.png"));
		}
	}
	
	public void draw(SpriteBatch batch, ShapeRenderer s1) {
		
		batch.begin();
		spriteTitle.draw(batch);
		spriteTitleX.draw(batch);
		spriteMegaman.draw(batch);
		spriteScore.draw(batch);
		spriteStart.draw(batch);
		batch.end();
		camara.paint(batch);
		
	}
}
