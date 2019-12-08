package com.mygdx.game.games.cardfight;

import com.mygdx.game.games.cardfight.screens.BattleScreen;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

import java.util.List;

public class BattleManager {
  private BattleScreen screen;
  public BattleManager(BattleScreen screen) {
    this.screen = screen;
  }

  public List<AbstractMonster> getMonsters() {
    return screen.getMonsters();
  }
}
