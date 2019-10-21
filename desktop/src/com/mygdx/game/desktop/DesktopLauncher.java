package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.SizedWindow;
import com.mygdx.game.examples.CircleGame;
import com.mygdx.game.examples.ImageLoader;
import com.mygdx.game.examples.UserInputTest;
import com.mygdx.game.examples.RandomWalk;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.circlegamealpha.CircleGameAlpha;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DesktopLauncher {
//	static final Mode MODE = Mode.IMAGE_LOADER;
//	static final Mode MODE = Mode.USER_INPUT_TEST;
//	static final Mode MODE = Mode.RANDOM_WALK;
//	static final Mode MODE = Mode.CIRCLE_GAME;
//	static final Mode MODE = Mode.CIRCLE_GAME_ALPHA;
	static final Mode MODE = Mode.CARD_FIGHT;

	enum Mode {
		IMAGE_LOADER,
		USER_INPUT_TEST,
		RANDOM_WALK,
		CIRCLE_GAME,
		CIRCLE_GAME_ALPHA,
		CARD_FIGHT;
	}

	static final Map<Mode,  Function<Void, ApplicationListener>> modes = new HashMap<Mode, Function<Void, ApplicationListener>>() { {
		put(Mode.IMAGE_LOADER, (v) -> new ImageLoader());
		put(Mode.USER_INPUT_TEST, (v) -> new UserInputTest());
		put(Mode.RANDOM_WALK, (v) -> new RandomWalk());
		put(Mode.CIRCLE_GAME, (v) -> new CircleGame());
		put(Mode.CIRCLE_GAME_ALPHA, (v) -> new CircleGameAlpha());
		put(Mode.CARD_FIGHT, (v) -> new CardFight());
	}};

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		ApplicationListener app = modes.get(MODE).apply(null);

		if (app instanceof SizedWindow) {
			config.width = (((SizedWindow) app).getWidth());
			config.height = (((SizedWindow) app).getHeight());
		}
		new LwjglApplication(app, config);
	}
}
