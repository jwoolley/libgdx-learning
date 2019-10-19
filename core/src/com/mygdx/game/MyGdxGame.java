package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch sb;
	BitmapFont font;

	@Override
	public void create () {
		sb = new SpriteBatch();
		font = new BitmapFont();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		font.draw(sb, "Welcome to my world. Would you like to be a moderator?", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		sb.end();
	}
	
	@Override
	public void dispose () {
		sb.dispose();
		font.dispose();
	}
}
