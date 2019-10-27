package com.mygdx.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderable {
  void render(SpriteBatch sb);
  default void render(SpriteBatch sb, float objectScale) {};
}
