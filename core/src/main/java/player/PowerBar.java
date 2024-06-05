package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PowerBar extends LifeBar {

	public PowerBar() {
		super();
		lifeBar = new Sprite(new Texture("Player/PowerHud.png"));
		lifeBar.setScale(2.0f,2.0f);
		lifePoint = new Sprite(new Texture("Player/PowervitPoint.png"));
		lifePoint.setScale(2.0f,2.0f);
	}

}
