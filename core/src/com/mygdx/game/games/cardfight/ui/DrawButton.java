package com.mygdx.game.games.cardfight.ui;

import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.player.Player;

public class DrawButton extends AbstractButton {
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
    Player player = CardFight.player;
    if (player.hand.size() < Player.STARTING_HAND_SIZE) {
//      CardFight.playSound("SFX_UI_CLICK_1");
      System.out.println("Initiating draw or shuffle action.");
        if (player.deck.size() == 0) {
          System.out.println("Initiating shuffle action.");
          player.initiateShuffleDiscardIntoDeck();
        } else {
          System.out.println("Initiating draw action.");
          player.drawTopCardFromDeck();
        }
    } else {
      CardFight.playSound("SFX_UI_CLICK_MUFFLED_1");
    }
  }
}
