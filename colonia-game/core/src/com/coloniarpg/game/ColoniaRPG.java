package com.coloniarpg.game;

import com.badlogic.gdx.Game;
import com.coloniarpg.game.screens.MainMenuScreen;

public class ColoniaRPG extends Game {
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
	}
}