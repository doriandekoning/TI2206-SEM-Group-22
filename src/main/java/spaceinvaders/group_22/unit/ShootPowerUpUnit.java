package spaceinvaders.group_22.unit;

import spaceinvaders.group_22.Player;
import spaceinvaders.group_22.ShootPowerUp;

/**
 * Shoot power up class extends the power Up class.
 * @author Bryan
 *
 */
public class ShootPowerUpUnit extends PowerUpUnit {
	/**
	 * Indicates the velocity in the Y direction of this powerup type.
	 */
	static final double MAXVELY = 50.0;
	/**
	 * Creates a shoot power Up.
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param spriteFile The filename of the sprite.
	 */
	public ShootPowerUpUnit(final double x, final double y, final String spriteFile) {
		super(x, y, spriteFile);
	}

	@Override
	public final void activate(final Player newplayer) {
		new ShootPowerUp(newplayer);
	}
	
	/**
	 * Returns the velocity in the Y direction.
	 * @return The velocity in the Y direction.
	 */
	public static double getMaxVely() {
		return MAXVELY;
	}

}
