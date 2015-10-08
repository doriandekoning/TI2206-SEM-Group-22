package spaceinvaders.group_22;

import java.util.ArrayList;

import spaceinvaders.group_22.unit.Alien;

/**
 * AlienWave class to store the information about an alien wave.
 * @author Dorian and Bryan
 *
 */
@SuppressWarnings("checkstyle:magicnumber") 
public class ConcreteAlienWave implements AlienWave {
	
	/**
	 * List of aliens from this wave.
	 */
	private ArrayList<ArrayList<Alien>> aliens = new ArrayList<ArrayList<Alien>>();
	
	/**
	 * Speed of the aliens in the X direction in pixels per second.
	 */
	private double alienVelX = 40;
	
	/**
	 * Speed of the aliens in the Y direction in pixels per second.
	 */
	private double alienVelY = 40;
	/**
	 * Amount of pixels the aliens go down per wave.
	 */
	private double alienFall = 10;
	
	/**
	 * Constructor of the alien wave.
	 */
	public ConcreteAlienWave() {
		
	}

	@Override
	public final AlienWave copy() {
		AlienWave wave = new ConcreteAlienWave();
		wave.setAliens(aliens);
		return wave;
	}
	
	@Override
	public final ArrayList<Alien> getAliens() {
		ArrayList<Alien> alienList = new ArrayList<Alien>();
		for(ArrayList<Alien> list : aliens) {
			alienList.addAll(list);
		}
		return alienList;
	}
	/**
	 * Sets the aliens in this wave.
	 * @param setaliens to set in this wave.
	 */
	public final void setAliens(final ArrayList<ArrayList<Alien>> setaliens) {
		this.aliens = setaliens;
	}
	@Override
	public final double getAlienVelX() {
		return alienVelX;
	}
	@Override
	public final void setAlienVelX(final double setalienVelX) {
		this.alienVelX = setalienVelX;
	}
	@Override
	public final double getAlienVelY() {
		return alienVelY;
	}
	/**
	 * Sets the alien velocity in the y direction.
	 * @param setalienVelY the velocity in the y direction.
	 */
	public final void setAlienVelY(final double setalienVelY) {
		this.alienVelY = setalienVelY;
	}
	@Override
	public final double getAlienFall() {
		return alienFall;
	}
	/**
	 * Sets the amount of pixels the aliens go down per wave.
	 * @param setalienFall the amount of pixels aliens go down per wave.
	 */
	public final void setAlienFall(final double setalienFall) {
		this.alienFall = setalienFall;
	}
	/**
	 * Sets a row of the alienWave to the row given in the parameter
	 * @param rowIndex the index of the row to set
	 * @param row the new row of aliens
	 */
	public final void setAlienRow(int rowIndex, ArrayList<Alien> row) {
		aliens.get(rowIndex).clear();
		for(Alien alien : row) {
			aliens.get(rowIndex).add(alien);
		}
	}

}