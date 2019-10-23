package com.mygdx.game.games.cardfight.ui.combat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.cards.AbstractCard;
import com.mygdx.game.games.cardfight.ui.DrawButton;
import com.mygdx.game.games.cardfight.ui.InfoPanel;

public class CombatUi {

  public static final int HAND_OFFSET_Y = 70;
  public static final int INFO_BAR_HEIGHT = 60;
  public static final int HAND_SPACING = 16;

  final InfoPanel infoPanel;
  public CombatUi() {
    infoPanel = new InfoPanel();
  }

  private final DrawButton drawButton = new DrawButton();

  public void render(SpriteBatch sb) {
    infoPanel.render(sb);
    CardFight.player.renderDrawPile(sb);
    CardFight.player.renderDiscard(sb);
    CardFight.player.renderHand(sb);
    drawButton.render(sb);
  }

  public void update() {
    updateDrawPile();
    updateDiscardPile();
    updateHand();
    drawButton.update();
  }

  // TODO: move to hand/cardgroup class
  public void updateHand() {
    for (AbstractCard card: CardFight.player.hand) {
      card.update();
    }
  }

  public void updateDrawPile() { }

  public void updateDiscardPile() { }
}
