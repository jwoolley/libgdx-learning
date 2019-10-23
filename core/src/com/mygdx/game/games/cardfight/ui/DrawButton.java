package com.mygdx.game.games.cardfight.ui;

public class DrawButton extends AbstractButton {
  // TODO: calculate this instead of fudging it
  public static final String KEY = "DrawButton";
  public static final String TEXT = "Draw";

  public static final String IMG_PATH = "combat/panel/draw-button.png";
  private static final int textWidth = 16;

  public static final int X_POS = 32;
  public static final int Y_POS = 360;

  public static final int WIDTH = 154;
  public static final int HEIGHT = 66;

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  public DrawButton() {
    super(KEY, TEXT, IMG_PATH);
    this.width = WIDTH;
    this.height = HEIGHT;
    this.xPos = X_POS;
    this.yPos = Y_POS;
  }

  public int getTextWidth() {
    return textWidth;
  }

  @Override
  public void use() {
    System.out.println("DrawButton.use called");
  }
}
