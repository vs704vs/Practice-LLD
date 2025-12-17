/*
 * 9. Application Config Singleton (with thread-safety)
Requirement: Implement a Config object that loads app settings once and is globally accessible. Ensure thread-safe lazy initialization and safe deserialization/reflective attacks.
Implement: Thread-safe singleton (e.g., enum singleton in Java, or double-checked locking with volatile). Provide getInstance() and reload() semantics (if allowed).
Acceptance: Multithreaded test where many threads call getInstance() — only one instance is created. Serialization-deserialization preserves singleton identity.
Follow-ups (very common interview follow-ups): Explain enum-based singletons (Java) vs static field, how to prevent breaking by reflection, serialization pitfalls and readResolve(), why many consider Singleton an anti-pattern and alternatives (DI). Be ready to explain pros/cons. (Singleton interview questions appear a lot; StackOverflow threads cover nuances). 
 */

package com.example;

class Config {
	
	String appsetting;
	
	Config() {
		System.out.println("instance initialised");
		appsetting = "setting";
	}
	
	private static class innerConfig {
		private static final Config INSTANCE = new Config();
	}
	
	public static Config getInstance() {
		return innerConfig.INSTANCE;
	}
}

public class ApplicationConfig {
	public static void main(String[] args) {
		Runnable task = () -> {
            Config config = Config.getInstance();
            System.out.println(Thread.currentThread().getName() + " -> " + config.hashCode());
        };

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
	}
}
