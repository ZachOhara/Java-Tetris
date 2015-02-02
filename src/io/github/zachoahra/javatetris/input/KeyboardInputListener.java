package io.github.zachoahra.javatetris.input;

import io.github.zachoahra.javatetris.window.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

public class KeyboardInputListener implements KeyListener {
	
	private GameWindow masterWindow;
	
	private static final Integer[] defaultListenFor = {37, 38, 39, 40, 65, 87, 68, 83};
	private static final boolean listenToAll = false;
	private static final boolean displayOutputs = true;
	private List<Integer> listenFor;
	
	public KeyboardInputListener(GameWindow master) {
		this.listenFor = Arrays.asList(defaultListenFor);
		this.masterWindow = master;
	}
	
	public KeyboardInputListener() {
		this(null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (listenToAll || this.listenFor.contains(e.getKeyCode())) {
			if (this.masterWindow != null)
				this.masterWindow.doInput(e.getKeyCode());
			if (displayOutputs)
				System.out.println(e.getKeyCode());
		}
	}
	
	public KeyboardInputListener setMaster(GameWindow master) {
		this.masterWindow = master;
		return this;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ignore input
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ignore input
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.addKeyListener(new KeyboardInputListener());
		f.setVisible(true);
	}

}
