package com.mygdx.game.games;

import com.mygdx.game.games.cardfight.screens.BattleScreen;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

import java.util.Collections;
import java.util.List;

public class StupidClass {
    public void StupidClass(BattleScreen screen) {
      this.screen = screen;
    }

    public void setScreen(BattleScreen screen) {
      this.screen = screen;
    }

    public boolean isBattleScene() {
      // TODO: check with BattleScreen
      return true;
    }

    public List<AbstractMonster> getMonsters() {
      if (!isBattleScene()) {
        return Collections.emptyList();
      }
      return screen.getMonsters();

    }

    private BattleScreen screen;
}
