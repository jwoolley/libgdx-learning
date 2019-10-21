package com.mygdx.game.games.cardfight.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.cards.AbstractCard;
import com.mygdx.game.games.cardfight.cards.HealingPotion;
import com.mygdx.game.games.cardfight.cards.QuickenPotion;
import com.mygdx.game.games.cardfight.cards.SimpleAttack;
import com.mygdx.game.games.cardfight.ui.CombatUi;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;

import java.util.ArrayList;
import java.util.List;

public class Player {

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


    for (AbstractCard c : hand) {
      ScreenPosition nudge = c.getNudgeDimensions();
      c.xPos += nudge.x;
      c.yPos += nudge.y;
      c.render(sb);
    }
  }

  public void dealHand() {
    final List<AbstractCard> startingHand = new ArrayList<>();
    startingHand.add(new HealingPotion());
    startingHand.add(new HealingPotion());
    startingHand.add(new QuickenPotion());
    this.hand.clear();
    this.hand.addAll(startingHand);
  }
}
