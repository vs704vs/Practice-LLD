/*
 * 6) Undo / Redo in Text Editor (VERY common)
Requirement: Implement undo/redo for actions like:

Insert text
Delete text
Add text
Each action must be reversible.

Implement:

Command interface (execute, undo)
Command history stack
Acceptance:

Undo restores previous state
Redo re-applies last undone command

Follow-ups:

Command vs Memento
Memory optimization
Batch commands
Logging & audit trails
 */

package com.behavioral;

interface Command {
	void execute();
	void undo();
}

class InsertCommand implements Command {
	TextOperations textOperations;
	String text; int index;
	
	InsertCommand(TextOperations textOperations, String text, int index) {
		this.textOperations = textOperations;
		this.text = text;
		this.index = index;
	}
	
	public void execute() {
		textOperations.insertText(index, text);
	}
	
	public void undo() {
		
	}
}

class AddCommand implements Command {
	TextOperations textOperations;
	String text;
	
	AddCommand(TextOperations textOperations, String text) {
		this.textOperations = textOperations;
		this.text = text;
	}
	
	public void execute() {
		textOperations.addText(text);
	}
	
	public void undo() {
		
	}
}

class DeleteCommand implements Command {
	TextOperations textOperations;
	String text; 
	
	DeleteCommand(TextOperations textOperations, String text) {
		this.textOperations = textOperations;
		this.text = text;
	}
	
	public void execute() {
		textOperations.deleteText(text);
	}
	
	public void undo() {
		
	}
}








class TextOperations {
	String mainText;

	public TextOperations() {
		this.mainText = "";
	}

	public String getMainText() {
		return mainText;
	}

	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	
	
	public void insertText(int index, String text) {
		if(index > mainText.length() - 1 || index < 0) throw new IllegalArgumentException("invalid index");
		else {
			String s1 = mainText.substring(0, index+1);
			String s2 = mainText.substring(index+1);
			String str = s1 + text + s2;
			setMainText(str);
		}
	}
	
	public void addText(String text) {
		setMainText(mainText + text);
	}
	
	public void deleteText(String text) {
		String str = mainText;
		str.replace(text, "");
		setMainText(str);
	}
}





public class TextEditorCommandPattern {
	public static void main(String[] args) {
		
	}
}
