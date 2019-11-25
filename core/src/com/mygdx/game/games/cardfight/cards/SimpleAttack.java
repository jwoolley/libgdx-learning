package com.mygdx.game.games.cardfight.cards;

import com.mygdx.game.games.cardfight.types.DamageType;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

public class SimpleAttack extends AbstractTargetedCard {
  public static final String CARD_KEY = "SimpleAttack";
  public static final String NAME = "Swipe";
  public static final CardFrame FRAME = CardFrame.Default;
  public static final String IMG_PATH = "misc/axe-swing-1.png";

  private static final int DAMAGE = 8;
  private static final DamageType DAMAGE_TYPE = DamageType.PHYSICAL;

  private static final String DESCRIPTION = "Deal #D# damage to target enemy.";

  public SimpleAttack() {
    super(CARD_KEY, NAME, FRAME, IMG_PATH, DESCRIPTION);
    this.damage = this.baseDamage = DAMAGE;
  }

  public void use() {
    super.use();

    if (hasTarget()) {
      AbstractMonster target = getTarget();

      // TODO: this should be a time-based action
      target.applyDamage(this.damage, DAMAGE_TYPE);
    }
  }
}