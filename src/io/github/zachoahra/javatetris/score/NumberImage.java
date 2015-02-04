package io.github.zachoahra.javatetris.score;

import java.awt.Color;

import io.github.zachoahra.javatetris.resource.texture.digit.DigitTexture;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NumberImage extends JPanel {

	private int number;
	private int numberLength;
	private int holdTotalDigits;
	private boolean useLeftAlign;
	private int leftAlignX;
	private int leftAlignY;
	
	private static final int digitWidthPix = 60;
	private static final int digitHeightPix = 100;
	
	private static final long serialVersionUID = 1L;
	
	public NumberImage(int n) {
		super();
		this.setLayout(null);
		this.number = n;
		this.holdTotalDigits = 0;
		this.setLeftAlign(0, 0);
		this.setLeftAlign(false);
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
	
	public void setTotalDigits(int d) {
		this.holdTotalDigits = d;
		this.update();
	}
	
	public void changeNumber(int dn) {
		this.number += dn;
		this.update();
	}
	
	public void setLeftAlign(int x, int y) {
		this.leftAlignX = x;
		this.leftAlignY = y;
		this.useLeftAlign = true;
		this.update();
	}
	
	public void setLeftAlign(boolean b) {
		this.useLeftAlign = b;
		this.update();
	}
	
	private void update() {
		this.removeAll();
		this.numberLength = new Integer(this.number).toString().length();
		int newLength;
		if (this.holdTotalDigits != 0)
			newLength = this.holdTotalDigits * digitWidthPix;
		else
			newLength = this.numberLength * digitWidthPix;
		this.setSize(newLength, digitHeightPix);
		JLabel jl;
		int tempNumber = this.number;
		for (int i = newLength - digitWidthPix; i >= 0; i -= digitWidthPix) {
			jl = DigitTexture.get(tempNumber % 10).getTexture().getLabel();
			jl.setLocation(i, 0);
			this.add(jl);
			tempNumber /= 10;
		}
		if (this.useLeftAlign) {
			this.setLocation(this.leftAlignX - newLength, this.leftAlignY);
			System.out.println(newLength + ", " + (this.leftAlignX - newLength));
		}
		this.revalidate();
		this.repaint();
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame f = new JFrame();
		f.setLayout(null);
		f.setSize(800, 200);
		NumberImage ni = new NumberImage(25005);
		ni.setTotalDigits(8);
		ni.setLeftAlign(800, 0);
		
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		p.setSize(100, 100);
		p.setLocation(800, 0);
		
		f.add(ni);
		f.add(p);
		f.setVisible(true);
		Thread.sleep(3000);
		ni.changeNumber(111111);
		f.revalidate();
		f.repaint();
	}

}
