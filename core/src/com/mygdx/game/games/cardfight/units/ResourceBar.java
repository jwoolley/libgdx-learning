package com.mygdx.game.games.cardfight.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.core.Hitbox;
import com.mygdx.game.core.Renderable;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.ui.fonts.FontUtil;

// TODO: add support for max-width and mid-width (and then normalize value)

public class ResourceBar implements Renderable {
  private static final int WIDTH_PER_RESOURCE_UNIT = 4;
  private static final int DEFAULT_BAR_HEIGHT = 14;
  private static final int DEFAULT_BAR_Y_OFFSET = 10;
  private static final Color DEFAULT_BACKDROP_COLOR = new Color(0.0f, 0.02f, 0.0f, 0.6f);

  private Color backdropColor;
  private Color barColor;
  private Color labelColor = Color.WHITE.cpy();
  private Color labelHighlightColor = Color.YELLOW.cpy();
  private int maxValue;
  private int value;
  private int barHeight;
  private int barYOffset;
  private Hitbox hitbox;
  private RelativePosition relativePosition;
  private boolean highlightFlag;

  enum RelativePosition {
    ABOVE,
    BELOW,
    CENTERED
  }

  // TODO: either track parent / parent hitbox OR parent add method for parent / group to update position

  public ResourceBar(int startingValue, Color barColor, Hitbox parentHitbox, RelativePosition relativePosition) {
    this(startingValue, barColor, parentHitbox, relativePosition, DEFAULT_BAR_HEIGHT, DEFAULT_BAR_Y_OFFSET);
  }

  public ResourceBar(int startingValue, Color barColor, Hitbox parentHitbox, RelativePosition relativePosition,
                     int barHeight, int barYOffset) {
    this.value = this.maxValue = startingValue;
    this.barColor = barColor.cpy();
    this.barHeight = barHeight;
    this.barYOffset = barYOffset;
    this.relativePosition = relativePosition;
    this.hitbox = calculateHitbox(parentHitbox);
    this.backdropColor = DEFAULT_BACKDROP_COLOR;
    this.highlightFlag = false;
  }

  private int getYPosition(Hitbox parentHitbox) {
    switch (relativePosition) {
      case ABOVE:
        return parentHitbox.getHeight() + getBarHeight() / 2 + getBarYOffset();
      case BELOW:
        return -getBarHeight() / 2 - getBarYOffset();
      case CENTERED:
      default:
          return  (parentHitbox.getHeight() - getBarHeight()) / 2;
    }
  }

  public void reposition(Hitbox parentHitbox) {
    this.hitbox = calculateHitbox(parentHitbox);
  }

  private Hitbox calculateHitbox(Hitbox parentHitbox) {
    final int xPos = parentHitbox.getXPosition() + (parentHitbox.getWidth() - getBarWidth()) / 2;
    final int yPos = parentHitbox.getYPosition() + getYPosition(parentHitbox);
    return new Hitbox(xPos, yPos, getBarWidth(), this.barHeight);
  }

  private int getBarWidth() {
    return maxValue * WIDTH_PER_RESOURCE_UNIT;
  }

  private int getBarHeight() {
    return barHeight;
  }

  private int getBarYOffset() {
    return barYOffset;
  }

  public void setValue(int amount) {
    this.value = amount;
  }

  private BitmapFont getLabelFont() {
    return CardFight.font;
  }

  protected String getLabelText() {
    return "" + value;
  }

  public void render(SpriteBatch sb) {
    render(sb, 1.0f);
  }

  public void highlight() {
    this.highlightFlag = true;
  }

  public void unhighlight() {
    this.highlightFlag = false;
  }

  // TODO: add shapes to global overlay batch instead of calling end/begin on SpriteBatch
  public void render(SpriteBatch sb, float objectScale) {
    sb.end();

    Gdx.gl.glEnable(GL20.GL_BLEND);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

    shapeRenderer.setColor(backdropColor);
    shapeRenderer.rect(hitbox.getXPosition(), hitbox.getYPosition(), hitbox.getWidth(), hitbox.getHeight());

    shapeRenderer.setColor(barColor);
    shapeRenderer.rect(hitbox.getXPosition(), hitbox.getYPosition(), this.value * WIDTH_PER_RESOURCE_UNIT,
        hitbox.getHeight());

    shapeRenderer.end();
    Gdx.gl.glDisable(GL20.GL_BLEND);

    sb.begin();
    BitmapFont labelFont = getLabelFont();
    labelFont.setColor(this.highlightFlag ? labelHighlightColor : labelColor);
    labelFont.draw(sb, getLabelText(),
        hitbox.getXPosition() + (hitbox.getWidth() - FontUtil.getTextWidth(labelFont, getLabelText())) / 2,
        hitbox.getYPosition() + (hitbox.getHeight() + labelFont.getCapHeight()) / 2);
  }
}