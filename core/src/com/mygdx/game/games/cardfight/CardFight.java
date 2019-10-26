package com.mygdx.game.games.cardfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.SizedWindow;
import com.mygdx.game.games.cardfight.player.Player;
import com.mygdx.game.games.cardfight.screens.GameScreen;
import com.mygdx.game.games.cardfight.ui.combat.CombatUi;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;

import java.util.HashMap;
import java.util.Map;

public class CardFight extends Game implements SizedWindow {
  public static final int WINDOW_DEFAULT_X = 1600;
  public static final int WINDOW_DEFAULT_Y = 1000;

  public static SpriteBatch sb;
  public static ShapeRenderer shapeRenderer;
  public static BitmapFont font;

  public static Player player;
  public static CombatUi combatUi;

  private static ScreenPosition screenDimensions = new ScreenPosition(WINDOW_DEFAULT_X, WINDOW_DEFAULT_Y);
  private static ScreenPosition screenPosition = new ScreenPosition(0, 0);

  public static boolean mouseButtonDown = false;
  public static boolean mouseButtonStateChanged = false;

  private static Map<String, Sound> SOUND_MAP = new HashMap<>();

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    sb = new SpriteBatch();
    font = new BitmapFont();

    initializeSounds();

    player = new Player();
    combatUi = new CombatUi();
    setScreen(new GameScreen(this));
  }

  private static final String SOUND_ASSET_DIRECTORY = "audio/sounds/";

  private Sound createSound(String filename) {
    return Gdx.audio.newSound(Gdx.files.internal(SOUND_ASSET_DIRECTORY + filename));
  }

  private void initializeSounds() {
    SOUND_MAP.put("SFX_SHUFFLE_CARDS_1", createSound("SFX_ShuffleCards_1.ogg"));
    SOUND_MAP.put("SFX_UI_CLICK_1", createSound("SFX_UiClick_1.ogg"));
    SOUND_MAP.put("SFX_UI_CLICK_2", createSound("SFX_UiClick_2.ogg"));
    SOUND_MAP.put("SFX_UI_CLICK_MUFFLED_1", createSound("SFX_UiClickMuffled_1.ogg"));

  }

  public static void playSound(String key) {
    if (SOUND_MAP.containsKey(key)) {
      System.out.println("Playing sound: " + key);
      SOUND_MAP.get(key).play();
    } else {
      System.out.println("Sound not found: " + key);
    }
  }

  @Override
  public void dispose() {
    shapeRenderer.dispose();
    sb.dispose();
    font.dispose();
  }

  public void updateMousePosition(int x, int y) {
    screenPosition.x = x;
    screenPosition.y = y;
  }

  @Override
  public int getWidth() {
    return screenDimensions.x;
  }

  @Override
  public int getHeight() {
    return screenDimensions.y;
  }

  public static int getScreenWidth() {
    return screenDimensions.x;
  }

  public static int getScreenHeight() {
    return screenDimensions.y;
  }

  public static int getMouseX() {
    return screenPosition.x;
  }

  public static int getMouseY() {
    return screenPosition.y;
  }

  // TODO: maintain a map of fonts; move to font utility class
  public static int getTextHeight(BitmapFont _font) {
    return (int)CardFight.font.getCapHeight();
  }
}