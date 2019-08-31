package com.monx.BE_monxi.game.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.monx.BE_monxi.models.basic.Vec2;
import com.monx.BE_monxi.models.tictactoe.TicTacToeBoard;

public class TicTacToe {
	private Random randomGenerator = new Random();

	private Boolean[][] board = new Boolean[3][3];
	private Boolean winner = null;
	private boolean turn = true;
	private List<Vec2<Integer>> moves = new ArrayList<>();
	long lastMove = System.currentTimeMillis() / 1000;

	private void refreshLastMove() {
		lastMove = System.currentTimeMillis() / 1000;
	}
	
	public long getLastMoveTs() {
		return lastMove;
	}
	
	public TicTacToe() {
		initMoves();
	}

	void initMoves() {
		moves.clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				moves.add(new Vec2<Integer>(i, j));
			}
		}
	}

	void cTurn() {
		if(moves.size() <= 0 || winner != null) {
			return;
		}
		int nt = nextRando(moves.size());
		Vec2<Integer> t = moves.get(nt);
		moves.remove(nt);
		board[t.x][t.y] = turn;
		turn = !turn;
	}

	boolean turnAvailable = true;
	public boolean pTurn(Vec2<Integer> t) {
		if(!isTurnValid(t) || !turnAvailable || winner != null) {
			return false;
		}
		turnAvailable = false;
		refreshLastMove();		// refreshing here so GameCleaner wont delete
		board[t.x][t.y] = turn;
		moves.remove(t);
		checkWin();
		turn = !turn;
		cTurn();
		refreshLastMove();		// refreshing here so gameTime is more accurate
		turnAvailable = true;
		return true;
	}
	
	void checkWin() {
		winner = didWin();
	}
	
	int nextRando(int range) {
		return randomGenerator.nextInt(range);
	}

	Boolean didWin() {
		for (int i = 0; i < board.length; i++) {
			Boolean b = checkCol(i);
			if (b != null)
				return b;
			b = checkRow(i);
			if (b != null)
				return b;
		}
		return checkDia();
	}
	

	Boolean checkRow(int r) {
		int ta = 0, fa = 0;
		for (int i = 0; i < board[r].length; i++) {
			if (board[r][i] == null)
				return null;
			if (board[r][i] == true)
				if (++ta == 3)
					return true;
			if (board[r][i] == false)
				if (++fa == 3)
					return false;
		}
		return null;
	}

	Boolean checkCol(int c) {
		int ta = 0, fa = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i][c] == null)
				return null;
			if (board[i][c] == true)
				if (++ta == 3)
					return true;
			if (board[i][c] == false)
				if (++fa == 3)
					return false;
		}
		return null;
	}

	Boolean checkDia() {
		int ta = 0, fa = 0, ita = 0, ifa = 0, ofs = board.length - 1;
		for (int i = 0; i < board.length; i++) {
			if (board[i][i] != null) {
				if (board[i][i] == true)
					if (++ta == 3)
						return true;
				if (board[i][i] == false)
					if (++fa == 3)
						return false;
			}
			if (board[i][ofs - i] != null) {
				if (board[i][ofs - i] == true)
					if (++ita == 3)
						return true;
				if (board[i][ofs - i] == false)
					if (++ifa == 3)
						return false;
			}
		}
		return null;
	}

	boolean isTurnValid(Vec2<Integer> t) {
		return moves.contains(t);
	}
	
	public TicTacToeBoard toTttBoard() {
		TicTacToeBoard ret = new TicTacToeBoard();
		checkWin();
		ret.board = baordToCharArr();
		ret.availableTurns = moves;
		ret.currentTurn = convertCell(turn);
		ret.finished = (winner != null);
		if(ret.finished) {
			ret.winner = convertCell(winner);
		}
		return ret;
	}
	
	char[][] baordToCharArr() {
		char[][] ret = new char[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				ret[i][j] = convertCell(board[i][j]);
			}
		}
		return ret;
	}
	
	char convertCell(Boolean b) {
		if(b == null) {
			return '-';
		}
		if(b) {
			return 'X';
		}
		return 'O';
	}
	
}
