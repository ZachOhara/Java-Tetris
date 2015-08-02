/* Copyright (C) 2015 Zach Ohara
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.zachohara.javatetris.score;

import io.github.zachohara.javatetris.resource.texture.digit.DigitTexture;

import java.awt.Color;

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
		return NumberImage.digitWidthPix;
	}

	public static int getDigitHeightPix() {
		return NumberImage.digitHeightPix;
	}

	public int getNumber() {
		return this.number;
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

	public void setLeftAlign(int x) {
		int y = (int) this.getLocation().getY();
		this.setLeftAlign(x, y);
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
		if (this.holdTotalDigits != 0) {
			newLength = this.holdTotalDigits * NumberImage.digitWidthPix;
		} else {
			newLength = this.numberLength * NumberImage.digitWidthPix;
		}
		this.setSize(newLength, NumberImage.digitHeightPix);
		JLabel jl;
		int tempNumber = this.number;
		for (int i = newLength - NumberImage.digitWidthPix; i >= 0; i -= NumberImage.digitWidthPix) {
			jl = DigitTexture.get(tempNumber % 10).getTexture().getLabel();
			jl.setLocation(i, 0);
			this.add(jl);
			tempNumber /= 10;
		}
		if (this.useLeftAlign) {
			this.setLocation(this.leftAlignX - newLength, this.leftAlignY);
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
