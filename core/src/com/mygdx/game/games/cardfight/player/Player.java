package com.mygdx.game.games.cardfight.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.cards.*;
import com.mygdx.game.games.cardfight.ui.combat.CombatUi;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        this.hand.get(1).xPos = screenXCenter - (int) (1.5 * AbstractCard.DEFAULT_WIDTH + CombatUi.HAND_SPACING);
        this.hand.get(1).yPos = CombatUi.HAND_OFFSET_Y;
        this.hand.get(2).xPos = screenXCenter + (int) (0.5f * AbstractCard.DEFAULT_WIDTH + CombatUi.HAND_SPACING);
        this.hand.get(2).yPos = CombatUi.HAND_OFFSET_Y;
        break;
      default:
        break;
    }

    List<AbstractCard> drawFromDeckList = new ArrayList<>();
    for (AbstractCard c : deck) {
      if (c.handFromDeckFlag) {
        System.out.println("setting handFromDeckFlag for " + c.getClass().getSimpleName());

        drawFromDeckList.add(c);
      }
    }

    for (AbstractCard c : drawFromDeckList) {
      cardToHandFromDeck(c);
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
      cardToDiscardFromHand(c);
    }

    List<AbstractCard> deckFromDiscardList = new ArrayList<>();
    for (AbstractCard c : discardPile) {
      if (c.deckFromDiscardFlag) {
        deckFromDiscardList.add(c);
      }
    }
    for (AbstractCard c : deckFromDiscardList) {
      cardToDeckFromDiscard(c);
    }

    if (shuffleDeck) {
      // TODO: move to util method (so we can e.g. hook into shuffling)
      Collections.shuffle(deck);
      shuffleDeck = false;
    }
  }

  private static final AbstractCardBack cardBack = new DefaultCardBack();
  private static final int DRAW_PILE_X_POS = 32;
  private static final int DRAW_PILE_Y_POS = 32 + CombatUi.INFO_BAR_HEIGHT;

  public void renderDrawPile(SpriteBatch sb){
    cardBack.xPos = DRAW_PILE_X_POS;
    cardBack.yPos = DRAW_PILE_Y_POS;
    cardBack.render(sb);
    renderDrawPileText(sb);
  }

  // TODO: scale this text according to screen scale (Get card-in-hand size / standard card size from AbstractCard)
  private static final int DRAW_PILE_TEXT_X_SHIFT = -8;
  private static final int DRAW_PILE_TEXT_Y_PADDING = 4;

  private void renderDrawPileText(SpriteBatch sb) {
    BitmapFont font = CardFight.font;
    font.setColor(Color.YELLOW.cpy());
    font.draw(sb, "" + deck.size(), DRAW_PILE_X_POS + (float)cardBack.getWidth() / 2 + DRAW_PILE_TEXT_X_SHIFT,
        DRAW_PILE_Y_POS - ((float)CardFight.getTextHeight(CardFight.font)/2 + DRAW_PILE_TEXT_Y_PADDING));
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

  public void cardToDiscardFromHand(AbstractCard card) {
    card.discardFlag = false; /* is this the right place for this? */
    cardFromListToList(hand, discardPile, card, true);
  }

  public void drawTopCardFromDeck() {
    System.out.println("drawTopCardFromDeck called");
    if (deck.size() > 0) {
      System.out.println("drawing top card from deck");

      deck.get(0).handFromDeckFlag = true;
    }
  }

  public void cardToHandFromDeck(AbstractCard card) {
    System.out.println("cardToHandFromDeck called");
    card.handFromDeckFlag = false; /* is this the right place for this? */
    cardFromListToList(deck, hand, card, false);
  }

  public void cardToDeckFromDiscard(AbstractCard card) {
    card.deckFromDiscardFlag = false; /* is this the right place for this? */
    cardFromListToList(discardPile, deck, card, true);
  }

  public static boolean shuffleDeck = false;

  public void cardFromListToList(List<AbstractCard> fromList, List<AbstractCard> toList, AbstractCard card, boolean toFirstLocation) {
    if (fromList.contains(card)) {
      fromList.remove(card);
      if (toFirstLocation) {
        toList.add(0, card);
      } else {
        toList.add(card);
      }
    } else {
      System.out.println("cardFromListToList card not in expected list. card: " + card.key);
    }
  }

  public static List<String> cardListToKeyList(List<AbstractCard> cards) {
    return cards.stream().map(c -> c.key).collect(Collectors.toList());
  }

  public void initiateShuffleDiscardIntoDeck() {
    System.out.println("initiateShuffleDiscardIntoDeck called");

    for(AbstractCard card : discardPile) {
      card.deckFromDiscardFlag = true;
    }
    shuffleDeck = true;
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
