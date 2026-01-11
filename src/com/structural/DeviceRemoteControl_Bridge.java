/*
 * Device + Remote Control
Asked in: Embedded + system design

Requirement: Remote controls operate:

TV
AC
Speaker

Remote abstraction decoupled from device implementation.
 */


package com.structural;

interface IAction {
	void performAction();
}

class ChangeChannel implements IAction {
	@Override
	public void performAction() {
		System.out.println("Channel has been changed");
	}
}

class LowerTemperature implements IAction {
	@Override
	public void performAction() {
		System.out.println("Temperature has been lowered");
	}
}

class Volume implements IAction {
	@Override
	public void performAction() {
		System.out.println("Volume has been increased");
	}
}




abstract class Device {
	IAction action;
	
	public Device(IAction action) {
		this.action = action;
	}
	
	abstract public void doAction();
}






class Tv extends Device {
	public Tv(IAction action) {
		super(action);
	}
	
	@Override
	public void doAction() {
		System.out.print("TV | ");
		action.performAction();
	}
}

class Ac extends Device {
	public Ac(IAction action) {
		super(action);
	}
	
	@Override
	public void doAction() {
		System.out.print("AC | ");
		action.performAction();
	}
}

class Speaker extends Device {
	public Speaker(IAction action) {
		super(action);
	}
	
	@Override
	public void doAction() {
		System.out.print("Speaker | ");
		action.performAction();
	}
}









public class DeviceRemoteControl_Bridge {
    public static void main(String[] args) {
        // Create actions
        IAction changeChannel = new ChangeChannel();
        IAction lowerTemperature = new LowerTemperature();
        IAction increaseVolume = new Volume();

        // Use actions with different devices
        Device tvChangeChannel = new Tv(changeChannel);
        Device tvVolume = new Tv(increaseVolume);

        Device acLowerTemp = new Ac(lowerTemperature);

        Device speakerVolume = new Speaker(increaseVolume);

        // Execute actions
        tvChangeChannel.doAction();  // TV | Channel has been changed
        tvVolume.doAction();         // TV | Volume has been increased
        acLowerTemp.doAction();      // AC | Temperature has been lowered
        speakerVolume.doAction();    // Speaker | Volume has been increased
    }
}
