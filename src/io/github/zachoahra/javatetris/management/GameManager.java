package io.github.zachoahra.javatetris.management;

import javax.swing.JPanel;

import io.github.zachoahra.javatetris.game.Block;
import io.github.zachoahra.javatetris.game.BlockGrid;
import io.github.zachoahra.javatetris.game.Shape;
import io.github.zachoahra.javatetris.game.ShapeFactory;
import io.github.zachoahra.javatetris.input.KeyboardInputListener;
import io.github.zachoahra.javatetris.window.GameWindow;

public class GameManager {
	
	private GameWindow masterWindow;
	private BlockGrid gameGrid;
	private Shape nextShape;
	private KeyboardInputListener keyListener;
	private LevelManager levelManager;
	private ScoreManager scoreManager;
	private TimeManager timingManager;
	private JPanel nextShapePanel;
	private boolean isFastMode;
	
	private static final int gridWidth = 10;
	private static final int gridHeight = 17;
	private static final int[] inputRotate = {32};
	private static final int[] inputLeft = {65, 37};
	private static final int[] inputRight = {68, 39};
	private static final int[] inputUp = {87, 38};
	private static final int[] inputDown = {83, 40};
	
	public GameManager(GameWindow master) {
		this.masterWindow = master;
		this.gameGrid = new BlockGrid(gridWidth, gridHeight);
		this.gameGrid.setLocation(0, 0);
		this.keyListener = new KeyboardInputListener(this.masterWindow, this);
		this.masterWindow.addKeyListener(this.keyListener);
		this.masterWindow.add(this.gameGrid);
		this.levelManager = new LevelManager();
		this.scoreManager = new ScoreManager(this.masterWindow);
		this.timingManager = new TimeManager(this.levelManager, this);
		this.isFastMode = false;
		this.nextShapePanel = new JPanel();
		this.nextShapePanel.setLayout(null);
		this.nextShapePanel.setLocation(650, 650);
		this.nextShapePanel.setSize(Block.getLength() * 4, Block.getLength() * 4);
		this.masterWindow.add(nextShapePanel);
		this.spawnNewShape();
		this.timingManager.start();
	}
	
	public BlockGrid getBlockGrid() {
		return this.gameGrid;
	}
	
	public Shape getNextShape() {
		return this.nextShape;
	}
	
	public void doGravity() {
		if (!(this.gameGrid.descendShape())) {
			this.gameGrid.anchorShape();
			this.checkLinesCleared();
			this.spawnNewShape();
			if (!this.gameGrid.isShapeViable()) {
				System.out.println("GAME OVER");
			}
		}
	}
	
	private void checkLinesCleared() {
		int[] lines = this.gameGrid.getFullLines();
		this.gameGrid.removeAll(lines);
		this.scoreManager.addLines(lines.length);
		this.scoreManager.addScore(this.levelManager.getLinePoints(lines.length));
		this.levelManager.clearLines(lines.length);
		this.scoreManager.setLevel(this.levelManager.getLevel());
	}
	
	private void spawnNewShape() {
		this.nextShapePanel.removeAll();
		if (this.nextShape != null) {
			this.nextShape.translate(3, 0);
			this.gameGrid.setShape(this.nextShape);
		} else
			this.gameGrid.setShape(ShapeFactory.makeRandomShape());
		this.nextShape = ShapeFactory.makeRandomShape();
		this.nextShape.translate(-3, 0);
		this.nextShape.setPanel(this.nextShapePanel);
		this.nextShapePanel.revalidate();
		this.nextShapePanel.repaint();
		
	}
	
	public void doInput(int code) {
		// left
		if (contains(inputLeft, code))
			this.gameGrid.shiftShape(-1); 
		// right
		if (contains(inputRight, code))
			this.gameGrid.shiftShape(1);
		// down
		if (contains(inputDown, code)) {
			this.isFastMode = true;
			this.timingManager.interrupt();
		}
		// up
		if (contains(inputUp, code))
			this.gameGrid.hardDrop();
		// rotate
		if (contains(inputRotate, code))
			this.gameGrid.rotateShape(1);
	}
	
	public void doReleaseInput(int code) {
		System.out.println("fastoff");
		if (contains(inputDown, code))
			this.isFastMode = false;
	}
	
	// TODO: move fastMode functionality to TimeManger
	public boolean isFastMode() {
		return this.isFastMode;
	}
	
	private static boolean contains(int[] arr, int elem) {
		for (int i : arr)
			if (i == elem)
				return true;
		return false;
	}
	
	public static void main(String[] args) {
		GameWindow g = new GameWindow();
		g.setVisible(true);
	}

}
