package io.github.zachoahra.javatetris.management;

public class LevelManager {
	
	private int level;
	
	public LevelManager(int level) {
		this.level = level;
	}
	
	public LevelManager() {
		this(0);
	}
	
	public void levelUp() {
		this.level++;
	}
	
	public int getDropTicks() {
		return 0;
		//TODO: return the milliseconds for the block to fall at the current level
	}

}
