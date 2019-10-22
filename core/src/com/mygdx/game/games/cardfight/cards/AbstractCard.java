package com.mygdx.game.games.cardfight.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;

import java.util.HashMap;
import java.util.Map;

public class AbstractCard extends BaseCard {
  private static final String CARD_IMAGE_DIRECTORY = "images/cards/artwork/";
  private final String key;
  private final String name;
  private final CardFrame cardFrame;
  private Texture image;

  private final static Map<String, Texture> cardImageMap = new HashMap<>();

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;


  private static int SELECTED_NUDGE_DIST_X = -3;
  private static int SELECTED_NUDGE_DIST_Y = 3;

  private ScreenPosition nudgeDimensions = new ScreenPosition(0,0);

  private static final int DEFAULT_FRAME_X_INDENT = 10;

  public AbstractCard(String key, String name, CardFrame cardFrame, String imgPath) {
    super();
    this.key = key;
    this.name = name;
    this.cardFrame = cardFrame;

    if (!cardImageMap.containsKey(key)) {
      cardImageMap.put(key, new Texture(Gdx.files.internal(CARD_IMAGE_DIRECTORY + imgPath)));
    }
    this.image = cardImageMap.get(key);
  }

  @Override
  public void render(SpriteBatch sb, float objectScale) {
    renderImage(sb, objectScale);
    renderFrame(sb, objectScale);
    renderTitle(sb, objectScale);
    super.render(sb, objectScale);
  }

  private void renderImage(SpriteBatch sb, float objectScale) {
    sb.draw(image, xPos, yPos, getWidth() * objectScale, getHeight() * objectScale);
  }

  private void renderFrame(SpriteBatch sb, float objectScale) {
    sb.draw(cardFrame.getImage(), xPos, yPos, getWidth() * objectScale, getHeight()* objectScale);
  }

  private void renderTitle(SpriteBatch sb, float objectScale) {
    CardFight.font.draw(sb, name,
        xPos + DEFAULT_FRAME_X_INDENT,
        yPos + (DEFAULT_HEIGHT + (float) cardFrame.getTitleBarHeight() / 2 - 18) * objectScale);
  }

  @Override
  protected void setSelected() {
    super.setSelected();
    nudgeDimensions.x = SELECTED_NUDGE_DIST_X;
    nudgeDimensions.y = SELECTED_NUDGE_DIST_Y;
  }

  @Override
  protected void setUnselected() {
    super.setUnselected();
    nudgeDimensions.x = 0;
    nudgeDimensions.y = 0;
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