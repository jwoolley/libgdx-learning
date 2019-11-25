package com.mygdx.game.games.cardfight.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.ui.ScreenPosition;
import com.mygdx.game.games.cardfight.utils.TextUtil.SymbolSubstitutions;

import java.util.HashMap;
import java.util.Map;

public class AbstractCard extends BaseCard {
  private static final String CARD_IMAGE_DIRECTORY = "images/cards/artwork/";
  private static final Color CARD_TITLE_COLOR = Color.WHITE.cpy();
  private static final Color CARD_TEXT_COLOR = Color.WHITE.cpy();

  private static final String DESCRIPTION_PLACEHOLDER = "No description specified.";

  public final String key;
  private final String name;
  private final CardFrame cardFrame;
  private Texture image;
  private String description = "";

  private final static Map<String, Texture> cardImageMap = new HashMap<>();

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  public final static int DEFAULT_DESCRIPTION_INDENT_X = 22;
  public final static int DEFAULT_DESCRIPTION_INDENT_Y = 105;
  public final static int DEFAULT_DESCRIPTION_WIDTH = 154;

  public final static int  DEFAULT_TEXT_BOX_HEIGHT = 185;

  private static int SELECTED_NUDGE_DIST_X = -3;
  private static int SELECTED_NUDGE_DIST_Y = 3;

  private ScreenPosition nudgeDimensions = new ScreenPosition(0,0);

  private static final int DEFAULT_FRAME_X_INDENT = 10;


  protected int baseDamage = 0;
  protected int damage = 0;

  protected int baseMagicNumber = 0;
  protected int magicNumber = 0;

  public AbstractCard(String key, String name, CardFrame cardFrame, String imgPath) {
    this(key, name, cardFrame, imgPath, DESCRIPTION_PLACEHOLDER);
  }

  public AbstractCard(String key, String name, CardFrame cardFrame, String imgPath, String description) {
    super();
    this.key = key;
    this.name = name;
    this.cardFrame = cardFrame;

    if (!cardImageMap.containsKey(key)) {
      cardImageMap.put(key, new Texture(Gdx.files.internal(CARD_IMAGE_DIRECTORY + imgPath)));
    }
    this.image = cardImageMap.get(key);
    this.description = description;
  }

  @Override
  public void render(SpriteBatch sb, float objectScale) {
    renderImage(sb, objectScale);
    renderFrame(sb, objectScale);
    renderTitle(sb, objectScale);
    renderDescription(sb, objectScale);
    super.render(sb, objectScale);
  }

  private void renderImage(SpriteBatch sb, float objectScale) {
    sb.draw(image, xPos, yPos, getWidth() * objectScale, getHeight() * objectScale);
  }

  private void renderFrame(SpriteBatch sb, float objectScale) {
    sb.draw(cardFrame.getImage(), xPos, yPos, getWidth() * objectScale, getHeight()* objectScale);
  }

  private void renderTitle(SpriteBatch sb, float objectScale) {
    BitmapFont font = CardFight.font;
    font.setColor(CARD_TITLE_COLOR);
    font.draw(sb, name,
        xPos + DEFAULT_FRAME_X_INDENT,
        yPos + (DEFAULT_HEIGHT + (float) cardFrame.getTitleBarHeight() / 2 - 18) * objectScale);
  }

  private void renderDescription(SpriteBatch sb, float objectScale) {
    BitmapFont font = CardFight.font;
    GlyphLayout gl = new GlyphLayout(font, getResolvedDescription(), CARD_TEXT_COLOR,
        DEFAULT_DESCRIPTION_WIDTH * objectScale, Align.center, true);

    float textXPos = xPos + DEFAULT_DESCRIPTION_INDENT_X;
    float textYPos = yPos + (DEFAULT_DESCRIPTION_INDENT_Y * objectScale);

    if (gl.height < DEFAULT_TEXT_BOX_HEIGHT * objectScale) {
      textYPos -= ((DEFAULT_TEXT_BOX_HEIGHT - DEFAULT_DESCRIPTION_INDENT_Y - gl.height) * objectScale) / 2;
    }

    font.draw(sb, gl, textXPos, textYPos);
  }

  public String getDescription() {
    return this.description;
  }

  protected String getResolvedDescription() {
    String updated = SymbolSubstitutions.DAMAGE.substitute(getDescription(), "" + this.damage);
    updated = SymbolSubstitutions.MAGIC_NUMBER.substitute(updated, "" + this.magicNumber);
    return updated;
  }

  @Override
  public void onSetSelected() {
    CardFight.playSound("SFX_UI_CLICK_2");
  }

  public void use() {
    super.use();
    discardFlag = true;
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