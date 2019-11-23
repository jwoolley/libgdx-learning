package com.mygdx.game.games.cardfight.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.core.TargetableEntity;
import com.mygdx.game.core.Hitbox;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.ui.fonts.FontUtil;

abstract public class AbstractUnit extends TargetableEntity {
  private static final String UNIT_IMAGE_PATH = "units/";
  private final Color NAME_TEXT_COLOR = Color.YELLOW.cpy();

  public final static int DEFAULT_WIDTH = 200;
  public final static int DEFAULT_HEIGHT = 295;

  private static final int DEFAULT_NAME_OFFSET_X = 140;
  public final static int DEFAULT_NAME_OFFSET_Y = 16;

  private int maxHealth;
  private int health;

  private boolean renderNameFlag = false;

  public AbstractUnit(String key, String name, String imgPath, Hitbox hitbox) {
    this(key, name, imgPath, hitbox, -1);
  }

  public AbstractUnit(String key, String name, String imgPath, Hitbox hitbox, int startingHealth) {
    super(key, name, UNIT_IMAGE_PATH + imgPath, hitbox);
    this.health = this.maxHealth = startingHealth;
  }

  public void render(SpriteBatch sb, float objectScale) {
    super.render(sb, objectScale);
    if (renderNameFlag) {
      this.renderName(sb, 1.0f);
    }
  }

  public void update() {
    super.update();
    renderNameFlag = this.isHovered();
  }

  private void renderName(SpriteBatch sb, float objectScale) {
    BitmapFont font = CardFight.font;
    font.setColor(NAME_TEXT_COLOR);
    font.draw(sb, name,
        this.hitbox.getXPosition() + (getWidth() - FontUtil.getTextWidth(CardFight.font, name)) / 2,
        getYPosition() + getHeight() + DEFAULT_NAME_OFFSET_Y);
  }

  public void setTargeted() {
    super.setTargeted();
    System.out.println("Unit targeted: " + this.getClass().getSimpleName());
  }

  public void setUntargeted() {
    super.setUntargeted();
    System.out.println("Unit untargeted: " + this.getClass().getSimpleName());
  }
}