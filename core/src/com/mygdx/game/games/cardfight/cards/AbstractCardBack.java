package com.mygdx.game.games.cardfight.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class AbstractCardBack extends BaseCard {
  public final static int DEFAULT_WIDTH = 156;
  public final static int DEFAULT_HEIGHT = 230;

  private static final String CARD_IMAGE_DIRECTORY = "images/cards/backs/";
  private final String key;
  private Texture image;

  private final static Map<String, Texture> cardBackImageMap = new HashMap<>();

  public AbstractCardBack(String key, String imgPath) {
    super();
    this.key = key;

    if (!cardBackImageMap.containsKey(key)) {
      cardBackImageMap.put(key, new Texture(Gdx.files.internal(CARD_IMAGE_DIRECTORY + imgPath)));
    }
    this.image = cardBackImageMap.get(key);
  }

  @Override
  public void render(SpriteBatch sb) {
    renderImage(sb);
    super.render(sb);
  }

  public int getWidth() {
    return DEFAULT_WIDTH;
  }
  public int getHeight() {
    return DEFAULT_HEIGHT;
  }

  public void onClick() { }
  public void onClickRelease() {}

  private void renderImage(SpriteBatch sb) {
    sb.draw(image, xPos, yPos);
  }
}
