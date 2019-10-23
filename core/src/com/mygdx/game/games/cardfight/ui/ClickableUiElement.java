package com.mygdx.game.games.cardfight.ui;

import com.mygdx.game.games.cardfight.CardFight;

public interface ClickableUiElement {
  void onClick();
  void onClickRelease();
  int getXPosition();
  int getYPosition();
  int getWidth();
  int getHeight();

  default public boolean isHovered() {
    final int mouseX = CardFight.getMouseX();
    final int mouseY = CardFight.getMouseY();
//
//    System.out.println("\n\nClickableUiElement::called for  " + this.getClass().getSimpleName()
//      + "\n\tmouseX: " + mouseX
//      + "\n\tgetXPosition(): " + getXPosition()
//      + "\n\tgetWidth(): " + getWidth()
//      + "\n\tgetXPosition() + getWidth(): " + (getXPosition() + getWidth())
//      + "\n\tmouseY: " + mouseY
//      + "\n\tgetYPosition(): " + getYPosition()
//      + "\n\tgetHeight(): " + getHeight()
//      + "\n\tgetScreenHeight(): " + CardFight.getScreenHeight()
//      + "\n\tgetYPosition() + getHeight(): " + (getYPosition() + getHeight())
//      + "\n\tgetScreenHeight() - (getYPosition() + getHeight()): " + (CardFight.getScreenHeight() - (getYPosition() + getHeight()))
//      + "\n\tgetScreenHeight() - getYPosition(): " + (CardFight.getScreenHeight() - getYPosition())
//    );
//
//    boolean rightOfLeftBound =  mouseX >= getXPosition();
//    boolean leftOfRightBound =   mouseX <= getXPosition() + getWidth();
//    boolean aboveLowerBound =  mouseY >= CardFight.getScreenHeight() - (getYPosition() + getHeight());
//    boolean belowUpperBound =   mouseY <= CardFight.getScreenHeight() - getYPosition();
//    System.out.println(""
//        + "\n\trightOfLeftBound: " + rightOfLeftBound
//        + "\n\tleftOfRightBound: " + leftOfRightBound
//        + "\n\taboveLowerBound: " + aboveLowerBound
//        + "\n\tbelowUpperBound: " + belowUpperBound
//    );

    return mouseX >= getXPosition()
        && mouseX <= getXPosition() + getWidth()
        && mouseY >= CardFight.getScreenHeight() - (getYPosition() + getHeight())
        && mouseY <= CardFight.getScreenHeight() - getYPosition();
  }
}
