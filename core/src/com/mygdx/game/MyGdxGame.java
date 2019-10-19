package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
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

	static final float POINT_COLOR_DELTA = 0.00175f;
	RGBColor shapeColor = new RGBColor(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat(), 1.0f);

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
//		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	class RGBColor{
		public RGBColor() {
			this(0.0f,0.0f,0.0f);
		}

		public RGBColor(float r, float g, float b) {
			this(r, g, b,1.0f);
		}

		public RGBColor(float r, float g, float b, float a) {
			this.colorR = r;
			this.colorG = g;
			this.colorB = b;
			this.colorA = a;
		}

		float colorR;
		float colorG ;
		float colorB;
		float colorA;
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
			circleX = Gdx.input.getX();
			circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}

		if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) && circleY + circleRadius < Gdx.graphics.getHeight()) {
			circleY += deltaY;
			updateColor(shapeColor);
		} else if ((Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) && circleY - circleRadius > 0) {
			circleY -= deltaY;
			updateColor(shapeColor);
		}

		if ((Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) && circleX - circleRadius > 0) {
			circleX -= deltaX;
			updateColor(shapeColor);
		} else if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) && circleX + circleRadius < Gdx.graphics.getWidth()) {
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