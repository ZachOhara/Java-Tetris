package io.github.zachoahra.javatetris.resource.texture.block;

import io.github.zachoahra.javatetris.game.Block;
import io.github.zachoahra.javatetris.resource.texture.Texture;

public enum BlockTexture {

	BLUE ("blue.png"),
	GREEN ("green.png"),
	ORANGE ("orange.png"),
	PINK ("pink.png"),
	PURPLE ("purple.png"),
	RED ("red.png"),
	YELLOW ("yellow.png");
	
	private Texture texture;
	
	private BlockTexture(String filename) {
		this.texture = new Texture(filename, Block.getLength(), Block.getLength(), this.getClass());
	}
	
	public Texture getTexture() {
		return this.texture;
	}

}
