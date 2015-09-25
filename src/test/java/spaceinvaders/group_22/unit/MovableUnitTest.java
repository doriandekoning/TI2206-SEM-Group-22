package spaceinvaders.group_22.unit;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MovableUnitTest {
	
	public static Class movableUnitClass;
	
	public static MovableUnit movableUnit, movableUnit2;
	
	@Parameters
	public static Collection parameters() {
		Class[] data = new Class[]{SpaceShip.class, ShipBullet.class, AlienBullet.class, Alien.class};
		return Arrays.asList(data);
	}
	
	public MovableUnitTest(Class cl) {
		movableUnitClass = cl;
	}
	
	@Before
	public void setUp() throws  IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		Constructor constructor = movableUnitClass.getConstructor(new Class[]{double.class, double.class, String.class});
		movableUnit = (MovableUnit)constructor.newInstance(new Object[] {1.2, 3.0, "testimage.png"});
		movableUnit2 = (MovableUnit)constructor.newInstance(new Object[] {1.2, 3.0, "testimage.png"});
	}

	/**
	 * Test the Velocity in the X direction.
	 */
	@Test
	public final void testUnintVelX() {
		assertEquals(0, movableUnit.getVelX(), 0.05);
	}
	
	/**
	 * Test the velocity in the Y direction.
	 */
	@Test
	public final void testUnintVelY() {
		assertEquals(0, movableUnit.getVelY(), 0.05);
	}
	/**
	 * Test the move method with velocity 0 in X and Y.
	 */
	@Test
	@SuppressWarnings("checkstyle:magicnumber")    
	public final void testMoveUnitVelocity0() {
		movableUnit.move(60.0);
		Unit unit = (Unit)movableUnit;
		assertEquals(1.2, unit.getXCoor(), 0.05);
		assertEquals(3, unit.getYCoor(), 0.05);
	}
	
	/**
	 * Test the move method with velocity 1 in the X direction.
	 */
	@Test
	@SuppressWarnings("checkstyle:magicnumber")    
	public void testMoveUnit() {
		movableUnit.setVelX(1);
		movableUnit.move(1.0);
		Unit unit = (Unit)movableUnit;
		assertEquals(2.2, unit.getXCoor(), 0.05);
		assertEquals(3.0, unit.getYCoor(), 0.05);
	}
	/**
	 * Test the equals method with a different velX.
	 */
	@Test
	@SuppressWarnings("checkstyle:magicnumber")   
	public final void testEqualsVelX() {
		movableUnit2.setVelX(4);
		assertNotEquals(movableUnit, movableUnit2);
	}
	/**
	 * Test the equals method with a different velY.
	 */
	@Test
	@SuppressWarnings("checkstyle:magicnumber")   
	public final void testEqualsVelY() {
		movableUnit2.setVelY(4);
		assertNotEquals(movableUnit, movableUnit2);
	}
	
}