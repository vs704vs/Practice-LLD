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

// normal class supporting saving and undo operations 
//class GameHistory {
//	Stack<Memento> st;
//	
//	GameHistory() {
//		this.st = new Stack<>();
//	}
//	
//	public void push(Memento m) {
//		st.push(m);
//	}
//	
//	public Memento pop() {
//		if(st.isEmpty()) {
//			throw new NullPointerException("stack is empty");
//		}
//		else {
//			return st.pop();
//		}
//	}
//}



// enhanced history class supporting both undo and redo operations
class GameHistory {
	
	// stores past states (what you already have).
	Stack<Memento> undoStack;
	// stores states that were undone, so you can re-apply them.
	Stack<Memento> redoStack;
	
	GameHistory() {
		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
	}
		
	public void save(Memento memento) {
		undoStack.push(memento);
		redoStack.clear();
	}
	
	public Memento undo() {
		redoStack.push(undoStack.peek());
		return undoStack.pop();
	}
	
	public Memento redo() {
		undoStack.push(redoStack.peek());
		return redoStack.pop();
	}
}


/*
 * 
- Undo:
- Pop from the undo stack.
- Push the popped state onto the redo stack.
- Restore the popped state.

- Redo:
- Pop from the redo stack.
- Push the popped state back onto the undo stack.
- Restore the popped state.

- New Save:
- When you save a new state, clear the redo stack (because redo history is invalid once you branch off with a new change).
 */


public class GameSaveRestoreMemento {
	public static void main(String[] args) {
		GameStateManager gameStateManager = new GameStateManager();
        GameHistory gameHistory = new GameHistory();

        gameStateManager.setGameState("start");
        gameHistory.save(gameStateManager.save());
        System.out.println("Current: " + gameStateManager.getGameState());

        gameStateManager.setGameState("level 1");
        gameHistory.save(gameStateManager.save());
        System.out.println("Current: " + gameStateManager.getGameState());

        gameStateManager.setGameState("level 2");
        gameHistory.save(gameStateManager.save());
        System.out.println("Current: " + gameStateManager.getGameState());

        // Undo twice
        gameStateManager.restore(gameHistory.undo());
        System.out.println("Undo → " + gameStateManager.getGameState());

        gameStateManager.restore(gameHistory.undo());
        System.out.println("Undo → " + gameStateManager.getGameState());

        // Redo once
        gameStateManager.restore(gameHistory.redo());
        System.out.println("Redo → " + gameStateManager.getGameState());        
	}
}
