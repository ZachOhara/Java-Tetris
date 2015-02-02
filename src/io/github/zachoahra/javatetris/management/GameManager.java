package io.github.zachoahra.javatetris.management;

import java.util.Arrays;

import io.github.zachoahra.javatetris.game.BlockGrid;
import io.github.zachoahra.javatetris.input.KeyboardInputListener;
import io.github.zachoahra.javatetris.window.GameWindow;

public class GameManager {
	
	private GameWindow masterWindow;
	private BlockGrid gameGrid;
	private KeyboardInputListener keyListener;
	
	private static final int gridWidth = 10;
	private static final int gridHeight = 10;
	private static final int[] inputLeft = {65, 37};
	private static final int[] inputRight = {68, 39};
	private static final int[] inputUp = {87, 38};
	private static final int[] inputDown = {68, 40};
	
	public GameManager(GameWindow master) {
		this.masterWindow = master;
		this.gameGrid = new BlockGrid(gridWidth, gridHeight);
		this.keyListener = new KeyboardInputListener();
		this.keyListener.setMaster(this.masterWindow);
	}
	
	public void doInput(int code) {
		// left
		if (Arrays.asList(inputLeft).contains(code))
			if (this.gameGrid.canShapeShift(-1))
				this.gameGrid.shiftShape(-1);
		// right
		if (Arrays.asList(inputRight).contains(code))
			if (this.gameGrid.canShapeShift(1))
				this.gameGrid.shiftShape(1);
		//TODO: down for soft drop
		//TODO: up for firm drop
	}

}
