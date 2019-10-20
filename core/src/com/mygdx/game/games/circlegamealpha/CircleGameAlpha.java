package com.mygdx.game.games.circlegamealpha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.games.circlegamealpha.screens.TitleScreen;
import com.mygdx.player.PlayerInfo;

public class CircleGameAlpha extends Game {
  public SpriteBatch sb;
  public ShapeRenderer shapeRenderer;
  public BitmapFont font;
  public PlayerInfo playerInfo;

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    sb = new SpriteBatch();
    font = new BitmapFont();
    this.playerInfo = new PlayerInfo();
    setScreen(new TitleScreen(this));
  }

  @Override
  public void dispose() {
    this.shapeRenderer.dispose();
    this.sb.dispose();
    this.font.dispose();
  }
}
