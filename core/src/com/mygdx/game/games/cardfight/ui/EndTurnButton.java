package com.mygdx.game.games.cardfight.ui;

import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.cards.AbstractCard;
import com.mygdx.game.games.cardfight.player.Player;

public class EndTurnButton extends AbstractButton {
  public static final String KEY = "EndTurnButton";
  public static final String TEXT = "End Turn";

  public static final String IMG_PATH = "combat/panel/draw-button.png";

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

  public EndTurnButton() {
    super(KEY, TEXT, IMG_PATH);
    this.width = WIDTH;
    this.height = HEIGHT;
    this.xPos = X_POS;
    this.yPos = Y_POS;
  }

  @Override
  public void use() {
    // TODO: encapsulate this logic ("end turn") in an action
    //       should include end-turn logic and "End Turn" UI message
    //       disable actionable battle UI (cards, player power, etc.)
    Player player = CardFight.player;

    player.hand.forEach(AbstractCard::discard);

    getBattleManager().queueMonsterTurns();

    CardFight.playSound("SFX_UI_CLICK_1");
    System.out.println("Initiating draw or shuffle action.");

    // TODO: encapsulate this logic ("draw N cards") in an action
    for (int i = 0; i < getBattleManager().getStartingHandSize(); i++) {
      System.out.println("Initiating shuffle action.");
      if (player.deck.isEmpty() && !player.discardPile.isEmpty()) {
        // TODO: encapsulate this logic ("shuffle discard into deck") in an action
        player.initiateShuffleDiscardIntoDeck();
        i--;
      } else {
        // TODO: encapsulate this logic ("draw one card") in an action (including draw pile empty check /
        //       "shuffle discard into deck" action)
        System.out.println("Initiating draw action.");
        player.drawTopCardFromDeck();
      }
    }
  }
}
