package com.monx.BE_monxi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monx.BE_monxi.configuration.GeneralConfig;
import com.monx.BE_monxi.game.tictactoe.TicTacToe_Manager;

@RestController
public class Default_Controller {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		String ret = "<h1>Hello Monx M8!</h1>"
				+ "<br>"
				+ "<h2>Wassabi Masters ´°`_´°`</h2>"
				+ "<br>"
				+ "--> TicTacToe: " + TicTacToe_Manager.getOpenGameAmt() + " / " + GeneralConfig.MAX_OPEN_GAMES;
		return ret;
	}
}
