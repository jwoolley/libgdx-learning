package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.UserInputTest;
import com.mygdx.game.RandomWalk;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DesktopLauncher {

	static final Mode MODE = Mode.USER_INPUT_TEST;
//	static final Mode MODE = Mode.RANDOM_WALK;

	enum Mode {
		USER_INPUT_TEST,
		RANDOM_WALK;
	}

	static final Map<Mode,  Function<Void, ApplicationAdapter>> modes = new HashMap<Mode, Function<Void, ApplicationAdapter>>() { {
		put(Mode.USER_INPUT_TEST, (v) -> new UserInputTest());
		put(Mode.RANDOM_WALK, (v) -> new RandomWalk());
	}};

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(modes.get(MODE).apply(null), config);
	}
}
