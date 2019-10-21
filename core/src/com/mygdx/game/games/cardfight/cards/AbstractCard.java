package com.mygdx.game.games.cardfight.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.player.Player;

import java.util.HashMap;
import java.util.Map;

public class AbstractCard {
  private static final String CARD_IMAGE_DIRECTORY = "images/cards/artwork/";
  private static final String CARD_FRAME_IMAGE_DIRECTORY = "images/cards/frames/";

  private final String key;
  private final String name;
  private final CardFrame cardFrame;
  private Texture image;
  private Texture frame;

  public int xPos = 0;
  public int yPos = 0;

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  private final static Map<String, Texture> cardImageMap = new HashMap<>();

  private static final String DEFAULT_FRAME_FILENAME = "custom-frame-1.png";
  enum CardFrame {
    Default(DEFAULT_FRAME_FILENAME);

    CardFrame(String imgPath) {
      this.imgPath = imgPath;
    }

    public Texture getImage() {
      if (this.img == null) {
        this.img = new Texture(Gdx.files.internal(CARD_FRAME_IMAGE_DIRECTORY + this.imgPath));
      }
      return this.img;
    }

    private final String imgPath;
    private Texture img;
  }

  public AbstractCard(String key, String name, CardFrame cardFrame, String imgPath) {
    this.key = key;
    this.name = name;
    this.cardFrame = cardFrame;

    if (!cardImageMap.containsKey(key)) {
      cardImageMap.put(key, new Texture(Gdx.files.internal(CARD_IMAGE_DIRECTORY + imgPath)));
    }
    this.image = cardImageMap.get(key);
  }

  public void render(SpriteBatch sb) {
    renderImage(sb);
    renderFrame(sb);
  }

  private void renderImage(SpriteBatch sb) {
    sb.draw(image, xPos, yPos);
  }

  private void renderFrame(SpriteBatch sb) {
    sb.draw(cardFrame.getImage(), xPos, yPos);
  }
}
