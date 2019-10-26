package com.mygdx.game.games.cardfight.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.Hitbox;
import com.mygdx.game.games.cardfight.ui.HoverableUiElement;

import java.util.HashMap;
import java.util.Map;

abstract public class AbstractUnit implements HoverableUiElement {
  private static final String UNIT_IMAGE_DIRECTORY = "images/units/";

//  private static Texture targetBorder;
  private boolean selected = false;

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  private final static Map<String, Texture> unitImageMap = new HashMap<>();

  public final String key;
  public final String name;
  private Texture image;
  public Hitbox hitbox;

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
  }

  private void renderImage(SpriteBatch sb, float objectScale) {
    sb.draw(image, getXPosition(), getYPosition(), getWidth() * objectScale, getHeight() * objectScale);
  }

  public void update() { }


  protected void setTargeted() {
    this.selected = true;
  }

  protected void setUntargeted() {
    this.selected = false;
  }
}