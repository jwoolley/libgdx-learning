package com.mygdx.game.games.cardfight.player;

public class PlayerInfo {
  private int score;

  public PlayerInfo() {
    this.score = 0;
  }

  public void resetGameInfo() {
    resetScore();
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void resetScore() {
    this.score = 0;
  }
}
