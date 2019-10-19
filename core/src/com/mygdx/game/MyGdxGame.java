package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;


	static final float circleRadius = 60.0f;
	float circleX = 200;
	float circleY = 100;

	float xSpeed = 180; // px per second
	float ySpeed = 120; // px per second

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		circleX += xSpeed * Gdx.graphics.getDeltaTime();
		circleY += ySpeed * Gdx.graphics.getDeltaTime();

		if (circleX < 0 + circleRadius|| circleX > Gdx.graphics.getWidth() - circleRadius) {
			xSpeed *= -1;
		}

		if (circleY < 0 + circleRadius || circleY > Gdx.graphics.getHeight() - circleRadius) {
			ySpeed *= -1;
		}

		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 0.25f, 1);
		shapeRenderer.circle(circleX, circleY, circleRadius);
		shapeRenderer.end();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}