package mainPakage;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import windows.IntroStage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainLoop extends ApplicationAdapter {
	
    private SpriteBatch batch;
    private ShapeRenderer s1;
    private IntroStage introStage;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        s1 = new ShapeRenderer();
        introStage = new IntroStage();    
    }

    @Override
    public void render() {
    	clearScreen();
    	ubdate();
        paint();
    }
    
    public void clearScreen()
    {
    	 Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    public void ubdate()
    {
    	introStage.update();
    }
    
    public void paint()
    {
    	
    	introStage.draw(batch,s1);
    }

    @Override
    public void dispose() {
        batch.dispose();
 
    }
    
}
