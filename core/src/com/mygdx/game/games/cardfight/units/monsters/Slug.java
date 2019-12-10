package com.mygdx.game.games.cardfight.units.monsters;

import com.mygdx.game.core.Hitbox;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.actions.DamagePlayerAction;

public class Slug extends AbstractMonster {
  public static final String KEY = "Unit:Slug";
  public static final String NAME = "Blood Slug";
  public static final String IMG_PATH = "blood-slug.png";

  private static int WIDTH = 345;
  private static int HEIGHT = 300;

  public static int STARTING_HEALTH = 60;

  public Slug(){
    super(KEY, NAME, IMG_PATH, new Hitbox(0,0, WIDTH, HEIGHT), STARTING_HEALTH);
  }

  private int getAttackDamage() {
    final int BASE_ATTACK_DAMAGE = 6;
    return BASE_ATTACK_DAMAGE;
  }

  @Override
  public void takeTurn() {
    System.out.println(this.getClass().getSimpleName() + ".takeTurn() called");
    // TODO: enqueue attack, etc. as an action
    CardFight.actionManager.addToBack(new DamagePlayerAction(getAttackDamage(), 0.2f));
  }
}
