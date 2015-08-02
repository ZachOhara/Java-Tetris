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

public enum DigitTexture {

	D0,
	D1,
	D2,
	D3,
	D4,
	D5,
	D6,
	D7,
	D8,
	D9;

	private Texture texture;
	
	private static final String DIGIT_TEXTURE_FOLDER = "digit";
	
	@SuppressWarnings("deprecation")
	private DigitTexture() {
		this.texture = new Texture(DIGIT_TEXTURE_FOLDER, this.getName(), NumberImage.getDigitWidthPix(), NumberImage.getDigitHeightPix());
	}

	public Texture getTexture() {
		return this.texture;
	}

	public static DigitTexture get(int i) {
		String digitName = "D" + i;
		return Enum.valueOf(DigitTexture.class, digitName);
	}
	
	public String getName() {
		return this.name().substring(1);
	}

}
