package com.monx.BE_monxi.game.tictactoe;

import com.monx.BE_monxi.configuration.GeneralConfig;

public class TicTacToe_GameCleaner extends Thread{
	boolean exit = false; 
	
	@Override
	public void run() {
		while (!exit) {
			TicTacToe_Manager.removeTimedOutGames();
			// only a BandAid solution,
			// since only a few games are allowed and 
			// time out function doesn't need to be 
			// precise it's ok
			
			// if timing needs to be more precise
			// this has to be implemented cleaner
			try {
				sleep(GeneralConfig.THREAD_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void exit() {
		exit = true;
	}
}
