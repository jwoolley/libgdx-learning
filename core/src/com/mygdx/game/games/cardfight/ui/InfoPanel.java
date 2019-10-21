package com.mygdx.game.games.cardfight.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.games.cardfight.utils.AssetUtil;

public class InfoPanel {
  private final String IMG_FILEPATH = "ui/status-bar.png";
  private Texture image;
  private int height;
  public InfoPanel() {
    this.image = AssetUtil.loadTexture(IMG_FILEPATH);
    this.height = CombatUi.INFO_BAR_HEIGHT;
  }

  public void render(SpriteBatch sb) {
    sb.draw(image, 0, height);
  }
}
