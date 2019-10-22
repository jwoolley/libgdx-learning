package com.mygdx.game.games.cardfight.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;

abstract public class BaseCard {
  private static final String CARD_FRAME_IMAGE_DIRECTORY = "images/cards/frames/";
  private static final String CARD_BORDER_IMAGE_DIRECTORY = "images/cards/borders/";

  private static Texture glowBorder;
  private boolean selected = false;

  public int xPos = 0;
  public int yPos = 0;

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  private static final String DEFAULT_GLOW_BORDER_FILENAME = "glow-border-1.png";
  private static final int DEFAULT_GLOW_BORDER_MARGIN_SIZE = 10;

  private static final String DEFAULT_FRAME_FILENAME = "custom-frame-1.png";
  private static final int DEFAULT_FRAME_TITLEBAR_HEIGHT = 22;

  static boolean cardSelected = false;

  enum CardFrame {
    Default(DEFAULT_FRAME_FILENAME, DEFAULT_FRAME_TITLEBAR_HEIGHT);

    CardFrame(String imgPath, int titleBarHeight) {
      this.imgPath = imgPath;
      this.titleBarHeight = titleBarHeight;
    }

    public Texture getImage() {
      if (this.img == null) {
        this.img = new Texture(Gdx.files.internal(CARD_FRAME_IMAGE_DIRECTORY + this.imgPath));
      }
      return this.img;
    }

    public int getTitleBarHeight() {
      return titleBarHeight;
    }

    private final String imgPath;
    private int titleBarHeight;
    private Texture img;
  }

  public BaseCard() {
    if (glowBorder == null) {
      glowBorder =  new Texture(Gdx.files.internal(CARD_BORDER_IMAGE_DIRECTORY + DEFAULT_GLOW_BORDER_FILENAME));
    }
  }

  abstract public int getWidth();
  abstract public int getHeight();

  public void render(SpriteBatch sb) {
    this.render(sb, 1.0f);
  }

  public void render(SpriteBatch sb, float objectScale) {
    renderGlow(sb, objectScale);

    // TODO: this should go in an update() method, not render()
    handleClick();
  }

  protected void handleClick() {
    if (isHovered() && !cardSelected && !selected && CardFight.mouseButtonDown && CardFight.mouseButtonStateChanged) {
      setSelected();
    } else if (selected && !CardFight.mouseButtonDown) {
      setUnselected();
    }
  }

  protected void setSelected() {
    this.selected = true;
    cardSelected = true;
  }

  protected void setUnselected() {
    this.selected = false;
    cardSelected = false;
  }

  public boolean isHovered() {
    final int mouseX = CardFight.getMouseX();
    final int mouseY = CardFight.getMouseY();

    return mouseX >= xPos
        && mouseX <= xPos + getWidth()
        && mouseY >= CardFight.getScreenHeight() - (yPos + getHeight())
        && mouseY <= CardFight.getScreenHeight() - yPos;
  }

  private void renderGlow(SpriteBatch sb, float objectScale) {
    if (isHovered() && !cardSelected || selected) {
      System.out.println("BaseCard::renderGlow sb: " + sb + "; objectScale: " + objectScale);

      float xScale = objectScale * getWidth() / DEFAULT_WIDTH;
      float yScale = objectScale *  getHeight() / DEFAULT_HEIGHT;
       sb.draw(glowBorder, xPos - DEFAULT_GLOW_BORDER_MARGIN_SIZE * xScale,
      yPos - DEFAULT_GLOW_BORDER_MARGIN_SIZE * yScale,
           objectScale * (getWidth() + xScale * 2 * DEFAULT_GLOW_BORDER_MARGIN_SIZE),
           objectScale * (getHeight() + yScale * 2 * DEFAULT_GLOW_BORDER_MARGIN_SIZE));
    }
  }
}