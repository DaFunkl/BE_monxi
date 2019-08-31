package com.monx.BE_monxi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monx.BE_monxi.game.tictactoe.TicTacToe;
import com.monx.BE_monxi.game.tictactoe.TicTacToe_Manager;
import com.monx.BE_monxi.models.GameRequest;
import com.monx.BE_monxi.models.Response;
import com.monx.BE_monxi.models.basic.Vec2;
import com.monx.BE_monxi.models.tictactoe.TicTacToeBoard;

@RestController
@RequestMapping("api/game/ttt")
public class TicTacToe_Controller {

	@RequestMapping(value = "/newGame", method = RequestMethod.GET)
	public String requestGame() {
		return TicTacToe_Manager.requestGame();
	}

	@RequestMapping(value = "/state", method = RequestMethod.POST)
	public Response<TicTacToeBoard> requestGameState(@RequestBody String gameId) {
		Response<TicTacToeBoard> ret = new Response<>();
		TicTacToe game = TicTacToe_Manager.getGame(gameId);
		if (game == null) {
			ret.valid = false;
			ret.msg = "No Game with ID '" + gameId + "' found.";
			return ret.send();
		}
		ret.body = game.toTttBoard();
		return ret.send();
	}

	@RequestMapping(value = "/move", method = RequestMethod.POST)
	public Response<TicTacToeBoard> makeMove(@RequestBody GameRequest<Vec2<Integer>> gr) {
		Response<TicTacToeBoard> ret = new Response<>();
		// Game not found
		if (!TicTacToe_Manager.gameExists(gr.gameId)) {
			ret.valid = false;
			ret.msg = "No Game with ID '" + gr.gameId + "' found.";
			return ret.send();
		}
		// Invalid Move
		boolean wasMoveValid = TicTacToe_Manager.makeMove(gr.gameId, gr.req);
		ret.body = TicTacToe_Manager.getBoard(gr.gameId);
		if(!wasMoveValid) {
			ret.valid = false;
			ret.msg = "Invalid move: " + gr.req;
			return ret.send();
		}
		return ret.send();
	}

}
