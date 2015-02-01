package io.github.zachoahra.javatetris.resource.texture.block;

import io.github.zachoahra.javatetris.game.Block;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public enum BlockTexture {

	NULL (),
	BLUE ("blue.png"),
	GREEN ("green.png"),
	ORANGE ("orange.png"),
	PINK ("pink.png"),
	PURPLE ("purple.png"),
	RED ("red.png"),
	YELLOW ("yellow.png");
	
	private BufferedImage texture;
	private String filename;
	
	private BlockTexture(String filename) {
		this.filename = filename;
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(this.getClass().getResource(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.texture = scale(temp, Block.getLength());
	}

	private BlockTexture() {
		this.texture = null;
	}

	public ImageIcon getImage() {
		return new ImageIcon(this.texture);
	}

	public JLabel getLabel() {
		return new JLabel(this.getImage());
	}

	public static BufferedImage scale(BufferedImage b, int newLen) {
		int oldLen = b.getWidth();
		BufferedImage db = new BufferedImage(newLen, newLen, b.getType());
		Graphics2D g = db.createGraphics();
		g.drawImage(b, 0, 0, newLen, newLen, 0, 0, oldLen, oldLen, null);
		g.dispose();
		return db;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		f.add(BlockTexture.BLUE.getLabel());
		f.setVisible(true);
	}
	
	public String toString() {
		return this.filename.substring(0, this.filename.length() - 4);
	}

}
