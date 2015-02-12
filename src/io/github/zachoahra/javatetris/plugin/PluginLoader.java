package io.github.zachoahra.javatetris.plugin;

import java.io.File;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.CancellationException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PluginLoader {

	public static JavaPlugin handleLoadPlugin() {
		JavaPlugin plugin = null;
		try {
			plugin = loadPlugin();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalClassFormatException e) {
			e.printStackTrace();
		} catch (CancellationException ignore) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plugin;
	}

	public static JavaPlugin loadPlugin() throws ClassNotFoundException,
	NoSuchMethodException, IllegalAccessException,
	IllegalClassFormatException, CancellationException, Exception {
		JFrame dialogFrame = new JFrame();
		dialogFrame.setResizable(false);
		dialogFrame.setSize(500, 400);
		dialogFrame.setLocationRelativeTo(null);
		dialogFrame.setTitle("Load plugin");

		JFileChooser dialogBody = new JFileChooser();
		FileNameExtensionFilter jarFilter = new FileNameExtensionFilter("*.jar", "jar");
		dialogBody.setFileFilter(jarFilter);

		File selectedFile;
		int dialogResult = dialogBody.showOpenDialog(dialogFrame);
		if (dialogResult == JFileChooser.APPROVE_OPTION)
			selectedFile = dialogBody.getSelectedFile();
		else
			throw new CancellationException();

		return loadPlugin(selectedFile.toURI().toURL());
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws ClassNotFoundException if there is no Robot class defined in
	 * 		the default package of the imported jar
	 * @throws NoSuchMethodException if there is no default constructor for
	 * 		the Robot class
	 * @throws IllegalAccessException if the default constructor for Robot is not visible
	 * @throws IllegalClassFormatException if the found Robot class does
	 * 		not subclass JavaPlugin
	 * @throws Exception if an error is caused by the back-end, and is not
	 * 		necessarily the result of the plugin being incorrect
	 */
	private static JavaPlugin loadPlugin(URL url) throws ClassNotFoundException,
	NoSuchMethodException, IllegalAccessException,
	IllegalClassFormatException, Exception {
		URL[] urlArray = {url};
		URLClassLoader loader = new URLClassLoader(urlArray);
		Class<?> targetClass = loader.loadClass("Robot");
		loader.close();
		Constructor<?> defaultConstructor = targetClass.getConstructor(new Class<?>[0]);
		Object targetObject = defaultConstructor.newInstance(new Object[0]);
		if (targetObject instanceof JavaPlugin)
			return (JavaPlugin)targetObject;
		else
			throw new IllegalClassFormatException("Robot is not an instance of JavaPlugin");
	}

	public static void main(String[] args) throws Exception {
		JavaPlugin p = PluginLoader.loadPlugin();
		System.out.println(p.toString());
	}

}
