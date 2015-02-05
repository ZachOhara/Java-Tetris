package io.github.zachoahra.javatetris.game;

import java.awt.Point;

import io.github.zachoahra.javatetris.resource.texture.block.BlockTexture;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Block extends JPanel {
	
	private BlockTexture texture;

	private static int lengthPixels = 50;
	
	private static final long serialVersionUID = 1L;
	
	public Block(BlockTexture texture) {
		super();
		this.texture = texture;
		this.setSize(lengthPixels, lengthPixels);
		JLabel textureLabel = this.texture.getTexture().getLabel();
		textureLabel.setLocation(0, 0);
		textureLabel.setSize(lengthPixels, lengthPixels);
		this.setLayout(null);
		this.add(textureLabel);
	}
	
	public Block(Block other) {
		this(other.texture);
	}
	
	public static int getLength() {
		return lengthPixels;
	}
	
	public static void setLength(int length) {
		lengthPixels = length;
	}
	
	public void setGridPos(int x, int y) {
		this.setLocation(x * lengthPixels, y * lengthPixels);
	}
	
	public void translate(int dx, int dy) {
		Point p = this.getLocation();
		p.x += dx * lengthPixels;
		p.y += dy * lengthPixels;
		this.setLocation(p);
	}
	
	public String toString() {
		return this.texture.toString();
	}
	
}
