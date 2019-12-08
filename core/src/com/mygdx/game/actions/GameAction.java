package com.mygdx.game.actions;

import com.badlogic.gdx.Gdx;

public abstract class GameAction {
  public static final float DEFAULT_DURATION = 0.5f; // TODO: should be multiple of gdxDeltaTime

  private float duration;
  public boolean isDone;

  public GameAction() {
    this(DEFAULT_DURATION);
  }

  public GameAction(float duration) {
    this.duration = duration;
    this.isDone = false;
  }

  abstract public void update();

  protected void tick() {
    this.duration -= Gdx.graphics.getDeltaTime();
    if (this.duration < 0.0f) {
      this.isDone = true;
    }
  }
}
