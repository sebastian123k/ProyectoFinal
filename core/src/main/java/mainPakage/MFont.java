package mainPakage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MFont {
    
    protected float posX, posY;
    protected Texture textura;
    protected Sprite[] sprite;
    
    public MFont(int x, int y) 
    {
        posX = x;
        posY = y;
        sprite = new Sprite[80]; 
        textura = new Texture("font/blue.png");
        this.createSprites(13, 13);
    }
    
    public void createSprites(int spriteHeight, int spriteWeight)
    {
        TextureRegion Frames;
    
        for(int y = 0, i = 0; y <65 ; y += spriteWeight)
        {
            for(int x = 0; x < 208; x += spriteHeight) 
            {
                if(i < 80)
                {
                    Frames = new TextureRegion(textura, x, y, spriteHeight, spriteWeight);    
                    sprite[i] = new Sprite(Frames);
                    sprite[i].setScale(2.0f, 2.0f);
                    sprite[i].setPosition(posX, posY);
                    i++;
                }
            }
        }
    }   
    
    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY - 10;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public void draw(SpriteBatch batch, String texto) {
        batch.begin();
        for(int x = 0; x < texto.length(); x++)
        {
            sprite[getLetra(texto.charAt(x))].setPosition(posX + x * 16, posY - 8);
            sprite[getLetra(texto.charAt(x))].draw(batch);
        }
        batch.end();
    }
    
    public int getLetra(char letra)
    {
        // Añadimos todos los caracteres con sus respectivos índices
        switch(letra)
        {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case ':': return 10;
            case ';': return 11;
            case '<': return 12;
            case '=': return 13;
            case '>': return 14;
            case '?': return 15;
            case '@': return 16;
            case 'A': return 17;
            case 'B': return 18;
            case 'C': return 19;
            case 'D': return 20;
            case 'E': return 21;
            case 'F': return 22;
            case 'G': return 23;
            case 'H': return 24;
            case 'I': return 25;
            case 'J': return 26;
            case 'K': return 27;
            case 'L': return 28;
            case 'M': return 29;
            case 'N': return 30;
            case 'O': return 31;
            case 'P': return 32;
            case 'Q': return 33;
            case 'R': return 34;
            case 'S': return 35;
            case 'T': return 36;
            case 'U': return 37;
            case 'V': return 38;
            case 'W': return 39;
            case 'X': return 40;
            case 'Y': return 41;
            case 'Z': return 42;
            case '[': return 43;
            case '*': return 44;
            case ']': return 45;
            case '^': return 46;
            case '_': return 47;
            case '`': return 48;
            case 'a': return 49;
            case 'b': return 50;
            case 'c': return 51;
            case 'd': return 52;
            case 'e': return 53;
            case 'f': return 54;
            case 'g': return 55;
            case 'h': return 56;
            case 'i': return 57;
            case 'j': return 58;
            case 'k': return 59;
            case 'l': return 60;
            case 'm': return 61;
            case 'n': return 62;
            case 'o': return 63;
            case 'p': return 64;
            case 'q': return 65;
            case 'r': return 66;
            case 's': return 67;
            case 't': return 68;
            case 'u': return 69;
            case 'v': return 70;
            case 'x': return 71;
            case 'y': return 72;
            case 'z': return 73;
            case ' ': return 78;
         
            
           
        }
        return 0; 
    }
}
