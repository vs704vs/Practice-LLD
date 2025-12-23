/*
 * 14) Logging Framework
Requirement: Log messages go through multiple loggers based on severity.

Follow-ups:
Performance concerns
Async logging
Dynamic chain configuration
 */

package com.behavioral;

abstract class Logger {
	Logger nextLogger;
	
	public void setNextLogger(Logger logger) {
		this.nextLogger = logger;
	}
	
	abstract public void logMessage(int severity);  
}



class ServerLogger extends Logger {
	@Override
	public void logMessage(int severity) {
		if(severity <= 0) {
			System.out.println("Server logged the message");
		}
		else if(nextLogger != null) { 
			nextLogger.logMessage(severity);
		}
		else {
			System.out.println("Cannot be logged");
		}
	}
}

class PodLogger extends Logger {
	@Override
	public void logMessage(int severity) {
		if(severity <= 1) {
			System.out.println("Pod logged the message");
		}
		else if(nextLogger != null) { 
			nextLogger.logMessage(severity);
		}
		else {
			System.out.println("Cannot be logged");
		}
	}
}

class KubernetesLogger extends Logger {
	@Override
	public void logMessage(int severity) {
		if(severity <= 2) {
			System.out.println("Kubernetes logged the message");
		}
		else if(nextLogger != null) { 
			nextLogger.logMessage(severity);
		}
		else {
			System.out.println("Cannot be logged");
		}
	}
}

public class LoggingFrameworkChainOfResponsibility {
	public static void main(String[] args) {
		ServerLogger serverLogger = new ServerLogger();
		PodLogger podLogger = new PodLogger();
		KubernetesLogger kubernetesLogger = new KubernetesLogger();
		
		serverLogger.setNextLogger(podLogger);
		podLogger.setNextLogger(kubernetesLogger);
		
		serverLogger.logMessage(1);
	}
}
