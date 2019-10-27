package com.mygdx.game.games.cardfight.units.monsters;

import com.mygdx.game.core.Hitbox;
import com.mygdx.game.games.cardfight.units.AbstractUnit;

public class AbstractMonster extends AbstractUnit {
  public static String MONSTER_IMAGE_PATH = "monsters/";
  public AbstractMonster(String key, String name, String imgPath, Hitbox hitbox) {
    super(key, name, MONSTER_IMAGE_PATH + imgPath, hitbox);
  }
}
