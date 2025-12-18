package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.Logger;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceManager;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.Screens;
import com.mygdx.screens.ScreensManager;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

public class GarbageCollection extends Game {

	private static GarbageCollection instance;
	private ResourceManager manager;

	public GarbageCollection(){
		instance = this;
	}

	public static GarbageCollection getInstance() {
		return instance;
	}

	public ResourceManager getManager() {
		return manager;
	}

	@Override
	public void create() {
		manager = new ResourceManager();

		SavingsManager.loadDefaultIfNeeded();
		SavingsManager.load();
		StateManager.updateBoolState(StateEnum.IS_ENTERING, false);
		StateManager.updateBoolState(StateEnum.IS_EXITING, false);
		StateManager.updateStringState(StateEnum.DESTINATION, "");
		Logger.init();
		
		setScreen(ScreensManager.getScreen(Screens.MENU_SCREEN));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		RM.get().dispose();
	}
}
