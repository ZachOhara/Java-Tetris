package io.github.zachoahra.javatetris.window;

import io.github.zachoahra.javatetris.game.Block;
import io.github.zachoahra.javatetris.game.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class NextBlockPanel extends JPanel {
	
	private Shape nextShape = null;
	
	private static final int xPos = 650;
	private static final int yPos = 650;

	private static final long serialVersionUID = 1L;
	
	public NextBlockPanel(JFrame window) {
		super();
		this.setLayout(null);
		this.setLocation(xPos, yPos);
		this.setSize(getLength(), getLength());
		window.add(this);
	}
	
	public Shape retrieve() {
		this.removeAll();
		this.update();
		this.nextShape.translate(3, 0);
		Shape temp = this.nextShape;
		this.nextShape = null;
		return temp;
	}
	
	public Shape read() {
		return this.nextShape;
	}
	
	public void store(Shape nextShape) {
		this.nextShape = nextShape;
		this.nextShape.translate(-3, 0);
		this.nextShape.setPanel(this);
		this.update();
	}
	
	public boolean shapeIsNull() {
		return this.nextShape == null;
	}
	
	private void update() {
		this.revalidate();
		this.repaint();
	}
	
	public static int getLength() {
		return Block.getLength() * 4;
	}
	
}
