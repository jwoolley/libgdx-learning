package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class UserInputTest extends ApplicationAdapter {
	SpriteBatch sb;
	ShapeRenderer shapeRenderer;
	BitmapFont font;

	final Random RANDOM = new Random();


	float circleX = 200;
	float circleY = 100;

	float targetCircleX = 400;
	float targetCircleY = 300;

	static final float playerRadius = 30.0f;
	static final float targetStartRadius = 24.0f;

	static final float targetRadiusDelta = 0.25f;


	static final float DELTA_X_BASE = 3.0f;
	static final float DELTA_Y_BASE = 3.0f;

	static final float DELTA_X_SPEED_FACTOR = 1.5f;
	static final float DELTA_Y_SPEED_FACTOR = 1.5f;

	static float targetRadius = 24.0f;

	float deltaX = DELTA_X_BASE;
	float deltaY = DELTA_Y_BASE;

	static final float POINT_COLOR_DELTA = 0.002f;

	RGBColor playerColor = randomizedColor();
	RGBColor targetColor = randomizedColor();

	static final int UI_READOUT_Y_POS = 20;

	int score = 0;

	static final float MIN_TARGET_DISTANCE_FACTOR = 0.33f;
	int minTargetDistance;

	boolean firstFrame = true;

	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		sb = new SpriteBatch();
		font = new BitmapFont();

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char key) {
				if (key == 'c') {
					randomizeColor(playerColor);
				}
				return true;
			}

			@Override
			public boolean touchDown(int x, int y, int pointer, int button) {
//				randomizeColor(playerColor);
				return true;
			}
		});
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

		Gdx.gl.glClearColor(.20f, .20f, .20f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		boolean collision = false;
		if (playerMoved) {
			collision = circlesOverlap(circleX, circleY, playerRadius, targetCircleX, targetCircleY, targetRadius * 0.25f);
			if (collision) {
				System.out.println("Collision detected!");
				respawnTarget(circleX, circleY, playerRadius, targetStartRadius);
				score++;
			}
		}

		if (!collision) {
			if (targetRadius <= 1.0f) {
				respawnTarget(circleX, circleY, playerRadius, targetStartRadius);
			}	else {
				targetRadius -= targetRadiusDelta;
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

	private RGBColor updateColor(RGBColor currentColor) {
		currentColor.colorR = updateColorChannel(currentColor.colorR);
		currentColor.colorG = updateColorChannel(currentColor.colorG);
		currentColor.colorB = updateColorChannel(currentColor.colorB);
		return  currentColor;
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
		sb.dispose();
		font.dispose();
		shapeRenderer.dispose();
	}

	private float distance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2));
	}

	private boolean circlesOverlap(float c1_x, float c1_y, float c1_radius, float c2_x, float c2_y, float c2_radius) {
		return distance(c1_x, c1_y, c2_x, c2_y) <= c1_radius + c2_radius;
	}
}