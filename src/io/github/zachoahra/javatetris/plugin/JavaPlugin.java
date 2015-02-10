package io.github.zachoahra.javatetris.plugin;

import io.github.zachoahra.javatetris.game.BlockGrid;
import io.github.zachoahra.javatetris.game.Shape;
import io.github.zachoahra.javatetris.management.GameManager;
import io.github.zachoahra.javatetris.window.GameWindow;

public abstract class JavaPlugin {
	
	private GameWindow window = null;
	
	public void setWindow(GameWindow window) {
		this.window = window;
	}
	
	public GameWindow getWindow() {
		return this.window;
	}
	
	public GameManager getGame() {
		return this.window.getGame();
	}
	
	public BlockGrid getBlockGrid() {
		return this.window.getGame().getBlockGrid();
	}
	
	public Shape getCurrentShape() {
		return this.window.getGame().getBlockGrid().getCurrentShape();
	}
	
	public Shape getNextShape() {
		return this.window.getGame().getNextShape();
	}

}
