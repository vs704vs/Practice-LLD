/*
 * Version Control Lite
(Command + Memento + Observer) Asked in: Dev tools companies

Requirement
Track changes
Rollback
Notify collaborators

Patterns Used
Command → changes
Memento → snapshots
Observer → collaboration updates
 */

package com.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

interface ICommand {
	void execute(String s);
}

class AddFirst implements ICommand {
	VersionManager versionManager;

	public AddFirst(VersionManager versionManager) {
		this.versionManager = versionManager;
	}

	public void execute(String s) {
		versionManager.addFront(s);
	}
}

class AddLast implements ICommand {
	VersionManager versionManager;

	public AddLast(VersionManager versionManager) {
		this.versionManager = versionManager;
	}

	public void execute(String s) {
		versionManager.addLast(s);
	}
}










class VersionManager {
	String mainText;
	List<Observer> observers = new ArrayList<Observer>();

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}

	public void restore(Momento m) {
		setMainText(m.text);
		notifyObservers();
	}

	public Momento save() {
		return new Momento(mainText);
	}

	
	
	
	
	
	public void addLast(String s) {
		this.mainText = this.mainText + s;
		notifyObservers();
	}

	public void addFront(String s) {
		this.mainText = s + this.mainText;
		notifyObservers();
	}

	
	
	
	
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (Observer o : observers) {
			o.updateObserver(this);
		}
	}
}

class Momento {
	String text;

	Momento(String text) {
		this.text = text;
	}
}

class VersionManagerHistory {
	Stack<Momento> undoStack;
	Stack<Momento> redoStack;

	VersionManagerHistory() {
		undoStack = new Stack<Momento>();
		redoStack = new Stack<Momento>();
	}
	
	public void saveState(Momento m) {
		undoStack.push(m);
		redoStack.clear();
	}


	// Undo operation: push current state to redo stack and return last state from undo stack
	public Momento undo() {
		redoStack.push(undoStack.pop());
		return undoStack.peek();
	}

	// Redo operation: push current state to undo stack and return last state from redo stack
	public Momento redo() {
		undoStack.push(redoStack.peek());
		return redoStack.pop();
	}

}










class Observer {
	String name;

	public Observer(String name) {
		this.name = name;
	}

	public void updateObserver(VersionManager versionManager) {
		System.out.println(this.name + " is being informed about the text - " + versionManager.mainText);
	}
}








public class VersionControl_CommandMementoObserver {
    public static void main(String[] args) {
        // Create version manager and set initial content
        VersionManager vm = new VersionManager();
        vm.setMainText("Hello");

        // Add collaborators (observers)
        Observer alice = new Observer("Alice");
        Observer bob   = new Observer("Bob");
        vm.addObserver(alice);
        vm.addObserver(bob);

        // History manager for undo/redo
        VersionManagerHistory history = new VersionManagerHistory();

        // Commands
        ICommand addFirst = new AddFirst(vm);
        ICommand addLast  = new AddLast(vm);

        // --- Save initial state ---
        history.saveState(vm.save());

        // --- Perform some changes via commands ---
        addLast.execute(" World");          // "Hello World"            
        history.saveState(vm.save());          // Save snapshot for undo

        addFirst.execute("Hey, ");          // "Hey, Hello World"
        history.saveState(vm.save());

        addLast.execute("!");               // "Hey, Hello World!"
        history.saveState(vm.save());

        // --- Undo twice ---
        System.out.println("\n--- Undo x2 ---");
        
        Momento m;
        m = history.undo();                 // step back to "Hey, Hello World"
        if (m != null) vm.restore(m);
        
        m = history.undo();                 // step back to "Hello World"
        if (m != null) vm.restore(m);

        // --- Redo once ---
        System.out.println("\n--- Redo x1 ---");
        m = history.redo();                 // forward to "Hey, Hello World"
        if (m != null) vm.restore(m);

        // --- Remove one observer and make another change ---
        System.out.println("\n--- Bob unsubscribes ---");
        vm.removeObserver(bob);
        addLast.execute(" [after Bob left]"); // "Hey, Hello World [after Bob left]"
        
        history.saveState(vm.save());

        // Final state (for clarity)
        System.out.println("\nFinal text: " + vm.mainText);
    }
}
