package com.mygdx.game.games.cardfight.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.cards.*;
import com.mygdx.game.games.cardfight.ui.combat.CombatUi;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
  public static final int STARTING_HAND_SIZE = 3;

  public final List<AbstractCard> decklist;
  public final List<AbstractCard> deck;
  public final List<AbstractCard> hand;
  public final List<AbstractCard> discardPile;

  public final PlayerInfo playerInfo;

  public Player() {
    decklist = new ArrayList<>();
    deck = new ArrayList<>();
    hand = new ArrayList<>();
    discardPile = new ArrayList<>();
    playerInfo = new PlayerInfo();
  }

  // TODO: move to hand/cardgroup class
  public void renderHand(SpriteBatch sb) {
    final int screenXCenter = Gdx.graphics.getWidth() / 2;
    // TODO: scale these values according to screen scale
    switch (this.hand.size()) {
      case 1:
        this.hand.get(0).xPos = screenXCenter - AbstractCard.DEFAULT_WIDTH / 2;
        this.hand.get(0).yPos = CombatUi.HAND_OFFSET_Y;
        break;
      case 2:
        this.hand.get(0).xPos = screenXCenter - (AbstractCard.DEFAULT_WIDTH + CombatUi.HAND_SPACING / 2);
        this.hand.get(0).yPos = CombatUi.HAND_OFFSET_Y;
        this.hand.get(1).xPos = screenXCenter + CombatUi.HAND_SPACING / 2;
        this.hand.get(1).yPos = CombatUi.HAND_OFFSET_Y;
        break;
      case 3:
        this.hand.get(0).xPos = screenXCenter - AbstractCard.DEFAULT_WIDTH / 2;
        this.hand.get(0).yPos = CombatUi.HAND_OFFSET_Y;
        this.hand.get(1).xPos = screenXCenter - (int)(1.5 * AbstractCard.DEFAULT_WIDTH + CombatUi.HAND_SPACING);
        this.hand.get(1).yPos = CombatUi.HAND_OFFSET_Y;
        this.hand.get(2).xPos = screenXCenter + (int)(0.5f * AbstractCard.DEFAULT_WIDTH + CombatUi.HAND_SPACING);
        this.hand.get(2).yPos = CombatUi.HAND_OFFSET_Y;
        break;
      default:
        break;
    }

    List<AbstractCard> discardList = new ArrayList<>();
    for (AbstractCard c : hand) {
      if (c.discardFlag) {
        discardList.add(c);
      } else {
        ScreenPosition nudge = c.getNudgeDimensions();
        c.xPos += nudge.x;
        c.yPos += nudge.y;
        c.render(sb);
      }
    }
    for (AbstractCard c : discardList) {
      cardToDiscardPile(c);
    }
  }

  private static final AbstractCardBack cardBack = new DefaultCardBack();
  private static final int DRAW_PILE_X_POS = 32;
  private static final int DRAW_PILE_Y_POS = 32 + CombatUi.INFO_BAR_HEIGHT;
  public void renderDrawPile(SpriteBatch sb){
    cardBack.xPos = DRAW_PILE_X_POS;
    cardBack.yPos = DRAW_PILE_Y_POS;
    cardBack.render(sb);
  }

  private static final int DISCARD_X_POS_OFFSET = 32;
  private static final int DISCARD_X_POS = Gdx.graphics.getWidth() - (AbstractCardBack.DEFAULT_WIDTH + DISCARD_X_POS_OFFSET);
  private static final int DISCARD_Y_POS = 32 + CombatUi.INFO_BAR_HEIGHT;
  private static final float DISCARD_PILE_SCALE = 0.75f;

  public void renderDiscard(SpriteBatch sb){
    if (!discardPile.isEmpty()) {
      AbstractCard topCard = discardPile.get(0);
      topCard.xPos = DISCARD_X_POS;
      topCard.yPos = DISCARD_Y_POS;
      topCard.render(sb, DISCARD_PILE_SCALE);
    }
  }

  public void cardToDiscardPile(AbstractCard card) {
    if (hand.contains(card)) {
      hand.remove(card);
    }
    discardPile.add(0, card);
  }

  public void dealHand() {
    deck.clear();
    deck.addAll(decklist);
    Collections.shuffle(deck);

    final List<AbstractCard> startingHand = new ArrayList<>();
    startingHand.addAll(deck.subList(0,STARTING_HAND_SIZE));
    deck.removeAll(startingHand);
    this.hand.clear();
    this.hand.addAll(startingHand);
  }
}
