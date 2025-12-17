/*
 * 11. Plugin System Bootstrapping (choose Abstract Factory / Prototype / Builder)
Requirement: Design a plugin loader that instantiates plugin objects based on plugin metadata. Plugins belong to families (e.g., Analytics plugins and Auth plugins) where each family has different required collaborators. Implement a solution that supports adding new plugin families without changing client code.
What to do in interview: Propose an AbstractFactory for families, or use Factory + Prototype (load a prototype plugin and clone it), or a Builder if plugin construction is complex. Provide code skeleton for your chosen design and explain why.
Follow-ups: Lifecycle, versioning, security, classloader isolation, hot-reload, and dependency injection.
 */

package com.example;

interface AnalyticsPlugin {
	
}

interface AuthPlugin {
	
}

class BarGraph implements AnalyticsPlugin {

}

class LineGraph implements AnalyticsPlugin {

}

class Login implements AuthPlugin {

}

class Signup implements AuthPlugin {

}

enum PluginType {
	BAR_GRAPH, LINE_GRAPH, LOGIN, SIGN_UP;
}

class PluginFactory {
	public AnalyticsPlugin getAnalyticsPlugin(PluginType metadata) {
		if(metadata.equals(PluginType.BAR_GRAPH)) return new BarGraph();
		else if(metadata == PluginType.LINE_GRAPH) return new LineGraph();
		else throw new IllegalArgumentException("invalid data");
	}
	
	public AuthPlugin getAuthPlugin(PluginType metadata) {
		if(metadata.equals(PluginType.LOGIN)) return new Login();
		else if(metadata == PluginType.SIGN_UP) return new Signup();
		else throw new IllegalArgumentException("invalid data");
	}
}

public class PluginSystem {
	public static void main(String[] args) {
		
	}
}
