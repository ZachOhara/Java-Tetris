package io.github.zachoahra.javatetris.resource.texture;

import io.github.zachoahra.javatetris.score.NumberImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Texture {
	
	private BufferedImage texture;
	private String filename;
	
	public Texture(String filename, int width, int height, Class<?> resourceclass) {
		this.filename = filename;
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(resourceclass.getResource(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.texture = scale(temp, width, height);
	}

	public ImageIcon getImage() {
		return new ImageIcon(this.texture);
	}

	public JLabel getLabel() {
		JLabel jl = new JLabel(this.getImage());
		jl.setSize(NumberImage.getDigitWidthPix(), NumberImage.getDigitHeightPix());
		return jl;
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
	
	public String toString() {
		return this.filename.substring(0, this.filename.length() - 4);
	}

}
