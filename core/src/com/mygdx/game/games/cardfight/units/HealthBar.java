package com.mygdx.game.games.cardfight.units;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.core.Hitbox;
import com.mygdx.game.core.Renderable;

public class HealthBar extends ResourceBar implements Renderable {
  public static final Color BAR_COLOR = Color.SCARLET.cpy();
  public static final RelativePosition RELATIVE_POSITION = RelativePosition.BELOW;

  public HealthBar(int startingHealth, Hitbox unitHitbox) {
    super(startingHealth, BAR_COLOR, unitHitbox, RELATIVE_POSITION);
  }
}