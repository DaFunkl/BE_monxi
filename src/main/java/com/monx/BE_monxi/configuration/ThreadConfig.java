package com.monx.BE_monxi.configuration;

import com.monx.BE_monxi.game.tictactoe.TicTacToe_GameCleaner;

public class ThreadConfig {
	
	static TicTacToe_GameCleaner tttCleaner;
	
	public static void startTttCleaner() {
		tttCleaner = new TicTacToe_GameCleaner();
		tttCleaner.start();
	}
	
	public static void init() {
		startTttCleaner();
	}
}
