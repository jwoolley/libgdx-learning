package com.mygdx.game.games.cardfight.units.monsters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.core.Hitbox;
import com.mygdx.game.games.cardfight.units.AbstractUnit;
import com.mygdx.game.games.cardfight.units.HealthBar;

public class AbstractMonster extends AbstractUnit {
  public static String MONSTER_IMAGE_PATH = "monsters/";

  private int maxHealth;
  private int health;

  private final HealthBar healthBar;

  public AbstractMonster(String key, String name, String imgPath, Hitbox hitbox, int startingHealth) {
    super(key, name, MONSTER_IMAGE_PATH + imgPath, hitbox);
    this.health = this.maxHealth = startingHealth;
    this.healthBar = new HealthBar(startingHealth, this.hitbox);
  }

  @Override
  public void render(SpriteBatch sb, float objectScale) {
    super.render(sb, objectScale);
    healthBar.render(sb, objectScale);
  }

  @Override
  public void update() {
    super.update();
    if (this.isHovered()) {
      this.healthBar.highlight();
    } else {
      this.healthBar.unhighlight();
    }
  }

  public void reposition(int x, int y) {
    this.hitbox.setPosition(x, y);
    this.healthBar.reposition(this.hitbox);
  }

  public int getHealth() {
    return health;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public void increaseHealth(int amount) {
    final int netAmount = Math.min(amount, maxHealth - health);
    this.health += netAmount;
    this.healthBar.setValue(this.health);
  }

  public void decreaseHealth(int amount) {
    final int netAmount = Math.min(health, amount);
    this.health -= netAmount;
    this.healthBar.setValue(this.health);

    if (health <= 0) {
      onHealthDepleted();
    }
  }

  public void onHealthDepleted() {};
}
