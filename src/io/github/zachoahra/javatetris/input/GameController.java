/* GameController.java | An interface for any other classes that intend
 * to be main controller for the game. This includes a human input
 * manager, or any tetris-playing robots.
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

public interface GameController {

	public void setGame(GameManager game);
	public boolean hasControl();
	public void setControl(boolean b);

	public void start();

}
