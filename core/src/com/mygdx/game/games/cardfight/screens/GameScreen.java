package com.mygdx.game.games.cardfight.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.games.cardfight.CardFight;
import com.mygdx.game.games.cardfight.utils.AssetUtil;

public class GameScreen extends ScreenAdapter {
  private final String BACKGROUND_IMG_DIR = "background/1600x1000/";
  private final String BACKGROUND_IMG_FILENAME_1 = "basic-village-1.jpg";
  private Texture backgroundImage;

  final CardFight game;
  final ShapeRenderer shapeRenderer;
  final SpriteBatch sb;
  final BitmapFont font;

  public GameScreen(CardFight game) {
    this.game = game;
    this.shapeRenderer = this.game.shapeRenderer;
    this.sb = this.game.sb;
    this.font = this.game.font;

    backgroundImage = loadBackgroundImage(BACKGROUND_IMG_FILENAME_1);

    startGame();
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(new InputAdapter() {
      public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.ESCAPE) {
          Gdx.app.exit();
        }
        return true;
      }

      @Override
      public boolean keyTyped(char key) {
        if (key == 'c') {
        } else if (key == 'r') {
          game.setScreen(new GameScreen(game));
          return true;
        }
        return true;
      }

      @Override
      public boolean touchDown(int x, int y, int pointer, int button) {
        CardFight.mouseButtonDown = !CardFight.mouseButtonDown;
        CardFight.mouseButtonDown = true;
        return true;
      }

      @Override
      public boolean touchUp(int x, int y, int pointer, int button) {
        CardFight.mouseButtonStateChanged = CardFight.mouseButtonDown;
        CardFight. mouseButtonDown = false;
        return true;
      }
    });
  }

  int score = 0;

  @Override
  public void render (float delta) {
    game.updateMousePosition(Gdx.input.getX(), Gdx.input.getY());

    Gdx.input.isTouched();

    Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    sb.begin();

    sb.draw(backgroundImage, 0, 0);

    CardFight.combatUi.render(sb);
    sb.end();
  }

  private void startGame() {
    game.player.playerInfo.resetScore();
    game.player.dealHand();
  }

  private void gameOver() {
    CardFight.player.playerInfo.setScore(score);
    game.setScreen(new EndScreen(game));
  }

  @Override
  public void dispose () {
    sb.dispose();
    font.dispose();
    shapeRenderer.dispose();
  }

  @Override
  public void hide() {
    Gdx.input.setInputProcessor(null);
  }

  private Texture loadBackgroundImage(String fileName) {
    return AssetUtil.loadTexture(BACKGROUND_IMG_DIR + fileName);
  }

  private float distance(float x1, float y1, float x2, float y2) {
    return (float) Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));
  }

  private boolean circlesOverlap(float c1_x, float c1_y, float c1_radius, float c2_x, float c2_y, float c2_radius) {
    return distance(c1_x, c1_y, c2_x, c2_y) <= c1_radius + c2_radius;
  }
}
