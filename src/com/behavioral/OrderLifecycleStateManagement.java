/*
 * 10) Order Lifecycle Management (extremely common)
Requirement: An order transitions through states: Paid → Shipped → Delivered

Each state has different allowed actions.

Implement:
OrderState interface
Concrete state classes

Acceptance:
Invalid transitions are prevented
Behavior depends on current state

Follow-ups:
State vs if-else
State explosion
Persistence of state
Workflow engines comparison
 */


/*
 * for state design pattern:
 * - have a central state managemnt class what has information about what current state is going on
 * - have a setter class to set this current state
 * - the driver class will only talk to this central state management class
 * 
 * - implement concrete classes for the states you want
 * - these state classes can have 2 functions getState and nextState
 * - getState gives out what state is going on by printing out the current class (state in this case) the function is in.
 * - nextState calls the setter method in central state managemnt class so that it can change the state to the next state it wants.
 * 
 * - the central state namagement class also has getCurrentState and next methods which basically relays the request to one of the state class depending on the current state
 * - make sure to pass the current instance of the state management class when you relay or make a call to the next function of one of the states 
 * - so that the next function of the state class can call the setter method in the state management class to change the state
 */

package com.behavioral;

interface IOrderState {
	public void getState();
	public void nextState(OrderStateManagement orderStateManagement);
}

class PaidState implements IOrderState {
	public void getState() {
		System.out.println("This is the paid state");
	}
	
	public void nextState(OrderStateManagement orderStateManagement) {
		orderStateManagement.setCurrentState(new ShippedState());
	}
}

class ShippedState implements IOrderState {
	public void getState() {
		System.out.println("This is the shipped state");
	}
	
	public void nextState(OrderStateManagement orderStateManagement) {
		orderStateManagement.setCurrentState(new DeliveredState());
	}
}

class DeliveredState implements IOrderState {
	public void getState() {
		System.out.println("This is the delivered state");
	}
	
	public void nextState(OrderStateManagement orderStateManagement) {
		orderStateManagement.setCurrentState(null);
	}
}





class OrderStateManagement {
	IOrderState currentState;
	
	OrderStateManagement() {
		// start with a state
		this.currentState = new PaidState();
	}
	
	public void setCurrentState(IOrderState state) {
		this.currentState = state;
	}
	
	public void getCurrentState() {
		if(currentState == null) {
			System.out.println("Current state is null");
		}
		else {
			currentState.getState();
		}
	}
	
	public void next() {
		if(currentState == null) {
			System.out.println("Current state is null - no next state possible");
		}
		else {
			currentState.nextState(this);
		}
	}
}


public class OrderLifecycleStateManagement {
	public static void main(String[] args) {
		OrderStateManagement orderStateManagement = new OrderStateManagement();
		
		orderStateManagement.getCurrentState();		
		orderStateManagement.next();
		orderStateManagement.getCurrentState();
		orderStateManagement.next();
		orderStateManagement.getCurrentState();
		orderStateManagement.next();
		orderStateManagement.getCurrentState();
		orderStateManagement.next();
	}
}
