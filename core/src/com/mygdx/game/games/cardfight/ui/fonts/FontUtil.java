package com.mygdx.game.games.cardfight.ui.fonts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class FontUtil {
  // TODO: memoize these
  public static float getTextWidth(BitmapFont font, String text) {
    GlyphLayout layout = new GlyphLayout(font, text);
    return layout.width;
  }
}
