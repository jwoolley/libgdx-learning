package com.mygdx.game.games.cardfight.cards;

public class SimpleDefend extends AbstractCard {
  public static final String CARD_KEY = "SoulBarrier";
  public static final String NAME = "Soul Barrier";
  public static final CardFrame FRAME = CardFrame.Default;
  public static final String IMG_PATH = "misc/basic-shield-1.png";

  public SimpleDefend() {
    super(CARD_KEY, NAME, FRAME, IMG_PATH);
  }
}