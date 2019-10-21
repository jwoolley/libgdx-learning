package com.mygdx.game.games.cardfight.cards;

public class HealingPotion extends AbstractCard {
  public static final String CARD_KEY = "HealingPotion";
  public static final String NAME = "Heal";
  public static final CardFrame FRAME = CardFrame.Default;
  public static final String IMG_PATH = "misc/red-potion-1.png";

  public HealingPotion() {
    super(CARD_KEY, NAME, FRAME, IMG_PATH);
  }
}