package com.mygdx.game.games.cardfight.actions;

import com.mygdx.game.actions.GameAction;
import com.mygdx.game.games.cardfight.CardFight;

public class DamagePlayerAction extends GameAction {
  private int amount;

  public DamagePlayerAction(int amount) {
    this.amount = amount;
  }

  public DamagePlayerAction(int amount, float duration) {
    this(amount);
    this.duration = duration;
  }

  @Override
  public void update() {
    System.out.println(this.getClass().getSimpleName() + ".update() called. duration: " + this.duration);
    if (this.duration <= 0.0f) {
      CardFight.playSound("SFX_STRIKE_BLUNT_1");
      CardFight.playSound("SFX_GRUNT_PAIN_1");
      CardFight.player.playerInfo.decreaseHealth(this.amount);
      this.isDone = true;
    } else {
      this.tick();
    }
  }
}