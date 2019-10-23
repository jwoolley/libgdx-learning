package com.mygdx.game.games.cardfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.SizedWindow;
import com.mygdx.game.games.cardfight.player.Player;
import com.mygdx.game.games.cardfight.screens.GameScreen;
import com.mygdx.game.games.cardfight.ui.combat.CombatUi;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;

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

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    sb = new SpriteBatch();
    font = new BitmapFont();

    player = new Player();
    combatUi = new CombatUi();
    setScreen(new GameScreen(this));
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
}