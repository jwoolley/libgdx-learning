package com.mygdx.game.games.cardfight.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.Hitbox;
import com.mygdx.game.games.cardfight.ui.HoverableUiElement;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractUnit implements HoverableUiElement {
  private static final String UNIT_IMAGE_DIRECTORY = "images/units/";
  private final Color NAME_TEXT_COLOR = Color.YELLOW.cpy();

//  private static Texture targetBorder;
  private boolean selected = false;

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  private static final int DEFAULT_NAME_OFFSET_X = 140;
  public final static int DEFAULT_NAME_OFFSET_Y = 16;

  private final static Map<String, Texture> unitImageMap = new HashMap<>();

  public final String key;
  public final String name;
  private Texture image;
  public Hitbox hitbox;

  private boolean renderNameFlag = false;

  public AbstractUnit(String key, String name, String imgPath, Hitbox hitbox) {
    this.key = key;
    this.name = name;
    this.hitbox = new Hitbox(hitbox);

    if (!unitImageMap.containsKey(key)) {
      unitImageMap.put(key, new Texture(Gdx.files.internal(UNIT_IMAGE_DIRECTORY + imgPath)));
    }

    this.image = unitImageMap.get(key);
  }

  public int getWidth() {
    return hitbox.getWidth();
  }

  public int getHeight() {
    return hitbox.getHeight();
  }

  public int getXPosition() {
    return hitbox.getXPosition();
  }

  public int getYPosition() {
    return hitbox.getYPosition();
  }

  public void render(SpriteBatch sb) {
    this.render(sb, 1.0f);
  }

  public void render(SpriteBatch sb, float objectScale) {
    renderImage(sb, objectScale);
    if (renderNameFlag) {
      this.renderName(sb, 1.0f);
    }
  }

  private void renderImage(SpriteBatch sb, float objectScale) {
    sb.draw(image, getXPosition(), getYPosition(), getWidth() * objectScale, getHeight() * objectScale);
  }

  public void update() {
    renderNameFlag = this.isHovered();
  }

  private int getTextWidth(String text) {
    return DEFAULT_NAME_OFFSET_X;
  }

  private void renderName(SpriteBatch sb, float objectScale) {
    BitmapFont font = CardFight.font;
    font.setColor(NAME_TEXT_COLOR);
    font.draw(sb, name,
        this.hitbox.getXPosition() + (float)(getWidth() - getTextWidth(name)) / 2,
        getYPosition() + getHeight() + DEFAULT_NAME_OFFSET_Y);
  }

  protected void setTargeted() {
    this.selected = true;
  }

  protected void setUntargeted() {
    this.selected = false;
  }
}