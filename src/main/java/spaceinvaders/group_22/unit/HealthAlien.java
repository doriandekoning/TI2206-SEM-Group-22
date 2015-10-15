package spaceinvaders.group_22.unit;

import spaceinvaders.group_22.ui.SpriteLoader;

/**
 * Class for an special type of alien with health 5.
 * @author Bryan
 *
 */
public class HealthAlien extends Alien {
	/**
	 * Creates a alien with health 5.
	 * @param x location to create the alien on.
	 * @param y location to create the alien on.
	 */
	public HealthAlien(final double x, final double y) {
		super(x, y);
		setHealth(5);
	}
	
	@Override
	public final void setSpriteImage() {
		setSprite(SpriteLoader.getInstance().getAlienWithHealth(getHealth()));	
	}

}