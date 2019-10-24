package com.mygdx.game.games.cardfight.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.core.Updatable;
import com.mygdx.game.games.cardfight.CardFight;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractButton implements ClickableUiElement, Updatable {
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

//  private static final String DEFAULT_GLOW_BORDER_FILENAME = "glow-border-1.png";
//  private static final int DEFAULT_GLOW_BORDER_MARGIN_SIZE = 10;

  private static int SELECTED_NUDGE_DIST_X = 1;
  private static int SELECTED_NUDGE_DIST_Y = -1;

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

  abstract public int getTextWidth();

  private void renderText(SpriteBatch sb, float objectScale) {
    CardFight.font.draw(sb, text,
        xPos + (float)getWidth() / 2 - getTextWidth(),
        yPos + ((float)getHeight()/2 + (float)CardFight.getTextHeight(CardFight.font)/2) * objectScale);
  }

  abstract public void use();

  protected void setSelected() {
    System.out.println("AbstractButton::setSelected called for  " + this.getClass().getSimpleName());

    this.selected = true;
    if (canNudge) {
      nudgeDimensions.x = SELECTED_NUDGE_DIST_X;
      nudgeDimensions.y = SELECTED_NUDGE_DIST_Y;
    }
  }

  protected void setUnselected() {
    System.out.println("AbstractButton::setUnselected called for  " + this.getClass().getSimpleName());

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


  public void onClick() {}

  public void onClickRelease() {}
}