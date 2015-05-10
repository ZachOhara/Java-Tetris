/* KeyboardInputManager.java | Translates keyboard input to game actions.
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

package io.github.zachoahra.javatetris.input;

import io.github.zachoahra.javatetris.management.GameManager;
import io.github.zachoahra.javatetris.management.TimeManager;
import io.github.zachoahra.javatetris.plugin.JavaPlugin;
import io.github.zachoahra.javatetris.plugin.PluginLoader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JFrame;

public class KeyboardInputManager implements KeyListener, GameController {

	private GameManager game;
	private TimeManager time;
	private boolean hasControl;

	private static final boolean displayOutputs = false;

	private static final Integer[] inputRotateCW = {32};
	private static final Integer[] inputRotateCCW = {16};
	private static final Integer[] inputLeft = {65, 37};
	private static final Integer[] inputRight = {68, 39};
	private static final Integer[] inputUp = {87, 38};
	private static final Integer[] inputDown = {83, 40};
	private static final Integer[] loadPlugin = {80};

	public KeyboardInputManager(JFrame window, GameManager game, TimeManager time) {
		this.setGame(game);
		window.addKeyListener(this);
		this.time = time;
		this.hasControl = false;
	}

	@Override
	public boolean hasControl() {
		return this.hasControl;
	}

	@Override
	public void setControl(boolean b) {
		this.hasControl = b;
	}

	@Override
	public void setGame(GameManager game) {
		this.game = game;
	}
	
	@Override
	public void start() {
		// do nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (displayOutputs)
			System.out.println(code);

		if (this.hasControl && this.game.isRunning()) {
			// left
			if (Arrays.asList(inputLeft).contains(code))
				this.game.getBlockGrid().shiftShape(-1); 
			// right
			if (Arrays.asList(inputRight).contains(code))
				this.game.getBlockGrid().shiftShape(1);
			// down
			if (Arrays.asList(inputDown).contains(code)) {
				this.time.setFastMode(true);
				this.time.interrupt();
			}
			// up
			if (Arrays.asList(inputUp).contains(code))
				this.game.getBlockGrid().hardDrop();
			// rotate clockwise
			if (Arrays.asList(inputRotateCW).contains(code))
				this.game.getBlockGrid().rotateShape(1);
			// rotate counterclockwise
			if (Arrays.asList(inputRotateCCW).contains(code))
				this.game.getBlockGrid().rotateShape(-1);
		}
		// load plugin
		if (Arrays.asList(loadPlugin).contains(code)) {
			this.game.setPaused(true);
			JavaPlugin plugin = PluginLoader.handleLoadPlugin();
			if (plugin != null)
				this.game.setController(plugin);
			else
				this.game.setController(this);
			this.game.setPaused(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (this.hasControl)
			if (Arrays.asList(inputDown).contains(code))
				this.time.setFastMode(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ignore input
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		new KeyboardInputManager(f, null, null);
		f.setVisible(true);
	}

}
