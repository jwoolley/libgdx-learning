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

public class TitleScreen extends ScreenAdapter {
  final CircleGameAlpha game;
  final ShapeRenderer shapeRenderer;
  final SpriteBatch sb;
  final BitmapFont font;

  public TitleScreen(CircleGameAlpha game) {
    this.game = game;
    this.shapeRenderer = this.game.shapeRenderer;
    this.sb = this.game.sb;
    this.font = this.game.font;
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(new InputAdapter() {
      public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.ESCAPE) {
          Gdx.app.exit();
        } else {
          game.setScreen(new GameScreen(game));
        }
        return true;
      }

      @Override
      public boolean touchDown(int x, int y, int pointer, int button) {
        game.setScreen(new GameScreen(game));
        return true;
      }
    });
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(0, .25f, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    sb.begin();
    font.draw(sb, "CIRCLE GAME ALPHA",Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
    font.draw(sb, "Eat circles to stay full. Don't starve!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
    font.draw(sb, "WASD to move", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .30f);
    font.draw(sb, "Press any key to play", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
    font.draw(sb, "Press ESC to quit", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .20f);
    sb.end();
  }

  @Override
  public void hide(){
    Gdx.input.setInputProcessor(null);
  }
}
