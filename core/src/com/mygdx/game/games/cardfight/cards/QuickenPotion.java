package com.mygdx.game.games.cardfight.cards;

public class QuickenPotion extends AbstractCard {
  public static final String CARD_KEY = "QuickenPotion";
  public static final String NAME = "Quicklime";
  public static final CardFrame FRAME = CardFrame.Default;
  public static final String IMG_PATH = "misc/green-potion-1.png";

  public QuickenPotion() {
    super(CARD_KEY, NAME, FRAME, IMG_PATH);
  }
}