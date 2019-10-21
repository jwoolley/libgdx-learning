package com.mygdx.game.games.cardfight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.SizedWindow;
import com.mygdx.game.games.cardfight.player.Player;
import com.mygdx.game.games.cardfight.screens.GameScreen;
import com.mygdx.game.games.cardfight.ui.CombatUi;

public class CardFight extends Game implements SizedWindow {
  public static final int WINDOW_DEFAULT_X = 1600;
  public static final int WINDOW_DEFAULT_Y = 1000;

  public SpriteBatch sb;
  public ShapeRenderer shapeRenderer;
  public BitmapFont font;

  public static Player player;
  public static CombatUi combatUi;

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    sb = new SpriteBatch();
    font = new BitmapFont();

    this.player = new Player();
    this.combatUi = new CombatUi();
    setScreen(new GameScreen(this));
  }

  @Override
  public void dispose() {
    this.shapeRenderer.dispose();
    this.sb.dispose();
    this.font.dispose();
  }

  @Override
  public int getWidth() {
    return WINDOW_DEFAULT_X;
  }

  @Override
  public int getHeight() {
    return WINDOW_DEFAULT_Y;
  }
}