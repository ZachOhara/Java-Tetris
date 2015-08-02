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

package io.github.zachohara.javatetris.window;

import io.github.zachohara.javatetris.input.KeyboardInputManager;
import io.github.zachohara.javatetris.management.GameManager;
import io.github.zachohara.javatetris.management.LevelManager;
import io.github.zachohara.javatetris.management.ScoreManager;
import io.github.zachohara.javatetris.management.TimeManager;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	private GameManager game;
	private LevelManager level;
	private ScoreManager score;
	private TimeManager time;
	private NextBlockPanel nextShapePanel;

	private static final String windowTitle = "Tetris by Zach Ohara";

	private static final long serialVersionUID = 1L;

	public GameWindow() {
		super(GameWindow.windowTitle);

		this.setSize(GameWindow.getWindowSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);

		this.level = new LevelManager();
		this.score = new ScoreManager(this);
		this.nextShapePanel = new NextBlockPanel(this);
		this.game = new GameManager(this, this.level, this.score, this.nextShapePanel);
		this.time = new TimeManager(this.level, this.game);
		this.game.setController(new KeyboardInputManager(this, this.game, this.time));
	}

	public GameManager getGame() {
		return this.game;
	}

	public void startGame() {
		this.time.start();
		this.game.startGame();
	}

	public void endGame() {
		this.time.interrupt();
	}

	private static Dimension getWindowSize() {
		return new Dimension(975, 978);
		// TODO: calculate size needs based on the size needs of the constituent window
		// elements
	}

	public void doInput(int code) {
		// do nothing, for now
	}

	public static void main(String[] args) {
		GameWindow g = new GameWindow();
		g.setVisible(true);
		g.startGame();
	}

}
