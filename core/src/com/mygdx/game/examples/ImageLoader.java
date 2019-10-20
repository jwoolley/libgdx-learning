package com.mygdx.game.examples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageLoader extends ApplicationAdapter {
  static final String IMG_PATH = "images/cards/artwork/misc/samurai-woods.png";
  static final int IMG_WIDTH = 200;
  static final int IMG_HEIGHT = 295;

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
    Gdx.gl.glClearColor(.2f, .2f, .2f, 1.0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    sb.begin();

    sb.draw(texture, imgX, imgY);

    sb.end();
  }
}
