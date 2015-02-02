package io.github.zachoahra.javatetris.resource.texture.digit;

import io.github.zachoahra.javatetris.score.NumberImage;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public enum DigitTexture {
	
	NULL (),
	D0 ("0.png"),
	D1 ("1.png"),
	D2 ("2.png"),
	D3 ("3.png"),
	D4 ("4.png"),
	D5 ("5.png"),
	D6 ("6.png"),
	D7 ("7.png"),
	D8 ("8.png"),
	D9 ("9.png");
	
	private BufferedImage texture;
	private String filename;
	
	private DigitTexture(String filename) {
		this.filename = filename;
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(this.getClass().getResource(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.texture = scale(temp, NumberImage.getDigitWidthPix(), NumberImage.getDigitHeightPix());
	}

	private DigitTexture() {
		this.texture = null;
	}

	public ImageIcon getImage() {
		return new ImageIcon(this.texture);
	}

	public JLabel getLabel() {
		return new JLabel(this.getImage());
	}

	public static BufferedImage scale(BufferedImage b, int newW, int newH) {
		int oldW = b.getWidth();
		int oldH = b.getHeight();
		BufferedImage db = new BufferedImage(newW, newH, b.getType());
		Graphics2D g = db.createGraphics();
		g.drawImage(b, 0, 0, newW, newH, 0, 0, oldW, oldH, null);
		g.dispose();
		return db;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new FlowLayout());
		f.add(DigitTexture.D0.getLabel());
		f.setVisible(true);
	}
	
	public String toString() {
		return this.filename.substring(0, this.filename.length() - 4);
	}

}