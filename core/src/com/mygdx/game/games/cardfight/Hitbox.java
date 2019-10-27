package com.mygdx.game.games.cardfight;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.ui.HoverableUiElement;

public class Hitbox implements HoverableUiElement {

  private int x;
  private int y;
  private int width;
  private int height;

  public Hitbox(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public Hitbox(Hitbox hitbox) {
    this(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
  }

  @Override
  public int getXPosition() {
    return x;
  }

  @Override
  public int getYPosition() {
    return y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  public void setPosition(int x, int y) {
    setX(x);
    setY(y);
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public void render(SpriteBatch sb) {
  }

  @Override
  public void render(SpriteBatch sb, float objectScale) {
  }
}