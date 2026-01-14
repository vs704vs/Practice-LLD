package com.tictactoe;

public class Board {
	private char[][] grid;
	private int boardMoveCount;
	
	public Board() {
		this.grid = new char[3][3];
	}
	
	
	public void reset() {
		this.boardMoveCount = 0;
		
		// initialise grid with dummy values
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				grid[i][j]= '#'; 
			}
		}
	}
	
	
	public char[][] getGrid() {
		return this.grid;
	}
	
	
	
	
	
	public void performMove(char symbol, int i, int j) {
		this.grid[i][j] = symbol; 
		this.boardMoveCount++;
	}
	
	public boolean isBoardFull() {
		if(boardMoveCount >= 9) return true;
		return false;
	}
	
	
	public void printBoard() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
	}
}
