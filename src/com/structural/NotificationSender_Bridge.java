/*
 * Notification Sender (VERY common)
Asked in: Uber, Swiggy

Requirement: Notifications:

Types: Alert, Promotion, OTP
Channels: Email, SMS, Push
Avoid class explosion like: EmailAlert, SMSAlert, PushAlert, etc.

Implement:

Abstraction: Notification
Implementor: NotificationSender
Acceptance:

Any notification type works with any channel

Follow-ups:
Bridge vs Strategy
When bridge is overkill
Runtime switching

 */


package com.structural;



interface INotificationType {
	public void sendMessageType(String message);
}

class Alert implements INotificationType {
	@Override
	public void sendMessageType(String message) {
		System.out.println("Alert notification type | message - " + message);
	}
}

class Promotion implements INotificationType {
	@Override
	public void sendMessageType(String message) {
		System.out.println("Promotion notification type | message - " + message);
	}
}

class Otp implements INotificationType {
	@Override
	public void sendMessageType(String message) {
		System.out.println("OTP notification type | message - " + message);
	}
}








abstract class NotificationChannel {
	INotificationType notificationType;		// Bridge
	
	public NotificationChannel(INotificationType notificationType) {
		this.notificationType = notificationType;
	}
	
	abstract void sendMessage(String message);
}






class Email extends NotificationChannel {
	Email(INotificationType notificationType) {
		super(notificationType);
	}
	
	@Override
	void sendMessage(String message) {
		System.out.print("Email notification | "); 
		notificationType.sendMessageType(message);
	}
}

class Sms extends NotificationChannel {
	Sms(INotificationType notificationType) {
		super(notificationType);
	}
	
	@Override
	void sendMessage(String message) {
		System.out.print("SMS notification | "); 
		notificationType.sendMessageType(message);
	}
}

class Push extends NotificationChannel {
	Push(INotificationType notificationType) {
		super(notificationType);
	}
	
	@Override
	void sendMessage(String message) {
		System.out.print("Push notification | "); 
		notificationType.sendMessageType(message);
	}
}




public class NotificationSender_Bridge {
	public static void main(String[] args) {
		INotificationType alertType = new Alert();
		INotificationType promotionType = new Promotion();
		INotificationType otpType = new Otp();
		
		Email email = new Email(promotionType);
		Sms sms = new Sms(alertType);
		Push push = new Push(otpType);
		
		email.sendMessage("50% off is going on");
		sms.sendMessage("We will have services down for 2 hours");
		push.sendMessage("your otp is 7969");
	}
}
