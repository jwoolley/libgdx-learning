package com.mygdx.game.games.cardfight.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.core.Updatable;
import com.mygdx.game.games.cardfight.BattleManager;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.GameManager;
import com.mygdx.game.games.cardfight.ui.fonts.FontUtil;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractButton implements HoverableUiElement, Updatable {
  private static final String UI_IMAGE_DIRECTORY = "images/ui/";
  private final String key;
  private final String label;
  private Texture image;

  private final static Map<String, Texture> buttonMap = new HashMap<>();

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;
  private boolean selected = false;

  public int xPos = 0;
  public int yPos = 0;
  public int width = 0;
  public int height = 0;
  public boolean canNudge = true;

  private static int SELECTED_NUDGE_DIST_X = 1;
  private static int SELECTED_NUDGE_DIST_Y = -1;

  private ScreenPosition nudgeDimensions = new ScreenPosition(0,0);

  public AbstractButton(String key, String label, String imgPath) {
    super();
    this.key = key;
    this.label = label;

    if (!buttonMap.containsKey(key)) {
      buttonMap.put(key, new Texture(Gdx.files.internal(UI_IMAGE_DIRECTORY + imgPath)));
    }
    this.image = buttonMap.get(key);
  }

  public BitmapFont getLabelFont() {
    return CardFight.font;
  }

  public void render(SpriteBatch sb) {
    render(sb, 1.0f);
  }

  public void render(SpriteBatch sb, float objectScale) {
    ScreenPosition nudge = getNudgeDimensions();
    xPos += nudge.x;
    yPos += nudge.y;

    renderImage(sb, objectScale);
    renderText(sb, objectScale);

    xPos -= nudge.x;
    yPos -= nudge.y;
  }

  private void renderImage(SpriteBatch sb, float objectScale) {
    sb.draw(image, xPos, yPos, getWidth() * objectScale, getHeight() * objectScale);
  }

  public int getXPosition() {
    return xPos;
  }

  public int getYPosition() {
    return yPos;
  }

  public float getTextWidth() {
    return FontUtil.getTextWidth(getLabelFont(), label);
  }

  private void renderText(SpriteBatch sb, float objectScale) {
    CardFight.font.draw(sb, label,
        xPos + (getWidth() -  getTextWidth()) / 2,
        yPos + ((float)getHeight()/2 + (float)CardFight.getTextHeight(CardFight.font)/2) * objectScale);
  }

  abstract public void use();

  protected void setSelected() {
    System.out.println("AbstractButton::setTargeted called for  " + this.getClass().getSimpleName());

    this.selected = true;
    if (canNudge) {
      nudgeDimensions.x = SELECTED_NUDGE_DIST_X;
      nudgeDimensions.y = SELECTED_NUDGE_DIST_Y;
    }
  }

  protected void setUnselected() {
    System.out.println("AbstractButton::setUntargeted called for  " + this.getClass().getSimpleName());

    this.selected = false;
    if (canNudge) {
      nudgeDimensions.x = 0;
      nudgeDimensions.y = 0;
    }

    use();
  }

  public int getWidth() {
    return DEFAULT_WIDTH;
  }
    public int getHeight() {
    return DEFAULT_HEIGHT;
  }

   public ScreenPosition getNudgeDimensions() {
    return nudgeDimensions;
  }

  public void update() {
    handleClick();
  }

  protected void handleClick() {
    if (isHovered() && !selected && CardFight.mouseButtonDown && CardFight.mouseButtonStateChanged) {
      setSelected();
    } else if (selected && !CardFight.mouseButtonDown) {
      setUnselected();
    }
  }

  protected static GameManager getGameManager() {
    return CardFight.gameManager;
  }
  protected static BattleManager getBattleManager() {
    return CardFight.gameManager.getBattleManager();
  }

  public void onClick() {}

  public void onClickRelease() {}
}