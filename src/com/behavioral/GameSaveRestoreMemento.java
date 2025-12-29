/*
 * 16) Game Save / Restore System
Requirement: Allow saving and restoring game state without exposing internals.

Implement:
Memento object
Caretaker

Follow-ups:
Memento vs Command
Memory optimization
Snapshot frequency
 */


/*
 * the whole point of memento is to not expose anything about the originator to the caretaker
 * we keep a separate memento class which stores independent states which are final in nature. Something like a snapshot
 * only the GameManager can create these snapshots and the history stores and keeps track about these snapshots without knowing who created/modified these snapshots
 * 
 * in Memento the driver calss only talks to the game managere class
 */

package com.behavioral;

import java.util.Stack;

class GameStateManager {
	String gameState;

	public String getGameState() {
		return gameState;
	}

	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	
	public Memento save() {
		return new Memento(this.gameState);
	}
	
	public void restore(Memento m) {
		this.gameState = m.getGameState();
	}
}

class Memento {
	private final String gameState;
	
	Memento(String gs) {
		this.gameState = gs;
	}
	
	public String getGameState() {
		return this.gameState;
	}
}

class GameHistory {
	Stack<Memento> st;
	
	GameHistory() {
		this.st = new Stack<>();
	}
	
	public void push(Memento m) {
		st.push(m);
	}
	
	public Memento pop() {
		if(st.isEmpty()) {
			throw new NullPointerException("stack is empty");
		}
		else {
			return st.pop();
		}
	}
}

public class GameSaveRestoreMemento {
	public static void main(String[] args) {
		GameStateManager gameStateManager = new GameStateManager();
		GameHistory gameHistory = new GameHistory();
		
		gameStateManager.setGameState("start");
        System.out.println(gameStateManager.getGameState());
        
        // save game
        gameHistory.push(gameStateManager.save());

        gameStateManager.setGameState("level 1");
        gameHistory.push(gameStateManager.save());

        gameStateManager.setGameState("level 2");

        // restore last
        gameStateManager.restore(gameHistory.pop()); // back to "level 1"
        System.out.println(gameStateManager.getGameState());

        // restore previous
        gameStateManager.restore(gameHistory.pop()); // back to "start"
        System.out.println(gameStateManager.getGameState());
        
	}
}
