package com.mygdx.game.games.cardfight.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;

import javax.smartcardio.Card;

public class CombatUi {

  public static final int HAND_OFFSET_Y = 70;
  public static final int INFO_BAR_HEIGHT = 60;
  public static final int HAND_SPACING = 16;

  final InfoPanel infoPanel;
  public CombatUi() {
    infoPanel = new InfoPanel();
  }

  public void render(SpriteBatch sb) {
    infoPanel.render(sb);
    CardFight.player.renderHand(sb);
  }
}
