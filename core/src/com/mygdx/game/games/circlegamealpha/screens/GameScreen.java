package com.mygdx.game.games.circlegamealpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.games.circlegamealpha.CircleGameAlpha;
import com.mygdx.graphics.RGBColor;

import java.util.Random;

public class GameScreen extends ScreenAdapter {
  final CircleGameAlpha game;
  final ShapeRenderer shapeRenderer;
  final SpriteBatch sb;
  final BitmapFont font;

  public GameScreen(CircleGameAlpha game) {
    this.game = game;
    this.shapeRenderer = this.game.shapeRenderer;
    this.sb = this.game.sb;
    this.font = this.game.font;
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
          randomizeColor(playerColor);
        } else if (key == 'r') {
          game.setScreen(new GameScreen(game));
          return true;
        }
        return true;
      }

      @Override
      public boolean touchDown(int x, int y, int pointer, int button) {
        return true;
      }
    });
  }

  final Random RANDOM = new Random();

  float circleX = 200;
  float circleY = 100;

  float targetCircleX = 400;
  float targetCircleY = 300;
  static final float playerStartRadius = 30.0f;
  static final float targetStartRadius = 24.0f;

  static final float targetRadiusDelta = 0.25f;

  static final float playerRadiusStartingDelta = 0.10f;
  static float playerRadiusDelta = playerRadiusStartingDelta;


  static final float DELTA_X_BASE = 3.0f;
  static final float DELTA_Y_BASE = 3.0f;

  static final float DELTA_X_SPEED_FACTOR = 1.5f;
  static final float DELTA_Y_SPEED_FACTOR = 1.5f;

  float playerRadius = playerStartRadius;
  static float targetRadius = targetStartRadius;

  float deltaX = DELTA_X_BASE;
  float deltaY = DELTA_Y_BASE;

  static final float POINT_COLOR_DELTA = 0.002f;

  RGBColor playerColor = randomizedColor();
  RGBColor targetColor = randomizedColor();

  static final int UI_READOUT_Y_POS = 20;

  int score = 0;

  int minTargetDistance;

  @Override
  public void render (float delta) {
    boolean playerMoved = false;
    if (Gdx.input.isTouched()) {
      circleX = Math.min(Math.max(Gdx.input.getX(), playerRadius), Gdx.graphics.getWidth() - playerRadius);
      circleY = Gdx.graphics.getHeight() - Math.min(Math.max(Gdx.input.getY(), playerRadius), Gdx.graphics.getHeight() - playerRadius);
      playerMoved = true;
    }

    final boolean isRunning =
        Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT);

    if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) && circleY + playerRadius < Gdx.graphics.getHeight()) {
      circleY += deltaY * (isRunning ? DELTA_Y_SPEED_FACTOR : 1.0f);
      playerMoved = true;
    } else if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) && circleY - playerRadius > 0) {
      circleY -= deltaY * (isRunning ? DELTA_Y_SPEED_FACTOR : 1.0f);
      playerMoved = true;
    }

    if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) && circleX - playerRadius > 0) {
      circleX -= deltaX * (isRunning ? DELTA_X_SPEED_FACTOR : 1.0f);
      playerMoved = true;
    } else if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) && circleX + playerRadius < Gdx.graphics.getWidth()) {
      circleX += deltaX * (isRunning ? DELTA_X_SPEED_FACTOR : 1.0f);
      playerMoved = true;
    }

    Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    boolean collision = false;
    if (playerMoved) {
      collision = circlesOverlap(circleX, circleY, playerRadius, targetCircleX, targetCircleY, targetRadius * 0.25f);
      if (collision) {
        playerRadius += targetRadius;
        respawnTarget(circleX, circleY, playerRadius, targetStartRadius);
        score++;
      }
    }

    if (!collision) {
      if (targetRadius <= 1.0f) {
        respawnTarget(circleX, circleY, playerRadius, targetStartRadius);
      }	else {
        targetRadius -= targetRadiusDelta;
        playerRadius -= playerRadiusDelta;

        if (playerRadius <= 1.0f) {
          gameOver();
        }
      }
    }

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

    shapeRenderer.setColor(targetColor.colorR, targetColor.colorG, targetColor.colorB, targetColor.colorA);
    shapeRenderer.circle(targetCircleX, targetCircleY, targetRadius);

    shapeRenderer.setColor(playerColor.colorR, playerColor.colorG, playerColor.colorB, playerColor.colorA);
    shapeRenderer.circle(circleX, circleY, playerRadius);

    shapeRenderer.end();

    sb.begin();
    font.draw(sb, "Circles Eaten  -  " + score, Gdx.graphics.getWidth() / 2 - 50, UI_READOUT_Y_POS);
    sb.end();
  }

  private void startGame() {
    game.playerInfo.resetScore();
  }

  private void gameOver() {
    game.playerInfo.setScore(score);
    game.setScreen(new EndScreen(game));
  }

  private void respawnTarget(float _playerX, float _playerY, float _playerRadius, float _targetStartRadius) {
    float newTargetX;
    float newTargetY;
    do {
      newTargetX = _playerRadius + RANDOM.nextInt((int) (Gdx.graphics.getWidth() - (_playerRadius + targetStartRadius)));
      newTargetY = _playerRadius + RANDOM.nextInt((int) (Gdx.graphics.getHeight() - (_playerRadius + targetStartRadius)));
    } while (distance(_playerX, _playerY, newTargetX, newTargetY) < _playerRadius + _targetStartRadius + minTargetDistance);

    targetCircleX = newTargetX;
    targetCircleY = newTargetY;
    targetColor = randomizedColor();
    targetRadius = _targetStartRadius;
  }

  private RGBColor randomizedColor() {
    return new RGBColor(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
  }

  private RGBColor randomizeColor(RGBColor currentColor) {
    currentColor.colorR = RANDOM.nextFloat();
    currentColor.colorG = RANDOM.nextFloat();
    currentColor.colorB = RANDOM.nextFloat();
    return currentColor;
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

  private float distance(float x1, float y1, float x2, float y2) {
    return (float) Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));
  }

  private boolean circlesOverlap(float c1_x, float c1_y, float c1_radius, float c2_x, float c2_y, float c2_radius) {
    return distance(c1_x, c1_y, c2_x, c2_y) <= c1_radius + c2_radius;
  }
}
