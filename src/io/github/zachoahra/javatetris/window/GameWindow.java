package io.github.zachoahra.javatetris.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	private static final String windowTitle = "Tetris by Zach Ohara";
	
	private static final long serialVersionUID = 1L;
	
	public GameWindow(Dimension size) {
		super(windowTitle);
		this.setSize(size);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
	}
	
	public GameWindow() {
		this(Toolkit.getDefaultToolkit().getScreenSize());
	}

	public static void main(String[] args) {
		//GameWindow g = new GameWindow();
	}

}
