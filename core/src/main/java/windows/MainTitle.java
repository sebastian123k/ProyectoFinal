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
	Texture megaman;
	
	Sprite spriteTitle;
	Sprite spriteTitleX;
	Sprite spriteMegaman;
	Sprite spriteScore;
	Sprite spriteStart;
	
	public MainTitle(MainLoop ciclo) {
		super(ciclo);
		
		title = new Texture("tittle/title1.png");
		titleX = new Texture("tittle/titleX.png");
		megaman = new Texture("tittle/megaman1.png");
		spriteTitle = new Sprite(title);
		spriteTitle.setPosition(200, 300);
		spriteTitle.setScale(3.0f,3.0f);
		spriteTitleX = new Sprite(titleX);
		spriteTitleX.setPosition(520, 280);
		spriteTitleX.setScale(3.0f,3.0f);
		spriteMegaman = new Sprite(megaman);
		spriteMegaman.setPosition(50, 150);
		spriteMegaman.setScale(2.0f,2.0f);
		spriteScore = new Sprite(new Texture("tittle/blueScore.png"));
		spriteScore.setPosition(340, 50);
		spriteScore.setScale(2.0f,2.0f);
		spriteStart = new Sprite(new Texture("tittle/gameStartFont.png"));
		spriteStart.setPosition(350, 150);
		spriteStart.setScale(2.0f,2.0f);
		
	}
	
	public void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
		{
			cicloPrincipal.setWindow(1);
		}
		if((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) && spriteMegaman.getY()==150)
		{
			spriteMegaman.setY(spriteMegaman.getY()- 100);
			spriteScore.setTexture(new Texture("tittle/Score.png"));
			spriteStart.setTexture(new Texture("tittle/blueGameStart.png"));

		}
		if((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && spriteMegaman.getY()==50)
		{	
			spriteMegaman.setY(spriteMegaman.getY()+ 100);
			spriteScore.setTexture(new Texture("tittle/blueScore.png"));
			spriteStart.setTexture(new Texture("tittle/gameStartFont.png"));
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
		
	}

}
