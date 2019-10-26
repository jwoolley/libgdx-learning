package com.mygdx.game.games.cardfight.monsters;

import com.mygdx.game.games.cardfight.Hitbox;

public class Slug extends  AbstractMonster {
  public static final String KEY = "Unit:Slug";
  public static final String NAME = "Blood Slug";
  public static final String IMG_PATH = "blood-slug.png";

  private static int WIDTH = 400;
  private static int HEIGHT = 350;

  public Slug(){
    super(KEY, NAME, IMG_PATH, new Hitbox(0,0, WIDTH, HEIGHT));
  }
}
