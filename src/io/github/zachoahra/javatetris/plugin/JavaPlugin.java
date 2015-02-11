package io.github.zachoahra.javatetris.plugin;

import io.github.zachoahra.javatetris.game.Block;
import io.github.zachoahra.javatetris.game.BlockGrid;
import io.github.zachoahra.javatetris.game.Shape;
import io.github.zachoahra.javatetris.input.GameController;
import io.github.zachoahra.javatetris.management.GameManager;

public abstract class JavaPlugin implements GameController {
	
	private GameManager game;
	private boolean hasControl;
	
	public JavaPlugin() {
		this.game = null;
		this.hasControl = false;
	}
	
	@Override
	public boolean hasControl() {
		return this.hasControl;
	}
	
	@Override
	public void setControl(boolean b) {
		this.hasControl = b;
	}
	
	@Override
	public void setGame(GameManager game) {
		this.game = null;
	}
	
	public GameManager getGame() {
		return this.game;
	}
	
	public boolean canControlGame() {
		return this.game.isGameRunning() && this.hasControl();
	}
	
	public BlockGrid getBlockGrid() {
		return this.getGame().getBlockGrid();
	}
	
	public Block[][] getBlockGridArray() {
		return this.getBlockGrid().getBlockgridArray();
	}
	
	public Shape getCurrentShape() {
		return this.getGame().getBlockGrid().getCurrentShape();
	}
	
	public Block[][] getCurrentShapeArray() {
		return this.getCurrentShape().getBlockGrid();
	}
	
	public Shape getNextShape() {
		return this.getGame().getNextShapePanel().read();
	}
	
	public Block[][] getNextShapeArray() {
		return this.getNextShape().getBlockGrid();
	}
	
	public void shiftShape(int dir) {
		this.getBlockGrid().shiftShape(dir);
	}
	
	public void rotateShape(int dir) {
		this.getBlockGrid().rotateShape(dir);
	}
	
	public void hardDropShape() {
		this.getBlockGrid().hardDrop();
	}

}
