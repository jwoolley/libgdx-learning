package com.mygdx.game.games.cardfight.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetUtil {
  public final static String IMG_DIR_PATH = "images/";
  public static Texture loadTexture(String imgPath) {
    return new Texture(Gdx.files.internal(IMG_DIR_PATH + imgPath));
  }
}
