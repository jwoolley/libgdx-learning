package com.mygdx.game.games.cardfight.cards;

import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

import java.util.List;

public class AbstractTargetedCard extends AbstractCard {
  public AbstractTargetedCard(String key, String name, CardFrame cardFrame, String imgPath, String description) {
    super(key, name, cardFrame, imgPath, description);
  }

  protected List<AbstractMonster> getMonsters() {
    return CardFight.gameManager.getMonsters();
  }

  protected boolean hasTarget() {
    return getMonsters().stream().anyMatch(m -> m.isHovered());
  }

  protected boolean isLegalTarget(AbstractMonster monster) {
    return true;
  }

  public boolean canUse() {
    return hasTarget() && isLegalTarget(getTarget());
  }

  protected AbstractMonster getTarget() {
    if (hasTarget()) {
      AbstractMonster monster = getMonsters().stream().findFirst().get();
        return monster;
    }
    //TODO: return NoMonster (extends AbstractMonster, throws exceptions on method calls
    return null;
  }
}
