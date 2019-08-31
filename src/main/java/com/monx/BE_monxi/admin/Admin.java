package com.monx.BE_monxi.admin;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monx.BE_monxi.game.tictactoe.TicTacToe_Manager;
import com.monx.BE_monxi.models.Response;
import com.monx.BE_monxi.models.tictactoe.TicTacToeBoard;

@RestController
@RequestMapping("admin/")
public class Admin {
	
    @RequestMapping(value = "ttt/resetGames", method = RequestMethod.GET)
    public String requestGame(@RequestParam(name = "game", required = false) String game) {
    	return "TicTacToe - openGames: " + TicTacToe_Manager.resetGames();
    }
    
    @RequestMapping(value = "ttt/getGames", method = RequestMethod.GET)
    public Response<List<TicTacToeBoard>> requestGames() {
    	Response<List<TicTacToeBoard>> resp = new Response<List<TicTacToeBoard>>();
    	resp.body = TicTacToe_Manager.getAllBoards();
    	return resp.send();
    }
    
}
