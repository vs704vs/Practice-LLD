package com.tictactoe;

public class Game {
	Player player1, player2;
	boolean isPlayer1Active;
	Board board;
	
	public Game(Board board, Player player1, Player player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	
	public void startGame() {
		this.isPlayer1Active = true;
		board.reset();
		board.printBoard();
	}
	
	
	
	public Player getActivePlayer() {	
		if(isPlayer1Active) return player1;
		return player2;
	}
	
	public void switchActivePlayer() {
		this.isPlayer1Active = !isPlayer1Active;
	}
	
	
	
	public boolean isBoardFull() {
		return board.isBoardFull();
	}
	
	public void printBoard() {
		board.printBoard();
	}
	
	
	
	// if move is winning return 1
	// if move is invalid return -1
	// else return 0
	public int performMove(int i, int j) {
		char symbol = getActivePlayer().getSymbol();
		
		if(isMoveValid(i, j)) {
			board.performMove(symbol, i, j);
			
			if(isMoveWinning(symbol, i, j)) return 1;
			else return 0;
		}
		return -1;
	}
	
	
	
	
	private boolean isMoveValid(int i, int j) {
		char[][] grid = board.getGrid();
		
		
		if(i >= 0 && i < 3 && j >= 0 && j < 3 && grid[i][j] == '#' ) return true;
		return false;
	}
	
	
	
	
	
	private boolean isMoveWinning(char symbol, int r, int c) {
		char[][] grid = board.getGrid();
		
		boolean isWinning;
		
		// check horizontal
		isWinning = true;
		for(int j=0; j<3; j++) {
			if(grid[r][j] != symbol) {
				isWinning = false;
				break;
			}
		}
		if(isWinning) return true;
		
		// check vertical
		isWinning = true;
		for(int i=0; i<3; i++) {
			if(grid[i][c] != symbol) {
				isWinning = false;
				break;
			}
		}
		if(isWinning) return true;
		
		// check diagonal
		isWinning = true;
		if(r == c || r + c == 3-1) {		// checking if it is a vlid diagonal value
			
			// left diagonal
			for(int i=0; i<3; i++) {
				if(grid[i][i] != symbol) {
					isWinning = false;
					break;
				}
			}
			if(isWinning) return true;
			
			// right diagonal
			isWinning = true;
			for(int i=0; i<3; i++) {
				if(grid[i][3-i-1] != symbol) {
					isWinning = false;
					break;
				}
			}
			if(isWinning) return true;
		}
		
		return false;
	}
}
 
