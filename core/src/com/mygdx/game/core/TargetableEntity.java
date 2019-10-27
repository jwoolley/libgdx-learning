package com.mygdx.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.ui.HoverableUiElement;

import java.util.HashMap;
import java.util.Map;

public class TargetableEntity implements HoverableUiElement {
    private static final String IMAGE_DIRECTORY = "images/";
    private final Color NAME_TEXT_COLOR = Color.YELLOW.cpy();

    private static final int DEFAULT_NAME_OFFSET_X = 140;
    public final static int DEFAULT_NAME_OFFSET_Y = 16;

    private final static Map<String, Texture> unitImageMap = new HashMap<>();

    public final String key;
    public final String name;
    public Hitbox hitbox;

    private Texture image;
    private boolean selected = false;

    private boolean renderNameFlag = false;

    public TargetableEntity(String key, String name, String imgPath, Hitbox hitbox) {
      this.key = key;
      this.name = name;
      this.hitbox = new Hitbox(hitbox);

      if (!unitImageMap.containsKey(key)) {
        unitImageMap.put(key, new Texture(Gdx.files.internal(IMAGE_DIRECTORY + imgPath)));
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

    public void update() {  }

    protected void setTargeted() {
      this.selected = true;
    }

    protected void setUntargeted() {
      this.selected = false;
    }
  }