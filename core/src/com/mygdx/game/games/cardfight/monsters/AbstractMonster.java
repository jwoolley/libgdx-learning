package com.mygdx.game.games.cardfight.monsters;

import com.mygdx.game.games.cardfight.Hitbox;

public class AbstractMonster extends AbstractUnit {
  public static String MONSTER_IMAGE_PATH = "monsters/";
  public AbstractMonster(String key, String name, String imgPath, Hitbox hitbox) {
    super(key, name, MONSTER_IMAGE_PATH + imgPath, hitbox);
  }
}
