package io.github.zachoahra.javatetris.plugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.CancellationException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PluginLoader {
	
	public JavaPlugin loadPlugin() throws Exception {
		JFrame f = new JFrame();
		f.setResizable(false);
		f.setSize(500, 400);
		f.setLocationRelativeTo(null);
		f.setTitle("Load plugin");
		
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter fil = new FileNameExtensionFilter("*.jar", "jar");
		fc.setFileFilter(fil);
		
		File fi;
		int r = fc.showOpenDialog(f);
		if (r == JFileChooser.APPROVE_OPTION)
			fi = fc.getSelectedFile();
		else
			throw new CancellationException();
		System.out.println(fi.getName());
		
		return this.loadPlugin(fi.toURI().toURL());
	}
	
	private JavaPlugin loadPlugin(URL url) throws Exception {
		URL[] urlArr = {url};
		URLClassLoader ucl = new URLClassLoader(urlArr);

		Class<?> cl = ucl.loadClass("Robot");
		
		Constructor<?> cons = cl.getConstructor(new Class<?>[0]);
		Object ob = cons.newInstance(new Object[0]);
		if (ob instanceof JavaPlugin)
			return (JavaPlugin)ob;
		else
			throw new Exception("Class is not an instance of JavaPlugin");
	}
	
	private JavaPlugin loadPlugin(File file) throws Exception {
		JarFile jf = new JarFile(file);
		String loadedClass = jf.getJarEntry("Robot.class").getName();
		
		for (Object je : jf.stream().toArray())
			System.out.println(((JarEntry)je).getName());
		
		jf.close();
		System.out.println(loadedClass);
		return this.loadPlugin(loadedClass);
		//return null;
	}
	
	private JavaPlugin loadPlugin(String classname) throws Exception {
		Class<?> cl = Class.forName(classname);
		Constructor<?> cons = cl.getConstructor(new Class<?>[0]);
		Object ob = cons.newInstance(new Object[0]);
		if (ob instanceof JavaPlugin)
			return (JavaPlugin)ob;
		else
			throw new Exception("Class is not an instance of JavaPlugin");
	}
	
	public static void main(String[] args) throws Exception {
		JavaPlugin p = new PluginLoader().loadPlugin();
		System.out.println(p.toString());
	}

}
