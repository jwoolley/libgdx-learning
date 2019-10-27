package com.mygdx.game.games.cardfight.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.core.Renderable;
import com.mygdx.game.core.Updatable;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

public class BattleScene implements Updatable, Renderable {

  public static final int MONSTER_DEFAULT_Y = 750;

  final MonsterGroup monsterGroup;

  public BattleScene() {
    this(new MonsterGroup());
  }

  public BattleScene(MonsterGroup group) {
    this.monsterGroup = new MonsterGroup(group);
  }

  public void addMonster(AbstractMonster monster, int x, int y) {
    monster.hitbox.setPosition(x, y);
    this.monsterGroup.addMonster(monster);
  }

  public void addMonster(AbstractMonster monster) {
    addMonster(monster, (Gdx.graphics.getWidth() - monster.hitbox.getWidth())/ 2, MONSTER_DEFAULT_Y - monster.hitbox.getHeight());
  }

  @Override
  public void update() {
    for (AbstractMonster monster : monsterGroup.monsters) {
      monster.update();
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    render(sb, 1.0f);
  }

  @Override
  public void render(SpriteBatch sb, float objectScale) {
    for (AbstractMonster monster : monsterGroup.monsters) {
      monster.render(sb, objectScale);
    }
  }
}
