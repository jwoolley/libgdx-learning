package com.mygdx.game.games.cardfight;

import com.mygdx.game.games.cardfight.ui.HoverableUiElement;

public class Hitbox implements HoverableUiElement {

  private int x;
  private int  y;
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
}
