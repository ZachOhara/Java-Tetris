/* DigitTexture.java | A Texture object for any possible decimal digit.
 * Copyright (C) 2015 Zach Ohara
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

package io.github.zachoahra.javatetris.resource.texture.digit;

import io.github.zachoahra.javatetris.resource.texture.Texture;
import io.github.zachoahra.javatetris.score.NumberImage;

public enum DigitTexture {
	
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
	
	private Texture texture;
	
	private DigitTexture(String filename) {
		this.texture = new Texture(filename, NumberImage.getDigitWidthPix(), NumberImage.getDigitHeightPix(), this.getClass());
	}

	public Texture getTexture() {
		return this.texture;
	}
	
	public static DigitTexture get(int i) {
		switch(i) {
		case 0:
			return DigitTexture.D0;
		case 1:
			return DigitTexture.D1;
		case 2:
			return DigitTexture.D2;
		case 3:
			return DigitTexture.D3;
		case 4:
			return DigitTexture.D4;
		case 5:
			return DigitTexture.D5;
		case 6:
			return DigitTexture.D6;
		case 7:
			return DigitTexture.D7;
		case 8:
			return DigitTexture.D8;
		case 9:
			return DigitTexture.D9;
		default:
			return null;
		}
	}

}