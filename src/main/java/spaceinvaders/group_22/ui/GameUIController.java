package spaceinvaders.group_22.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import spaceinvaders.group_22.Game;
import spaceinvaders.group_22.logger.LogEvent;
import spaceinvaders.group_22.unit.Alien;
import spaceinvaders.group_22.unit.Barricade;
import spaceinvaders.group_22.unit.Bullet;
import spaceinvaders.group_22.unit.Explosion;
import spaceinvaders.group_22.unit.SpaceShip;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Controller for the GameUI.fxml file,
 * controlling the behavior of a game.
 * @author Jochem
 *
 */
@SuppressWarnings("checkstyle:magicnumber")    
public class GameUIController
    implements Initializable {

	/**
	 * Canvas corresponding to fxml id.
	 */
    @FXML private Canvas canvas;
    
    /**
     * Stackpane corresponding to fmxl id.
     */
    @FXML private StackPane stackPane;

    /**
     * The framerate of the animation.
     */
    private Double framerate;
    
    /**
     * The width of the canvas.
     */
    private double canvasWidth;
    
    /**
     * The height of the canvas.
     */
    private double canvasHeight;
    
    /**
     * ArrayList of all the keys currently pressed.
     */
    private ArrayList<KeyCode> pressedKeys = new ArrayList<KeyCode>();
    
    /**
     * Game object of the current game.
     */
    private Game game;
    
    /**
     * Hashmap containing all sprites.
     */
    private HashMap<String, Image> sprites;
    
    /**
     * Label to load the score of the player in.
     */
    @FXML
	private Label scoreLabel;
    
    /**
     * Label to load the highscore in.
     */
    @FXML
	private Label highScoreLabel;
    
    /**
     * The game over screen.
     */
    private Node screenGameOver;
    
    /**
     * The game over screen.
     */
    private Node screenBeforePlay;
    
    
    /**
     * The paused screen.
     */
    private Node screenPaused;
    
    /**
     * The graphicscontext of the Canvas.
     */
    private static GraphicsContext gc;
    /**
     * The drawing of the SpaceShip.
     */
    private UIElementSpaceShip uiSpaceShip;
    /**
     * The drawing of the Alien.
     */
    private UIElementAlien uiAlien;
    /**
     * The drawing of the Bullet.
     */
    private UIElementBullet uiBullet;
    /**
     * The drawing of the Explosion.
     */
    private UIElementExplosion uiExplosion;
    /**
     * The drawing of the Barricade.
     */
    private UIElementBarricade uiBarricade;
    /**
     * The drawing of the score.
     */
    private Score uiScore;
    /**
     * The drawing of the lives.
     */
    private Lives uiLives;
    
    /**
     * Called by the FXMLLoader. 
     */
    @Override
	public final void initialize(final URL fxmlFileLocation, final ResourceBundle resources) {
    	initializeStackPaneScreens();
    	// Get the GraphicsContext of the canvas, so you can draw on it.
    	gc = canvas.getGraphicsContext2D();
    	
    	canvasWidth = canvas.getWidth();
    	canvasHeight = canvas.getHeight();
    	
    	newGame();
    	game.getLogger().log("Set canvas width to: " + canvasWidth, LogEvent.Type.INFO);
    	game.getLogger().log("Set canvas height to: " + canvasHeight, LogEvent.Type.INFO);
    	game.getLogger().log("Show screen Before Play", LogEvent.Type.INFO);
    	
    	uiAlien = new UIElementAlien(game, this);
    	uiSpaceShip = new UIElementSpaceShip(game, this);
    	uiBullet = new UIElementBullet(game, this);
    	uiExplosion = new UIElementExplosion(game, this);
    	uiBarricade = new UIElementBarricade(game, this);
    	uiScore = new Score(game, this);
    	uiLives = new Lives(game, this);
    	
    	canvas.setFocusTraversable(true);
    }
    
    /**
     * Retruns the canvas Width.
     * @return canvasWidth
     */
    public final double getCanvasWidth() {
    	return canvasWidth;
    }
    
    /**
     * Retruns the canvas Height.
     * @return canvasHeight
     */
    public final double getCanvasHeight() {
    	return canvasHeight;
    }
    
    /**
     * Gets the screens in the GameUI stackpane, and assigns them to the right variables.
     * The order in the FXML file matters (!).
     */
    public final void initializeStackPaneScreens() {
    	screenGameOver = stackPane.getChildren().get(0);
    	screenBeforePlay = stackPane.getChildren().get(1);
    	screenPaused = stackPane.getChildren().get(2);

    	// Move press to play to front.
    	screenBeforePlay.toFront();
    }
    
    /**
     * Creates a new game.
     */
    public final void newGame() {
    	// If the game does not exist, create a new one.
    	if (game == null) {
        	game = new Game(canvasWidth, canvasHeight);
        	sprites = getSprites();
        // Else reset the existing game.
    	} else {
        	game.resetGame();    		
    	}

    	startAnimation();
    }
    
    /**
     * Method to set the framerate of the animation.
     * @param fps The amount of frames per second.
     */
    public final void setFramerate(final int fps) {
    	if (fps > 0) {
        	framerate = 1.0 / (double) fps;
    	} else {
    		framerate = 0.0;
    	}
    	game.getLogger().log("Set framerate to: " + framerate, LogEvent.Type.INFO);
    }
    
    /**
     * Returns the framerate.
     * @return the framerate of the animation.
     */
    public final double getFramerate() {
    	return framerate;
    }
    
    /**
     * Returns the GraphicsContext.
     * @return the GraphicsContext
     */
    public static final GraphicsContext getGC() {
    	return gc;
    }
    
    /**
     * Creates a hashmap of all available sprite images.
     * @return a hashmap containing all available sprite images.
     */
    public final HashMap<String, Image> getSprites() {
    	HashMap<String, Image> spriteMap = new HashMap<String, Image>();
    		
    		addSprite(spriteMap, "alienbullet.png");
    		addSprite(spriteMap, "spaceshipbullet.png");
    		addSprite(spriteMap, "invader.png");
    		addSprite(spriteMap, "spaceship.png");
    		addSprite(spriteMap, "heart.png");
    		addSprite(spriteMap, "barrier.png");
    		addSprite(spriteMap, "explosion1.png");
    		addSprite(spriteMap, "explosion2.png");
    		addSprite(spriteMap, "explosion3.png");
    		addSprite(spriteMap, "explosion4.png");
    		addSprite(spriteMap, "explosion5.png");
    		
    	return spriteMap;
    }
    
    /**
     * Returns the sprites.
     * @return the sprites
     */
    public final HashMap<String, Image> getSprite() {
    	return sprites;
    }
    
    /**
     * Adds a sprite to the sprite Hasmap.
     * @param spriteMap The hashmap of sprites to add to.
     * @param filename The filename of the sprite to add.
     */
    public final void addSprite(final HashMap<String, Image> spriteMap, final String filename) {
		spriteMap.put(filename, 
				new Image(getClass().getClassLoader()
						.getResource("spaceinvaders/group_22/images/" + filename).toString()));
		game.getLogger().log("Loaded " + filename, LogEvent.Type.DEBUG);
    }
    
    /**
     * Starts the animation of the canvas.
     */
    public final void startAnimation() {
		
		// Create the animation Timeline, responsible for updating the animation.
		Timeline gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		
    	// Set the animation framerate.
    	setFramerate(60);
    	game.setTickrate(framerate);
    	
    	// Create each frame.
		KeyFrame frame = new KeyFrame(
			Duration.seconds(framerate), 
				new EventHandler<ActionEvent>()
				{
					public void handle(final ActionEvent ae) {
						// Clear the canvas.
						gc.clearRect(0, 0, canvasWidth, canvasHeight);
						
						// If the game is in progress, look if any key is pressed.
						if (game.isInProgress()) {
							game.tick(pressedKeys);
						}
						
						// Draw the various units on the screen.
						uiSpaceShip.draw();
						uiAlien.draw();
						uiBullet.draw();
						uiBarricade.draw();
						uiExplosion.draw();
	
						// Draw the lives and score on the screen.
						uiLives.draw();
						uiScore.draw();
						
						if (pressedKeys.contains(KeyCode.SPACE)) {
					    	pressedKeys.remove(KeyCode.SPACE);
					    }
						
						// If the game has ended, put the Game Over screen to the front.
						if (game.hasEnded()) {
							screenGameOver.toFront();
							highScoreLabel.setText("Highscore: " + game.getHighScore());
							gameLoop.stop();
							game.getLogger().log("Show screen Game Over", LogEvent.Type.INFO);
						} else {
							screenGameOver.toBack();
						}
						
					}
				});
		
		 gameLoop.getKeyFrames().add(frame);
		 gameLoop.play();
    }
	
	/**
	 * Returns the scoreLabel.
	 * @return The scoreLabel of the UI.
	 */
	public final Label getScoreLabel() {
		return scoreLabel;
	}
	
	/**
	 * Handles if a key is pressed.
	 * @param event of a key pressed
	 */
	@FXML
	public final void handleKeyPressed(final KeyEvent event) {
		game.getLogger().log("Player pressed " + event.getCode().toString(), LogEvent.Type.DEBUG);
        if (event.getCode().equals(KeyCode.S) && game.getPlayer().getLives() > 0) {
        	screenBeforePlay.toBack();
        	screenPaused.toBack();
        	game.start();
        } else if (event.getCode().equals(KeyCode.P)) {
        	if (game.isInProgress()) {
            	screenPaused.toFront();
            	game.getLogger().log("Show screen Paused", LogEvent.Type.INFO);
            	game.stop();
        	}
        } else if (event.getCode().equals(KeyCode.R)) {
        	if (game.hasEnded()) {
            	newGame();
            	game.start();
        	}
        } else if (!pressedKeys.contains(event.getCode())) {
	    	pressedKeys.add(event.getCode());
	    }
	}
	
	/**
	 * Handles if a key is released.
	 * @param event of a key released.
	 */
	@FXML
	public final void handleKeyReleased(final KeyEvent event) {
	    if (pressedKeys.contains(event.getCode())) {
	    	pressedKeys.remove(event.getCode());
	    }
	}

}