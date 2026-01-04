/*
 * Rate Limiter + Access Control (Chain + Strategy)
Asked in: Amazon, Uber

Requirement
Incoming API request passes through:
Authentication
Authorization
Rate limiting
Request validation
Different APIs use different rate-limit strategies.

Patterns Used
Chain of Responsibility → request pipeline
Strategy → fixed window, sliding window, token bucket

Interview Twist
“Tomorrow we want per-user + per-IP rate limits — how do you extend?”
 */


/*
 * important point mentioned here on how to access the lower class and its methods in the chain
 * see rate limiter class in the chain
 */

package com.behavioral;

abstract class ApiSteps {
	ApiSteps nextStep;
	
	public void setNextStep(ApiSteps nextStep) {
		this.nextStep = nextStep;
	}
	
	abstract void action();
}





class Authentication extends ApiSteps {
	void action() {
		System.out.println("Authentication done");
		if(nextStep != null) nextStep.action();
	}
}

class Authorization extends ApiSteps {
	void action() {
		System.out.println("Authorization done");
		if(nextStep != null) nextStep.action();
	}
}


class RateLimiting extends ApiSteps {
	RateLimitingStrategy rateLimitingStrategy;
	int requests; String user;
	
	public RateLimiting(RateLimitingStrategy rateLimitingStrategy, int requests, String user, RequestValidation requestValidation) {
		this.rateLimitingStrategy = rateLimitingStrategy;
		this.requests = requests;
		this.user = user;
	}
	
	void action() {
		boolean result = rateLimitingStrategy.executeRateLimiting(requests, user);
		if(result == true) {
			((RequestValidation)nextStep).setIsValid(true);		// accessing next class in the chain. type casting since nextStep is of abstract class type
		}
		else {
			((RequestValidation)nextStep).setIsValid(false);	// accessing next class in the chain. type casting since nextStep is of abstract class type
		}
		
		if(nextStep != null) nextStep.action();
	}
}


class RequestValidation extends ApiSteps {
	boolean isValid;
	
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	void action() {
		System.out.println("Validation - " + (isValid ? "success" : "failed"));
		
		if(nextStep != null) nextStep.action();
		else System.out.println("end of steps");
	}
}






interface IRateLimitingType {
	boolean RateLimit(int requests, String user);
}

class RateLimitByIp implements IRateLimitingType {
	public boolean RateLimit(int requests, String ip) {
		if(ip.startsWith("1")) {
			if(requests <= 10) {
				return true;
			}
		}
		return false;
	}
}

class RateLimitByRegion implements IRateLimitingType {
	public boolean RateLimit(int requests, String region) {
		if(region.equalsIgnoreCase("India")) {
			if(requests <= 10) {
				return true;
			}
		}
		return false;
	}
}

class RateLimitingStrategy {
	IRateLimitingType rateLimitingType;
	
	public void setRateLimitingType(IRateLimitingType rateLimitingType) {
		this.rateLimitingType = rateLimitingType;
	}
	
	public boolean  executeRateLimiting(int requests, String user) {
		return rateLimitingType.RateLimit(requests, user);
	}
}


public class RateLimiterChainStrategy {
	public static void main(String[] args) {
        // Step 1: Create chain elements
        Authentication auth = new Authentication();
        Authorization authorization = new Authorization();
        RequestValidation validation = new RequestValidation();

        // Step 2: Choose a rate limiting strategy
        RateLimitingStrategy strategy = new RateLimitingStrategy();
        strategy.setRateLimitingType(new RateLimitByIp()); // can swap with RateLimitByRegion

        RateLimiting rateLimiting = new RateLimiting(strategy, 5, "123.45.67.89", validation);

        // Step 3: Wire the chain
        auth.setNextStep(authorization);
        authorization.setNextStep(rateLimiting);
        rateLimiting.setNextStep(validation);

        // Step 4: Execute the chain
        auth.action();
	}
}
