package com.mygdx.game.games.cardfight;

import com.mygdx.game.games.cardfight.screens.BattleScreen;

public class GameManager {
  private static BattleManager battleManager;

  private static ScreenType screenType;

  public GameManager(BattleScreen screen) {
    this.screen = screen;
  }

  public void setScreen(BattleScreen screen) {
    this.screen = screen;
  }

  public void startBattle() {
    this.battleManager = new BattleManager(this.screen);
    screenType = ScreenType.BATTLE;
  }

  public void endBattle() {
    screenType = ScreenType.MAP;
  }

  private BattleScreen screen;

  public BattleManager getBattleManager() {
    return battleManager;
  }

  static {
    screenType = ScreenType.MAP;
  }
}
