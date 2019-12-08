package com.mygdx.game.actions;

import java.util.ArrayList;
import java.util.List;

public class ActionManager {
  private List<GameAction> actionQueue;
  private GameAction currentAction;

  public ActionManager() {
    this.actionQueue = new ArrayList<>();
  }

  public void addToFront(GameAction action) {
    actionQueue.add(0, action);
  }

  public void addToBack(GameAction action) {
    actionQueue.add(action);
  }

  public void update() {
    // TODO: logic to determine screen, room, phase, etc.
    if (hasCurrentAction() && currentAction.isDone) {
      clearAction();
    }

    if (hasCurrentAction() || !actionQueue.isEmpty()) {
      if (!hasCurrentAction()) {
        currentAction = getNextAction();
      }
      currentAction.update();
    }
  }

  private GameAction getNextAction() {
    return actionQueue.remove(0);
  }

  private boolean hasCurrentAction() {
    return currentAction != null;
  }

  private void clearAction() {
    this.currentAction = null;
  }
}