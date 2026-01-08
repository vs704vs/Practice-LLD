/*
 * API Request Middleware
Asked in: Backend-heavy roles

Requirement: HTTP request passes through:

Authentication
Logging
Metrics
Each concern should be pluggable.

Patterns:
Decorator (wrapping request handling)

Follow-ups:

Decorator vs Chain of Responsibility
Execution order
Async decorators
 */





/*
 * Decorator vs Chain of Responsibility

- Decorator Pattern
- Purpose: Adds responsibilities dynamically to an object by wrapping it.
- Execution: All decorators are applied in the order they’re wrapped. Each decorator always calls the next one.
- Use case: When you want to layer cross‑cutting concerns (auth, logging, metrics) without changing the base class.
- Analogy: Like wrapping a gift with multiple layers of paper — every layer is guaranteed to be applied.

- Chain of Responsibility (CoR)
- Purpose: Passes a request along a chain of handlers until one handles it (or none do).
- Execution: Each handler decides whether to process or forward the request.
- Use case: When you want conditional handling (e.g., different error handlers, different routing logic).
- Analogy: Like customer service escalation — each level decides if it can handle the issue or passes it up.

Key difference:
- Decorator = all layers always execute.
- CoR = only one (or some) handlers may execute depending on conditions.
 */


package com.structural;

interface IApiStep {
	String perform();
}

class BaseApi implements IApiStep{
	@Override
	public String perform() {
		return "This is a base API. It will go through the following steps ";
	}
}




abstract class ApiDecorator implements IApiStep {
	IApiStep baseApi;
	
	public ApiDecorator(IApiStep baseApi) {
		this.baseApi = baseApi;
	}
	
	abstract public String perform();
}





class AuthenticationDecorator extends ApiDecorator { 
	AuthenticationDecorator(IApiStep baseApi) {
		super(baseApi);
	}
	
	@Override
	public String perform() {
		return baseApi.perform() + "authnetication, ";
	}
}

class LoggingDecorator extends ApiDecorator { 
	LoggingDecorator(IApiStep baseApi) {
		super(baseApi);
	}
	
	@Override
	public String perform() {
		return baseApi.perform() + "Logging, ";
	}
}

class MetricsDecorator extends ApiDecorator { 
	MetricsDecorator(IApiStep baseApi) {
		super(baseApi);
	}
	
	@Override
	public String perform() {
		return baseApi.perform() + "Metrics, ";
	}
}


public class ApiRequestMiddleware_Decorator {
	public static void main(String[] args) {
		IApiStep api = new BaseApi();
		
		api = new AuthenticationDecorator(api);
		api = new LoggingDecorator(api);
		api = new MetricsDecorator(api);
		
		System.out.println(api.perform());
	}
}
