package com.mygdx.game.games.cardfight.ui;

import com.mygdx.game.core.Renderable;
import com.mygdx.game.games.cardfight.CardFight;

public interface HoverableUiElement extends Renderable {
  int getXPosition();
  int getYPosition();
  int getWidth();
  int getHeight();

  default public boolean isHovered() {
    final int mouseX = CardFight.getMouseX();
    final int mouseY = CardFight.getMouseY();

    return mouseX >= getXPosition()
        && mouseX <= getXPosition() + getWidth()
        && mouseY >= CardFight.getScreenHeight() - (getYPosition() + getHeight())
        && mouseY <= CardFight.getScreenHeight() - getYPosition();
  }
}