package io.github.zachoahra.javatetris.score;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NumberImage extends JPanel {

	private int number;
	
	private static final int digitWidthPix = 60;
	private static final int digitHeightPix = 100;
	
	private static final long serialVersionUID = 1L;
	
	public NumberImage(int n) {
		super();
		this.number = n;
		this.update();
	}
	
	public NumberImage() {
		this(0);
	}

	public static int getDigitWidthPix() {
		return digitWidthPix;
	}

	public static int getDigitHeightPix() {
		return digitHeightPix;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int n) {
		this.number = n;
		this.update();
	}
	
	public void changeNumber(int dn) {
		this.number += dn;
		this.update();
	}
	
	private void update() {
		//TODO: method stub: reset the image using the current this.number
	}

}
