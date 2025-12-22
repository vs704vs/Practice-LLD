/*
 * 9) Air Traffic Control System
Requirement: Planes do not talk to each other directly. All coordination happens via ATC.

Follow-ups:

Centralized control pros/cons
Failure handling
Distributed mediators
 */


/*
 * Mediator pattern will be used when there is a sending and receiving
 * usually used when there is a broadcast of messages
 * 
 *  when implementing mediator:
 *  - make 2 classes 
 *  - one is the user class which has function to send and receive messgae
 *  - other is the central system where users get registered and from where broadcast (sending message to all users) happens
 *  - implement addUser and breadcastSendMessgae functions in the central system.
 *  - make an interface for this central system class and all teh communication from the user class to this central system happens through this interface
 *  
 *  I have made the first working mplementataion without using the interface so that you get and idea about the 2 main classes and their functions.
 *  2nd implementation is with the interface and true mediator pattern implementataion
 *  
 *  
 *  Side note:
 *  If you want one to one sending and receiving of messages and not bradcast everytime 
 *  you can implement a functino is the central system class called sendMessageDIrect
 *  where instead of running a loop and sending message to all users, you will just send message to thet one receipient
 */


//package com.behavioral;
//import java.util.*;
//
//class Plane {
//	String name;
//	
//	Plane(String name) {
//		this.name = name;
//	}
//	
//	public void sendMessage(String message) {
//		AirTrafficControl.sendMessage(this, message);
//	}
//	
//	public void receiveMessage(String message) {
//		System.out.println(this.name + " received the message - " + message);
//	}
//}
//
//class AirTrafficControl {
//	static List<Plane> planes;
//	
//	AirTrafficControl() {
//		planes = new ArrayList<>();
//	}
//	
//	public void addPlane(Plane p) {
//		planes.add(p);
//	}
//	
//	public void removePlane(Plane p) {
//		planes.remove(p);
//	}
//	
//	public static void sendMessage(Plane sender, String message) {
//		for(Plane p: planes) {
//			if(p != sender) {
//				p.receiveMessage(message);
//			}
//		}
//	}
//}
//
//
//
//public class AirTrafficControlMediatorSystem {
//	public static void main(String[] args) {
//		AirTrafficControl atc = new AirTrafficControl();
//
//        Plane p1 = new Plane("Flight-101");
//        Plane p2 = new Plane("Flight-202");
//        Plane p3 = new Plane("Cargo-303");
//
//        atc.addPlane(p1);
//        atc.addPlane(p2);
//        atc.addPlane(p3);
//
//        p1.sendMessage("Requesting landing clearance.");
//        p2.sendMessage("Holding at waypoint ALFA.");
//        p3.sendMessage("Departing runway 27.");
//
//        System.out.println("\n[ATC] Removing Cargo-303 from control...");
//        ((AirTrafficControl) atc).removePlane(p3);
//
//        p1.sendMessage("Landing completed.");
//	}
//}





package com.behavioral;
import java.util.*;

class Plane {
	String name;
	AirTrafficControlMediator mediator;
	
	Plane(String name, AirTrafficControlMediator mediator) {
		this.name = name;
		this.mediator = mediator;
	}
	
	public void sendMessage(String message) {
		mediator.sendMessage(this, message);
	}
	
	public void receiveMessage(String message) {
		System.out.println(this.name + " received the message - " + message);
	}
}




interface AirTrafficControlMediator {
	public void addPlane(Plane p);
	public void sendMessage(Plane p, String msg);
}

class AirTrafficControl implements AirTrafficControlMediator{
	List<Plane> planes;
	
	AirTrafficControl() {
		planes = new ArrayList<>();
	}
	
	public void addPlane(Plane p) {
		planes.add(p);
	}
	
	public void removePlane(Plane p) {
		planes.remove(p);
	}
	
	public void sendMessage(Plane sender, String message) {
		for(Plane p: planes) {
			if(p != sender) {
				p.receiveMessage(message);
			}
		}
	}
}



public class AirTrafficControlMediatorSystem {
    public static void main(String[] args) {
        AirTrafficControlMediator atc = new AirTrafficControl();

        Plane p1 = new Plane("Flight-101", atc);
        Plane p2 = new Plane("Flight-202", atc);
        Plane p3 = new Plane("Cargo-303", atc);

        atc.addPlane(p1);
        atc.addPlane(p2);
        atc.addPlane(p3);

        p1.sendMessage("Requesting landing clearance.");
        p2.sendMessage("Holding at waypoint ALFA.");
        p3.sendMessage("Departing runway 27.");

        System.out.println("\n[ATC] Removing Cargo-303 from control...");
        ((AirTrafficControl) atc).removePlane(p3);

        p1.sendMessage("Landing completed.");
    }
}



