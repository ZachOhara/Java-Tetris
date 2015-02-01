package io.github.zachoahra.javatetris.window;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private GameWindow masterWindow;
	
	private static final long serialVersionUID = 1L;
	
	public GamePanel(GameWindow master) {
		this.masterWindow = master;
		this.masterWindow.add(this);
	}
	
	public static void main(String[] args) {
		//GameWindow win = new GameWindow();
		//GamePanel yay = new GamePanel(win);
		//Rectangle r = new Rectangle();
		//Graphics g = new JPanel();
	}

}
