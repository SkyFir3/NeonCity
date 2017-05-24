package com.luis.neoncity.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.luis.neoncity.NeonCity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.addIcon("icon256.jpg", Files.FileType.Internal);
		config.addIcon("icon64.jpg", Files.FileType.Internal);
		config.addIcon("icon32.jpg", Files.FileType.Internal);

		config.title = "N e o n C i t y";

		config.resizable = true;

		config.width = 1366;
		config.height = 768;
		new LwjglApplication(new NeonCity(), config);
	}
}
