package com.mygdx.game.games.cardfight.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.player.PlayerInfo;
import com.mygdx.game.games.cardfight.ui.combat.CombatUi;
import com.mygdx.game.games.cardfight.utils.AssetUtil;

public class InfoPanel {
  private static final String IMG_FILEPATH = "ui/status-bar.png";
  private final Color HEALTH_INFO_COLOR = Color.SCARLET;

  private static final int HEALTH_X_OFFSET = 24;
  private static final int HEALTH_Y_OFFSET = 5;

  private Texture image;
  private int height;
  public InfoPanel() {
    this.image = AssetUtil.loadTexture(IMG_FILEPATH);
    this.height = CombatUi.INFO_BAR_HEIGHT;
  }

  public void render(SpriteBatch sb) {
    final PlayerInfo info = CardFight.player.playerInfo;
    final BitmapFont font = CardFight.font;

    sb.draw(image, 0, 0);
    font.setColor(HEALTH_INFO_COLOR);
    font.draw(sb, "Health: " + info.getHealth() + " / " + info.getMaxHealth(), HEALTH_X_OFFSET, this.height / 2 + HEALTH_Y_OFFSET);
  }
}
