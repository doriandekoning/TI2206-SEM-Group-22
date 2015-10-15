package spaceinvaders.group_22.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import spaceinvaders.group_22.Game;
import spaceinvaders.group_22.SinglePlayerGame;
import spaceinvaders.group_22.logger.LogEvent;
import spaceinvaders.group_22.logger.Logger;

/**
 * The drawing of the Score.
 * @author Ege
 *
 */
@SuppressWarnings("checkstyle:magicnumber")  
public abstract class Score extends UIElement {
	
	/**
	 * The label to draw the score on.
	 */
	private Label label;
	
	Game game;
	
	/**
	 * The constructor.
	 * @param newGame the Game
	 * @param gc the GraphicsContext to draw on.	
	 * @param scoreLabel The label on which the score should be drawn. 
	 */
	public Score(final Game newGame, final GraphicsContext gc, final Label scoreLabel) {
		super(newGame, gc);
		label = scoreLabel;
		game = newGame;
	}

	@Override
	public abstract void draw();
}