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
