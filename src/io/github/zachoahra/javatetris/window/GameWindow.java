package io.github.zachoahra.javatetris.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	private static final String windowTitle = "Tetris by Zach Ohara";
	
	private static final long serialVersionUID = 1L;
	
	public GameWindow() {
		super(windowTitle);
		this.setSize(getWindowSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
	}

	public static void main(String[] args) {
		GameWindow g = new GameWindow();
		g.setVisible(true);
	}
	
	private static Dimension getWindowSize() {
		return new Dimension(500,900);
		//TODO: calculate size needs based on the size needs of the constituent window elements
	}
	
	public void doInput(int code) {
		//TODO: receive inputs and act accordingly
	}

}
