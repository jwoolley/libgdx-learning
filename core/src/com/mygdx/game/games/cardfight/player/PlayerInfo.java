package com.mygdx.game.games.cardfight.player;

public class PlayerInfo {
  private static final int DEFAULT_MAX_HEALTH = 50;
  private int maxHealth;
  private int health;
  private int score;

  public PlayerInfo() {
    this.maxHealth = this.health = DEFAULT_MAX_HEALTH;
    this.score = 0;
  }

  public void resetGameInfo() {
    resetScore();
  }

  public int getScore() {
    return score;
  }

  public void increaseHealth(int amount) {
    health += amount;
  }

  public void decreaseHealth(int amount) {
    health -= amount;
  }

  public void increaseMaxHealth(int amount) {
    maxHealth += amount;
  }

  public void decreaseMaxHealth(int amount) {
    maxHealth -= amount;
  }

  public int getHealth() {
    return health;
  }

  public int getMaxHealth() {
    return maxHealth;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void resetScore() {
    this.score = 0;
  }
}
