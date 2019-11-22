package com.mygdx.game.examples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class TextLayoutTest extends ApplicationAdapter {
  static final String IMG_PATH = "images/cards/artwork/misc/samurai-woods.png";
  static final String EXAMPLE_TEXT = "Karplusan legend told of an orc so cruel that he burned his own followers in rage--yet so revered that they rose from their pyres to serve him.";
  static final int IMG_WIDTH = 200;
  static final int IMG_HEIGHT = 295;

  static final int TEXT_OFFSET_X = 5;
  static final int TEXT_OFFSET_Y = 5;

  SpriteBatch sb;
  Texture texture;

  int imgX = 10;
  int imgY = 10;

  @Override
  public void create () {
    sb = new SpriteBatch();
    texture = new Texture(Gdx.files.internal(IMG_PATH));
  }

  public void render() {
    final BitmapFont font = new BitmapFont();
    GlyphLayout gl = new GlyphLayout(font, EXAMPLE_TEXT, Color.RED,
        IMG_WIDTH - 2 * TEXT_OFFSET_X, Align.left, true);

    Gdx.gl.glClearColor(.2f, .2f, .2f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    sb.begin();

    sb.draw(texture, imgX, imgY);
    font.draw(sb, gl, imgX + TEXT_OFFSET_X, imgY + IMG_HEIGHT - TEXT_OFFSET_Y);
    sb.end();
  }
}
