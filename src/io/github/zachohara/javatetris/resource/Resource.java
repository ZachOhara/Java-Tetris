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

import java.io.File;

/**
 * A {@code Resource} represents any loaded external resource, such as an image or a font.
 * 
 * @author Zach Ohara
 */
public abstract class Resource {
	
	private final File loadedFile;
	
	private static final String RESOURCE_FOLDER_LOCATION = "res";
	private static final File RESOURCE_FOLDER = new File(RESOURCE_FOLDER_LOCATION);

	public Resource(File subfile) {
		this(subfile.getPath());
	}
	
	public Resource(String subfile) {
		this.loadedFile = new File(RESOURCE_FOLDER, subfile);
	}
	
	public Resource(String folder, String file) {
		this(new File(folder, file));
	}
	
	public File getFile() {
		return this.loadedFile;
	}
	
	@Override
	public String toString() {
		return "Resource[" + this.getFile() + "]";
	}
	
}
