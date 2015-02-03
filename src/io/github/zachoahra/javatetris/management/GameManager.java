package io.github.zachoahra.javatetris.management;

import java.util.Arrays;

import io.github.zachoahra.javatetris.game.BlockGrid;
import io.github.zachoahra.javatetris.game.ShapeFactory;
import io.github.zachoahra.javatetris.input.KeyboardInputListener;
import io.github.zachoahra.javatetris.window.GameWindow;

public class GameManager {
	
	private GameWindow masterWindow;
	private BlockGrid gameGrid;
	private KeyboardInputListener keyListener;
	
	private static final int gridWidth = 10;
	private static final int gridHeight = 17;
	private static final int[] inputLeft = {65, 37};
	private static final int[] inputRight = {68, 39};
	private static final int[] inputUp = {87, 38};
	private static final int[] inputDown = {68, 40};
	
	public GameManager(GameWindow master) {
		this.masterWindow = master;
		this.gameGrid = new BlockGrid(gridWidth, gridHeight);
		this.gameGrid.setLocation(0, 0);
		this.keyListener = new KeyboardInputListener(this.masterWindow, this);
		this.masterWindow.addKeyListener(this.keyListener);
		this.gameGrid.setShape(ShapeFactory.makeRandomShape());
		this.masterWindow.add(this.gameGrid);
	}
	
	public void doInput(int code) {
		//TODO: make this less ugly, try to use list.contains()
		//System.out.println("jdjd");
		// left
		//if (Arrays.asList(inputLeft).contains(code))
		if (code == inputLeft[0] || code == inputLeft[1])
			if (this.gameGrid.canShapeShift(-1)) {
				this.gameGrid.shiftShape(-1);
				System.out.println("left");
			}
		// right
		if (code == inputRight[0] || code == inputRight[1])
			if (this.gameGrid.canShapeShift(1)) {
				this.gameGrid.shiftShape(1);
				System.out.println("right");
			}
		//TODO: down for soft drop
		//TODO: up for firm drop
	}
	
	public static void main(String[] args) {
		GameWindow g = new GameWindow();
		g.setVisible(true);
	}

}
