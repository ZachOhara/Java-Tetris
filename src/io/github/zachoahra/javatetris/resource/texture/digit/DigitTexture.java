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