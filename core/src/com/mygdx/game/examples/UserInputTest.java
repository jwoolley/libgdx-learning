package com.mygdx.game.examples;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.graphics.RGBColor;

import java.util.Random;

public class UserInputTest extends ApplicationAdapter {
	ShapeRenderer shapeRenderer;


	final Random RANDOM = new Random();


	float circleX = 200;
	float circleY = 100;

	static final float circleRadius = 30.0f;
	static final float deltaX = 3.0f;
	static final float deltaY = 3.0f;

//	float xSpeed = 1; // px per second
//	float ySpeed = 1; // px per second

//	static final int SWITCH_TICK_COOLDOWN = 5;
//	int xSwitchTickCountdown = 0;
//	int ySwitchTickCountdown = 0;

	static final float POINT_COLOR_DELTA = 0.0025f;
	RGBColor shapeColor = randomizedColor();

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
//
//		if (xSwitchTickCountdown == 0 && (circleX < 0 + circleRadius|| circleX > Gdx.graphics.getWidth() - circleRadius)) {
//			xSpeed *= -1;
//			xSwitchTickCountdown = SWITCH_TICK_COOLDOWN;
//		} else if (RANDOM.nextBoolean()) {
//			xSpeed *= -1;
//		}
//
//		if (ySwitchTickCountdown == 0 && (circleY < 0 + circleRadius || circleY > Gdx.graphics.getHeight() - circleRadius)) {
//			ySpeed *= -1;
//			ySwitchTickCountdown = SWITCH_TICK_COOLDOWN;
//		} else if (RANDOM.nextBoolean()) {
//			ySpeed *= -1;
//		}
//
//		circleX += xSpeed;
//		circleY += ySpeed;


		if (Gdx.input.isTouched()) {
			randomizeColor(shapeColor);
			circleX = Gdx.input.getX();
			circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}

		if ((Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) && circleY + circleRadius < Gdx.graphics.getHeight()) {
			updateColor(shapeColor);
			circleY += deltaY;
		} else if ((Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) && circleY - circleRadius > 0) {
			updateColor(shapeColor);
			circleY -= deltaY;
		}

		if ((Gdx.input.isKeyJustPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) && circleX - circleRadius > 0) {
			circleX -= deltaX;
			updateColor(shapeColor);
		} else if ((Gdx.input.isKeyJustPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) && circleX + circleRadius < Gdx.graphics.getWidth()) {
			circleX += deltaX;
			updateColor(shapeColor);
		}

		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(shapeColor.colorR, shapeColor.colorG, shapeColor.colorB, shapeColor.colorA);
		shapeRenderer.circle(circleX, circleY, circleRadius);
		shapeRenderer.end();

//		if (xSwitchTickCountdown > 0) {
//			xSwitchTickCountdown--;
//		}
//		if (ySwitchTickCountdown > 0) {
//			ySwitchTickCountdown--;
//		}
	}

	private RGBColor updateColor(RGBColor currentColor) {
		currentColor.colorR = updateColorChannel(currentColor.colorR);
		currentColor.colorG = updateColorChannel(currentColor.colorG);
		currentColor.colorB = updateColorChannel(currentColor.colorB);
		return  currentColor;
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

	private RGBColor randomizeColorOneChannel(RGBColor currentColor) {
		if (RANDOM.nextBoolean()) {
			if (RANDOM.nextBoolean()) {
				currentColor.colorR = RANDOM.nextFloat();
			} else {
				currentColor.colorG = RANDOM.nextFloat();
			}
		} else {
			if (RANDOM.nextBoolean()) {
				currentColor.colorB = RANDOM.nextFloat();
			} else {
				return randomizeColorOneChannel(currentColor);
			}
		}
		return currentColor;
	}

	private RGBColor updateOneColorChannel(RGBColor currentColor) {

		if (RANDOM.nextBoolean()) {
			if (RANDOM.nextBoolean()) {
				currentColor.colorR = updateColorChannel(currentColor.colorR);
			} else {
				currentColor.colorG = updateColorChannel(currentColor.colorG);
			}
		} else {
			if (RANDOM.nextBoolean()) {
				currentColor.colorB = updateColorChannel(currentColor.colorB);
			} else {
				currentColor = randomizeColorOneChannel(currentColor);
			}
		}
		return currentColor;
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