package com.mygdx.game.games.cardfight;

import com.mygdx.game.games.cardfight.battle.MonsterQueueItem;
import com.mygdx.game.games.cardfight.player.Player;
import com.mygdx.game.games.cardfight.screens.BattleScreen;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleManager {
  private BattleScreen screen;
  public BattleManager(BattleScreen screen) {
    this.screen = screen;
    monsterQueue = new ArrayList<>();
  }

  public List<AbstractMonster> getMonsters() {
    return screen.getMonsters();
  }

  private List<MonsterQueueItem> monsterQueue;

  public boolean isMonsterQueueEmpty() {
    return monsterQueue.isEmpty();
  }

  public MonsterQueueItem getNextMonsterFromQueue() {
    return monsterQueue.remove(0);
  }

  public void queueMonsterTurns() {
    monsterQueue.addAll(getMonsters().stream().map(m -> new MonsterQueueItem((m))).collect(Collectors.toList()));
  }

  public int getStartingHandSize() {
    // TODO: this could be modified by effects during combat, keep a combat-lifetime value
    //        (keep in an info data structure?)
    return Player.STARTING_HAND_SIZE;
  }

  public boolean allMonstersAreDefeated() {
    return getMonsters().stream().allMatch(AbstractMonster::isDefeated);
  }
}