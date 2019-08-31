package com.monx.BE_monxi.game.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.monx.BE_monxi.configuration.GeneralConfig;
import com.monx.BE_monxi.functions.SecureRandomString;
import com.monx.BE_monxi.models.basic.Vec2;
import com.monx.BE_monxi.models.tictactoe.TicTacToeBoard;

public class TicTacToe_Manager {
	static Map<String, TicTacToe> openGames = new HashMap<>();

	public static List<TicTacToeBoard> getAllBoards() {
		List<TicTacToeBoard> ret = new ArrayList<>();
		for (String id : openGames.keySet()) {
			TicTacToe ttt = openGames.get(id);
			if (ttt != null) {
				ret.add(ttt.toTttBoard());
			}
		}
		return ret;
	}

	public static String requestGame() {
		if (openGames.size() >= GeneralConfig.MAX_OPEN_GAMES) {
			return null;
		}
		String gameToken = SecureRandomString.generate();
		if (!openGames.containsKey(gameToken)) {
			openGames.put(gameToken, new TicTacToe());
			return gameToken;
		}
		for (int i = 0; i < GeneralConfig.MAX_TRIES_GENERATING_TOKEN; i++) {
			gameToken = SecureRandomString.generate();
			if (!openGames.containsKey(gameToken)) {
				openGames.put(gameToken, new TicTacToe());
				return gameToken;
			}
		}
		return null;
	}

	public static int resetGames() {
		openGames.clear();
		return openGames.size();
	}

	public static TicTacToe getGame(String key) {
		return openGames.get(key);
	}

	public static boolean gameExists(String gameId) {
		return openGames.containsKey(gameId);
	}

	public static boolean makeMove(String id, Vec2<Integer> move) {
		return openGames.get(id).pTurn(move);
	}

	public static com.monx.BE_monxi.models.tictactoe.TicTacToeBoard getBoard(String id) {
		return openGames.get(id).toTttBoard();
	}

	public static void removeTimedOutGames() {
		for (String id : openGames.keySet()) {
			long lastMove = openGames.get(id).lastMove;
			if (didGameTimedOut(lastMove)) {
				openGames.remove(id);
			}
		}
	}

	private static boolean didGameTimedOut(long ts) {
		long ct = System.currentTimeMillis() / 1000; // seconds
		return (ct - ts) > GeneralConfig.GAME_TIME_MAX_OUT;
	}

	public boolean endGame(String id) {
		if (openGames.containsKey(id)) {
			openGames.remove(id);
			return true;
		}
		return false;
	}
}
