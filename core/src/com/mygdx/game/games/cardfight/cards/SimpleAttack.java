package com.mygdx.game.games.cardfight.cards;

public class SimpleAttack extends AbstractCard {
  public static final String CARD_KEY = "SimpleAttack";
  public static final String NAME = "Bold Slash";
  public static final CardFrame FRAME = CardFrame.Default;
  public static final String IMG_PATH = "misc/axe-swing-1.png";

  public SimpleAttack() {
    super(CARD_KEY, NAME, FRAME, IMG_PATH);
  }
}