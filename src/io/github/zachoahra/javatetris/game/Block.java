package io.github.zachoahra.javatetris.game;

import java.awt.Point;

import io.github.zachoahra.javatetris.resource.texture.block.BlockTexture;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Block extends JPanel {
	
	private int posX;
	private int posY;
	private boolean isAnchored;
	private BlockTexture texture;

	private static int lengthPixels = 50;
	
	private static final long serialVersionUID = 1L;
	
	public Block(BlockTexture texture) {
		super();
		this.posX = 0;
		this.posY = 0;
		this.isAnchored = false;
		this.texture = texture;
		this.setSize(lengthPixels, lengthPixels);
		JLabel textureLabel = this.texture.getLabel();
		textureLabel.setLocation(0, 0);
		textureLabel.setSize(lengthPixels, lengthPixels);
		this.setLayout(null);
		this.add(textureLabel);
	}
	
	public Block(Block other) {
		this(other.texture);
		this.posX = other.posX;
		this.posY = other.posY;
		this.isAnchored = other.isAnchored;
	}
	
	public static int getLength() {
		return lengthPixels;
	}
	
	public static void setLength(int length) {
		lengthPixels = length;
	}
	
	public void setGridPos(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.setLocation(x * lengthPixels, y * lengthPixels);
	}
	
	public int getGridX() {
		return this.posX;
	}
	
	public int getGridY() {
		return this.posY;
	}
	
	public void anchor() {
		this.isAnchored = true;
	}
	
	public boolean isAnchored() {
		return this.isAnchored;
	}
	
	public void shift(int dir) {
		this.changeLocation(lengthPixels * dir, 0);
		this.posX += dir;
	}
	
	public void descend() {
		this.changeLocation(0, lengthPixels);
		this.posY++;
	}
	
	private void changeLocation(int dx, int dy) {
		Point p = this.getLocation();
		p.x += dx;
		p.y += dy;
		this.setLocation(p);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setSize(400, 800);
		f.setLocationRelativeTo(null);
		Block b = new Block(BlockTexture.BLUE);
		System.out.println(b);; 
		b.setLocation(0, 0);
		f.add(b);
		f.setVisible(true);
		Thread.sleep(1000);
		b.shift(1);
		f.revalidate();
		f.repaint();
		for (int i = 0; i < 20; i++) {
			Thread.sleep(1000);
			b.descend();
			f.repaint();
		}
	}
	
	public String toString() {
		return this.texture.toString();
	}
	
}
