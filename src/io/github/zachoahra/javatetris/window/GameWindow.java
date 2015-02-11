package io.github.zachoahra.javatetris.window;

import io.github.zachoahra.javatetris.input.KeyboardInputManager;
import io.github.zachoahra.javatetris.management.GameManager;
import io.github.zachoahra.javatetris.management.LevelManager;
import io.github.zachoahra.javatetris.management.ScoreManager;
import io.github.zachoahra.javatetris.management.TimeManager;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	private GameManager game;
	private LevelManager level;
	private ScoreManager score;
	private TimeManager time;
	private NextBlockPanel nextShapePanel;
	
	private static final String windowTitle = "Tetris by Zach Ohara";
	
	private static final long serialVersionUID = 1L;
	
	public GameWindow() {
		super(windowTitle);
		
		this.setSize(getWindowSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		this.level = new LevelManager();
		this.score = new ScoreManager(this);
		this.nextShapePanel = new NextBlockPanel(this);
		this.game = new GameManager(this, this.level, this.score, this.nextShapePanel);
		this.time = new TimeManager(this.level, this.game);
		this.game.setController(new KeyboardInputManager(this, this.game, this.time));
	}
	
	public GameManager getGame() {
		return this.game;
	}
	
	public void startGame() {
		this.time.start();
		this.game.startGame();
	}
	
	public void endGame() {
		this.time.interrupt();
	}
	
	private static Dimension getWindowSize() {
		return new Dimension(1000,900);
		//TODO: calculate size needs based on the size needs of the constituent window elements
	}
	
	public void doInput(int code) {
		// do nothing, for now
	}

	public static void main(String[] args) {
		GameWindow g = new GameWindow();
		g.setVisible(true);
		g.startGame();
	}

}
