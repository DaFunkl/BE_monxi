package com.monx.BE_monxi.models.tictactoe;

import java.util.List;

import com.monx.BE_monxi.models.basic.Vec2;

public class TicTacToeBoard {
	public char[][] board;
	public char currentTurn;
	public List<Vec2<Integer>> availableTurns;
	public char winner;
	public boolean finished = false;
}
