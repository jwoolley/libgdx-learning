package com.mygdx.game.games.cardfight.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractButton {
  private static final String UI_IMAGE_DIRECTORY = "images/ui/";
  private final String key;
  private final String text;
  private Texture image;

  private final static Map<String, Texture> buttonMap = new HashMap<>();

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;
  private static Texture glowBorder;
  private boolean selected = false;

  public int xPos = 0;
  public int yPos = 0;
  public int width = 0;
  public int height = 0;
  public boolean canNudge = true;

  private static final String DEFAULT_GLOW_BORDER_FILENAME = "glow-border-1.png";
  private static final int DEFAULT_GLOW_BORDER_MARGIN_SIZE = 10;

  private static final String DEFAULT_FRAME_FILENAME = "custom-frame-1.png";
  private static final int DEFAULT_FRAME_TITLEBAR_HEIGHT = 22;

  private static int SELECTED_NUDGE_DIST_X = -3;
  private static int SELECTED_NUDGE_DIST_Y = 3;

  private ScreenPosition nudgeDimensions = new ScreenPosition(0,0);

  public AbstractButton(String key, String text, String imgPath) {
    super();
    this.key = key;
    this.text = text;

    if (!buttonMap.containsKey(key)) {
      buttonMap.put(key, new Texture(Gdx.files.internal(UI_IMAGE_DIRECTORY + imgPath)));
    }
    this.image = buttonMap.get(key);
  }

  public void render(SpriteBatch sb, float objectScale) {
    renderImage(sb, objectScale);
    renderText(sb, objectScale);
  }

    private void renderImage(SpriteBatch sb, float objectScale) {
    sb.draw(image, xPos, yPos, getWidth() * objectScale, getHeight() * objectScale);
  }

  abstract public int getTextWidth();

  public int getTextHeight() {
    return (int)CardFight.font.getLineHeight();
  };
    private void renderText(SpriteBatch sb, float objectScale) {
    final int textLengthInPx = 40;
    CardFight.font.draw(sb, text,
        xPos + getWidth() / 2 - textLengthInPx,
        yPos + (DEFAULT_HEIGHT + (float)getTextHeight() / 2) * objectScale);
  }

  abstract public void use();

  protected void setSelected() {
    this.selected = true;
    if (canNudge) {
      nudgeDimensions.x = SELECTED_NUDGE_DIST_X;
      nudgeDimensions.y = SELECTED_NUDGE_DIST_Y;
    }
  }

  protected void setUnselected() {
    this.selected = false;
    if (canNudge) {
      nudgeDimensions.x = 0;
      nudgeDimensions.y = 0;
    }
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
}