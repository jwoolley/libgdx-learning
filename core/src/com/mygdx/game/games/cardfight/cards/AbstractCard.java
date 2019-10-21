package com.mygdx.game.games.cardfight.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.player.Player;

import javax.smartcardio.Card;
import java.util.HashMap;
import java.util.Map;

public class AbstractCard {
  private static final String CARD_IMAGE_DIRECTORY = "images/cards/artwork/";
  private static final String CARD_FRAME_IMAGE_DIRECTORY = "images/cards/frames/";
  private static final String CARD_BORDER_IMAGE_DIRECTORY = "images/cards/borders/";

  private final String key;
  private final String name;
  private final CardFrame cardFrame;
  private Texture image;
  private Texture frame;
  private static Texture glowBorder;

  public int xPos = 0;
  public int yPos = 0;

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  private final static Map<String, Texture> cardImageMap = new HashMap<>();

  private static final String DEFAULT_GLOW_BORDER_FILENAME = "glow-border-1.png";
  private static final int DEFAULT_GLOW_BORDER_MARGIN_SIZE = 10;

  private static final String DEFAULT_FRAME_FILENAME = "custom-frame-1.png";
  private static final int DEFAULT_FRAME_TITLEBAR_HEIGHT = 22;

  private static final int DEFAULT_FRAME_X_INDENT = 10;

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

    private final String imgPath;
    private int titleBarHeight;
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

    if (glowBorder == null) {
      glowBorder =  new Texture(Gdx.files.internal(CARD_BORDER_IMAGE_DIRECTORY + DEFAULT_GLOW_BORDER_FILENAME));
    }
  }

  public void render(SpriteBatch sb) {
    renderImage(sb);
    renderFrame(sb);
    renderTitle(sb);
    renderGlow(sb);
  }

  private void renderImage(SpriteBatch sb) {
    sb.draw(image, xPos, yPos);
  }


  public boolean isHovered() {
    final int mouseX = CardFight.getMouseX();
    final int mouseY = CardFight.getMouseY();

    return mouseX >= xPos
        && mouseX <= xPos + DEFAULT_WIDTH
        && mouseY >= CardFight.getScreenHeight() - (yPos + DEFAULT_HEIGHT)
        && mouseY <= CardFight.getScreenHeight() - yPos;
  }

  private void renderGlow(SpriteBatch sb) {
    if (isHovered()) {
      sb.draw(glowBorder, xPos - DEFAULT_GLOW_BORDER_MARGIN_SIZE, yPos - DEFAULT_GLOW_BORDER_MARGIN_SIZE);
    }
  }

  private void renderFrame(SpriteBatch sb) {
    sb.draw(cardFrame.getImage(), xPos, yPos);
  }

  private void renderTitle(SpriteBatch sb) {
    CardFight.font.draw(sb, name, xPos + DEFAULT_FRAME_X_INDENT, yPos + DEFAULT_HEIGHT + cardFrame.titleBarHeight / 2 - 18);
//    sb.draw(image, xPos, yPos);
  }
}