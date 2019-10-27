package com.mygdx.game.games.cardfight.battle;

import com.mygdx.game.games.cardfight.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonsterGroup {
  List<AbstractMonster> monsters;

  public MonsterGroup() {
    this.monsters = new ArrayList<>();
  }

  public MonsterGroup(MonsterGroup monsterGroup) {
    this.monsters = new ArrayList<>(monsterGroup.monsters);
  }

  public void addMonster(AbstractMonster monster) {
    this.monsters.add(monster);
  }

  public List<AbstractMonster> getMonsters() {
    return Collections.unmodifiableList(monsters);
  }
}