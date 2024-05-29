package mainPakage;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import windows.GameOverScreen;
import windows.IntroStage;
import windows.MWindow;
import windows.MainTitle;
import windows.ScoreScreen;
import windows.SecondStage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainLoop extends ApplicationAdapter {
	
    private SpriteBatch batch;
    private ShapeRenderer s1;
    private MWindow ventana;
    private int currentWindow;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        s1 = new ShapeRenderer();
        ventana = new MainTitle(this); 
        currentWindow = 0;
    }

    @Override
    public void render() {
    	clearScreen();
    	ubdate();
        paint();
    }
    
    public void clearScreen()
    {
    	 Gdx.gl.glClearColor(0, 0, 0, 0);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    
    public void ubdate()
    {
    	if(ventana.getWindowCode() != currentWindow)
    	{
    		changeWindow();
    	}
    	ventana.update();
    }
    
    public void paint()
    {
    	ventana.draw(batch,s1);
    }
    
    private void changeWindow()
    {
    	switch(currentWindow)
    	{
    	case 0:
    		ventana = new MainTitle(this);
    		break;
    	case 1:
    		ventana = new IntroStage(this);
    		break;
    	case 2:
    		ventana = new GameOverScreen(this);
    		break;
    	case 3:
    		ventana = new ScoreScreen(this);
    		break;
    	case 4:
    		ventana = new SecondStage(this);
    		break;	
    	
    	}
    }
    
    public void setWindow(int window)
    {
    	currentWindow = window;
    }

    @Override
    public void dispose() {
        batch.dispose();
 
    }
    
}
