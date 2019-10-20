package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.RandomWalk;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DesktopLauncher {

	static enum Mode {
		CONTROLLER_TEST,
		RANDOM_WALK;
	}

	static final Mode MODE = Mode.RANDOM_WALK;

	static final Map<Mode,  Function<Void, ApplicationAdapter>> modes = new HashMap<Mode, Function<Void, ApplicationAdapter>>() { {
		put(Mode.CONTROLLER_TEST, (v) -> new MyGdxGame());
		put(Mode.RANDOM_WALK, (v) -> new RandomWalk());
	}};

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(modes.get(MODE).apply(null), config);
	}
}
