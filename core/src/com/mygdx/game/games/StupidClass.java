package com.mygdx.game.games;

import com.mygdx.game.games.cardfight.screens.GameScreen;
import com.mygdx.game.games.cardfight.units.monsters.AbstractMonster;

import java.util.Collections;
import java.util.List;

public class StupidClass {
    public void StupidClass(GameScreen screen) {
      this.screen = screen;
    }

    public void setScreen(GameScreen screen) {
      this.screen = screen;
    }

    public boolean isBattleScene() {
      // TODO: check with GameScreen
      return true;
    }

    public List<AbstractMonster> getMonsters() {
      if (!isBattleScene()) {
        return Collections.emptyList();
      }
      return screen.getMonsters();

    }

    private GameScreen screen;
}
