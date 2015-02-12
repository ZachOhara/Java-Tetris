package io.github.zachoahra.javatetris.input;

import io.github.zachoahra.javatetris.management.GameManager;

public interface GameController {
		
	public void setGame(GameManager game);
	public boolean hasControl();
	public void setControl(boolean b);
	
	public void start();

}
