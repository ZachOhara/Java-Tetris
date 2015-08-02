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

package io.github.zachohara.javatetris.resource;

import io.github.zachohara.javatetris.score.NumberImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Texture extends Resource {

	private BufferedImage image;

//	public Texture(String filename, int width, int height) {
//		this.filename = filename;
//		BufferedImage temp = null;
//		try {
//			temp = ImageIO.read(resourceclass.getResource(filename));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		this.texture = Texture.scale(temp, width, height);
//	}
	
	private static final String DEFAULT_FILETYPE = "png";
	
	@Deprecated
	public Texture(String folder, String filename, int width, int height) {
		this(folder, filename);
		this.image = Texture.scale(this.image, width, height);
	}
	
	public Texture(String folder, String filename, String filetype) {
		super(folder, filename + "." + filetype);
		try {
			this.image = ImageIO.read(this.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Texture(String folder, String filename) {
		this(folder, filename, DEFAULT_FILETYPE);
	}

	public ImageIcon getImage() {
		return new ImageIcon(this.image);
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

	@Override
	public String toString() {
		return "Texture[" + this.getFile() + "]";
	}

}
