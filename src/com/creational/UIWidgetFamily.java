package com.creational;

/*
 * UI Widget Family (Abstract Factory)
Requirement: Desktop app needs to create families of widgets for MacOS and Windows (Button, Checkbox). Implement an AbstractFactory that returns the appropriate Button and Checkbox variants for the given platform. Client uses abstract interfaces only.
Implement: WidgetFactory (abstract), MacWidgetFactory, WindowsWidgetFactory, Button, Checkbox interfaces + concrete classes.
Acceptance: client.render(new WindowsWidgetFactory()) produces Windows-style button + checkbox.
Follow-ups: When to prefer Abstract Factory over simple Factory, adding a new platform (scalability), dependency injection, testing factory injection. (Advanced interview-level pattern). 
 */

interface Button {
	
}

interface Checkbox {
	
}

class WindowsButton implements Button {
	
}

class WindowsCheckbox implements Checkbox {
	
}

class MacOsButton implements Button {
	
}

class MacOsCheckbox implements Checkbox {
	
}

//class WidgetFactory {
//	public static Button getButton(String type) {
//		if(type == "W") {
//			return new WindowsButton();
//		}
//		else {
//			return new MacOsButton();
//		}
//	}
//	public static Checkbox getCheckbox(String type) {
//		if(type == "M") {
//			return new WindowsCheckbox();
//		}
//		else {
//			return new MacOsCheckbox();
//		}
//	}
//}

/*
 * when trying to implement abstract factory
 * try to imagine and implement factory first and then 
 * expand the function which returns objects into separate classes 
 * matching the return type of those functions as well
 */

interface WidgetFactory {
	Button getButton();
	Checkbox getCheckbox();
}

class WindowsWidgetFactory implements WidgetFactory {
	public Button getButton() {
		return new WindowsButton();
	}
	public Checkbox getCheckbox() {
		return new WindowsCheckbox();
	}
}

class MacOsWidgetFactory implements WidgetFactory {
	public Button getButton() {
		return new MacOsButton();
	}
	public Checkbox getCheckbox() {
		return new MacOsCheckbox();
	}
}

public class UIWidgetFamily {
	public static void main(String[] args) {
		
		// for factory
//		Button button = WidgetFactory.getButton("W");
//		Checkbox checkbox = WidgetFactory.getCheckbox("M");
		
		
		WidgetFactory widgetFactory1 = new WindowsWidgetFactory();
		widgetFactory1.getButton();
		widgetFactory1.getCheckbox();
		
		WidgetFactory widgetFactory2 = new WindowsWidgetFactory();
		widgetFactory2.getButton();
		widgetFactory2.getCheckbox();
	}
}
