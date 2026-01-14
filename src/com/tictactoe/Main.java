/*
 * Question and Requirements:
 * 
 * need to havea 3x3 board
 * there has to be 2 players
 * 
 * need to do validation
 * - cannot move outside the board 
 * - cannot move on top of an already marked cell
 * 
 * need to check for victory 
 * - after every move check whether the cell form a line (horizontal, vertical or diagonal)
 * - if winner found print the winner and finish the game
 * - if all cells are filled and no winner is found print tie game and finish the game
 * 
 * 
 * My Approach:
 * 
 * Will have a player class which will 
 * - store the player name
 * - store player symbol ('X' or 'O')
 * 
 * will have a board class which will
 * - perform move 
 * - check if board is full or not
 * 
 * will have a game class which will
 * - do move validation
 * - check victory
 * - check tie
 * - set active player
 * - return current active player
 * 
 * will be checking victory at every step
 * will have a char board denoting 'X' and 0 denoting 'O'
 * player 1 will go with 'X'
 * 
 * game class has a board and player
 */

package com.tictactoe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/*
		 * steps:
		 * 
		 * make 2 players
		 * set first player active (1st player will go first)
		 * start game
		 * 
		 * while board is not full
		 * - get active player 
		 * - let active player make move
		 * - take move input
		 * - check valid move 
		 * - insert move on board
		 * - check win 
		 * - if yes stop game else switch active player
		 * 
		 * outside while loop declare tie game 
		 */
		
		Scanner scanner = new Scanner(System.in);
		
		String player1Name, player2Name;
		
		System.out.println("Enter player1 name");
		player1Name = scanner.next();
		System.out.println("Enter player2 name");
		player2Name = scanner.next();
		
		Player player1 = new Player(player1Name, 'X');
		Player player2 = new Player(player2Name, 'O');
		Board board = new Board();
		
		Game game = new Game(board, player1, player2);
		game.startGame();
		
		
		
		while (!game.isBoardFull()) {
			Player activePlayer = game.getActivePlayer();
			
			System.out.println(activePlayer.getName() + " input:");
			System.out.print("Input row - ");
			int r = scanner.nextInt();
			System.out.print("Input column - ");
			int c = scanner.nextInt();
			
			int res = game.performMove(r, c);
			
			if(res == 1) {
				System.out.println(activePlayer.getName() + " has won the game!");
				game.printBoard();
				return;
			}
			else if(res == -1) {
				System.out.println("Invalid move, try again");
				continue;
			}
			else {
				game.switchActivePlayer();
				game.printBoard();
			}
		}
		
		System.out.println("Game is tied");
		
		scanner.close();
	}
}
