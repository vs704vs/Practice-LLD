package com.creational;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * 
 * Vehicle Factory (classic)
Requirement: Implement a factory that creates vehicles (Car, Bike, Truck) based on a string/type enum. Each concrete vehicle has start(), stop(), and getMaxSpeed() behaviors. Client code must only depend on an interface Vehicle.
Implement: IVehicle interface, Car/Bike/Truck classes, and VehicleFactory.create(type) returning IVehicle.
Acceptance: VehicleFactory.create("car").getMaxSpeed() returns car speed; invalid type → throw IllegalArgumentException.
Follow-ups: Add new vehicle types (open/closed), unit tests for factory, replace string with enum, lazy-loading of heavy objects, dependency injection alternatives. (Common interview staple — shows encapsulation of creation). 
GeeksforGeeks
 */

// benefit of using enum: you will know about types at compile time and not runtime. also, adding types becomes easier

enum VehicleType {
	CAR, BIKE, TRUCK;
}

interface IVehicle {
	public void start();
	public void stop();
	public int getMaxSpeed();
}

class Car implements IVehicle {
	public void start() {
		System.out.println("car start");
	}
	public void stop() {
		System.out.println("car stop");
	}
	public int getMaxSpeed() {
		return 100; 
	}
}

class Bike implements IVehicle {
	public void start() {
		System.out.println("bike start");
	}
	public void stop() {
		System.out.println("bike stop");
	}
	public int getMaxSpeed() {
		return 60; 
	}
}

class Truck implements IVehicle {
	public void start() {
		System.out.println("bike start");
	}
	public void stop() {
		System.out.println("bike stop");
	}
	public int getMaxSpeed() {
		return 150; 
	}
}

// for heavier object which require a lot of memory
// do not create new objects everytime 
// try to reuse them (making every object singleton) so that memory is saved
class VehicleFactory {
	public static IVehicle create(VehicleType type) {
		switch(type) {
			case CAR:
				return new Car();
			case BIKE:
				return new Bike();
			case TRUCK:
				return new Truck();
			default:
				throw new IllegalArgumentException("not a valid input");
		}
	}
}

public class AllVehicleFactory {
	public static void main(String[] args) {
		System.out.println(VehicleFactory.create(VehicleType.CAR).getMaxSpeed()); 
	}
}

//When you run your test suite (via IDE, Maven/Gradle, or command line), the testing framework:
//Scans your classes for methods annotated with @Test.
//Executes each of them independently.
//Reports results (pass/fail, exceptions, assertions).
class VehicleFactoryTest {
	@Test
	void testCarCreation() {
		IVehicle vehicle = VehicleFactory.create(VehicleType.CAR);
		assertTrue(vehicle instanceof Car);
		assertEquals(100, vehicle.getMaxSpeed());
	}
}
