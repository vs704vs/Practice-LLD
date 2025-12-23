/*
 * 13) Request Validation Pipeline (VERY common)
Requirement: An API request passes through:

Authentication
Authorization
Rate limiting
Each handler can stop the chain.

Implement:

Handler interface
Linked handlers
Acceptance:

Order of handlers matters
Easy to add/remove handlers
Follow-ups:

Chain vs filters / middleware
Short-circuiting
Debugging complexity
 */


/*
 * Difference between chain of responsibility and state design pattern :
 * 
 * Driver class - . In Chain of responsibility 
 * State:- 
 * - State you have to toggle/initiate the next step by calling the function next()
 * - The Driver method only talks to the central state management class which relays the operations to concete state classes.
 * - There is a central state management class which holds a variable currentState and ability to setState.
 * - currentState is of type IState which is an interface which is implemented by all the contrete state classes. 
 * - The central state management class always has context of the current state being maintained and the concrete state classes decide which next step show the currentState go to by calling the setter method in the central state management class. 
 * 
 * Chain of responsibiility:-
 * - all the next handlers are already pre configured in the driver method. The control simply flows from one step to the next set step.
 * - The driver method talks through the concrete classes (basically the first concrete class which starts the flow of operations).
 * - We do not have interfaces here. Here also we have a central class which is abstract in nature. this central abstract class has a setter method and an abstract function action().  
 * - Here the central class maintains a variable nextStep which is of type of the abstract class itself. the concrete classes are the steps in this case which extend the central abstract class and override the action() method to implement functionality. 
 * - the concrete class here do not decide which nextStep should the control go to. It is pre decided by the driver method.
 */

package com.behavioral;


abstract class ProcessApiRequest {
	ProcessApiRequest nextStep;
	
	public void setNextStep(ProcessApiRequest nextStep) {
		this.nextStep = nextStep;
	}
	
	abstract void action();
}




class AuthenticationStep extends ProcessApiRequest {
	@Override
	void action() {
		System.out.println("Authentication done");
		if(nextStep != null) {
			nextStep.action();
		}
	}
}

class AuthorizationStep extends ProcessApiRequest {
	@Override
	void action() {
		System.out.println("Authorization done");
		if(nextStep != null) {
			nextStep.action();
		}
	}
}

class RateLimitingStep extends ProcessApiRequest {
	@Override
	void action() {
		System.out.println("Rate limiting done");
		if(nextStep != null) {
			nextStep.action();
		}
		else {
			System.out.println("end of all validations");
		}
	}
}

public class RequestPipelineChainOfResponsibility {
	public static void main(String[] args) {
		AuthenticationStep authenticationStep = new AuthenticationStep();
		AuthorizationStep authorizationStep = new AuthorizationStep();
		RateLimitingStep rateLimitingStep = new RateLimitingStep();
		
		authenticationStep.setNextStep(authorizationStep);
		authorizationStep.setNextStep(rateLimitingStep);
		
		authenticationStep.action();
	}
}
