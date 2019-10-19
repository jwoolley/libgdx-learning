package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;


	final Random RANDOM = new Random();

	static final float circleRadius = 1.5f;
	float circleX = 200;
	float circleY = 100;
//
//	float xSpeed = 60; // px per second
//	float ySpeed = 60; // px per second

	float xSpeed = 1; // px per second
	float ySpeed = 1; // px per second

	static final int SWITCH_TICK_COOLDOWN = 5;
	int xSwitchTickCountdown = 0;
	int ySwitchTickCountdown = 0;

	static final float POINT_COLOR_DELTA = 0.025f;
	float pointColorR = 0.5f;
	float pointColorG = 0.5f;
	float pointColorB = 0.5f;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
//		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render () {
//		circleX += xSpeed * Gdx.graphics.getDeltaTime();
//		circleY += ySpeed * Gdx.graphics.getDeltaTime();
//
//		if (circleX < 0 + circleRadius|| circleX > Gdx.graphics.getWidth() - circleRadius) {
//			xSpeed *= -1;
//		}
//
//		if (circleY < 0 + circleRadius || circleY > Gdx.graphics.getHeight() - circleRadius) {
//			ySpeed *= -1;
//		}
//
//		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(1, 1, 0.25f, 1);
//		shapeRenderer.circle(circleX, circleY, circleRadius);
//		shapeRenderer.end();


//		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (xSwitchTickCountdown == 0 && (circleX < 0 + circleRadius|| circleX > Gdx.graphics.getWidth() - circleRadius)) {
			xSpeed *= -1;
			xSwitchTickCountdown = SWITCH_TICK_COOLDOWN;
		} else if (RANDOM.nextBoolean()) {
			xSpeed *= -1;
		}

		if (ySwitchTickCountdown == 0 && (circleY < 0 + circleRadius || circleY > Gdx.graphics.getHeight() - circleRadius)) {
			ySpeed *= -1;
			ySwitchTickCountdown = SWITCH_TICK_COOLDOWN;
		} else if (RANDOM.nextBoolean()) {
			ySpeed *= -1;
		}

		circleX += xSpeed;
		circleY += ySpeed;

		pointColorR = updateColorChannel(pointColorR);
		pointColorG = updateColorChannel(pointColorG);
		pointColorB = updateColorChannel(pointColorB);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(pointColorR, pointColorG, pointColorB, 1);
		shapeRenderer.circle(circleX, circleY, circleRadius);
//		shapeRenderer.point(circleX, circleY, 0);
		shapeRenderer.end();


		if (xSwitchTickCountdown > 0) {
			xSwitchTickCountdown--;
		}
		if (ySwitchTickCountdown > 0) {
			ySwitchTickCountdown--;
		}
	}

	private float updateColorChannel(float currentValue) {

		boolean increaseValue = RANDOM.nextBoolean() ;

		if (currentValue <= 0.0f) {
			increaseValue = true;
		} else if (currentValue >= 1.0f) {
			increaseValue = false;
		}

		return currentValue + (increaseValue ? POINT_COLOR_DELTA : -POINT_COLOR_DELTA);
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}