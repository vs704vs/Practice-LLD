///*
// * Notification Sender (VERY common)
//Asked in: Uber, Swiggy
//
//Requirement: Notifications:
//
//Types: Alert, Promotion, OTP
//Channels: Email, SMS, Push
//Avoid class explosion like: EmailAlert, SMSAlert, PushAlert, etc.
//
//Implement:
//
//Abstraction: Notification
//Implementor: NotificationSender
//Acceptance:
//
//Any notification type works with any channel
//
//Follow-ups:
//Bridge vs Strategy
//When bridge is overkill
//Runtime switching
//
// */
//
//
//package com.structural;
//
//
//
//interface INotificationType {
//	public void sendMessageType(String message);
//}
//
//class Alert implements INotificationType {
//	@Override
//	public void sendMessageType(String message) {
//		System.out.println("Alert notification type | message - " + message);
//	}
//}
//
//class Promotion implements INotificationType {
//	@Override
//	public void sendMessageType(String message) {
//		System.out.println("Promotion notification type | message - " + message);
//	}
//}
//
//class Otp implements INotificationType {
//	@Override
//	public void sendMessageType(String message) {
//		System.out.println("OTP notification type | message - " + message);
//	}
//}
//
//
//
//
//
//
//
//
//abstract class NotificationChannel {
//	INotificationType notificationType;		// Bridge
//	
//	public NotificationChannel(INotificationType notificationType) {
//		this.notificationType = notificationType;
//	}
//	
//	abstract void sendMessage(String message);
//}
//
//
//
//
//
//
//class Email extends NotificationChannel {
//	Email(INotificationType notificationType) {
//		super(notificationType);
//	}
//	
//	@Override
//	void sendMessage(String message) {
//		System.out.print("Email notification | "); 
//		notificationType.sendMessageType(message);
//	}
//}
//
//class Sms extends NotificationChannel {
//	Sms(INotificationType notificationType) {
//		super(notificationType);
//	}
//	
//	@Override
//	void sendMessage(String message) {
//		System.out.print("SMS notification | "); 
//		notificationType.sendMessageType(message);
//	}
//}
//
//class Push extends NotificationChannel {
//	Push(INotificationType notificationType) {
//		super(notificationType);
//	}
//	
//	@Override
//	void sendMessage(String message) {
//		System.out.print("Push notification | "); 
//		notificationType.sendMessageType(message);
//	}
//}
//
//
//
//
//public class NotificationSender_Bridge {
//	public static void main(String[] args) {
//		INotificationType alertType = new Alert();
//		INotificationType promotionType = new Promotion();
//		INotificationType otpType = new Otp();
//		
//		Email email = new Email(promotionType);
//		Sms sms = new Sms(alertType);
//		Push push = new Push(otpType);
//		
//		email.sendMessage("50% off is going on");
//		sms.sendMessage("We will have services down for 2 hours");
//		push.sendMessage("your otp is 7969");
//	}
//}





















/*
 * Notification Platform
(Observer + Bridge + Factory)

Asked in: Flipkart, Swiggy, Amazon, Uber

Problem Statement
Design a notification system for an e-commerce platform where different system events trigger notifications that can be sent through multiple channels.

Functional Requirements

System generates events like:

OrderPlaced
OrderCancelled
PaymentFailed

Each event can generate different notification types:

Alert
Promotional
Transactional

Notifications can be sent via:

Email
SMS
WhatsApp

New notification channels must be added without modifying existing code

Users can subscribe/unsubscribe from notification events

Follow-ups
Why Observer instead of direct method calls?
Why Bridge instead of inheritance (EmailAlert, SMSAlert)?
How will retries and failures be handled?
How would you make this async (Kafka / MQ)?
How to prevent notification storms?
 */


package com.structural;

import java.util.ArrayList;
import java.util.List;


class Subscriber {
	String name;
	public Subscriber(String name) {
		this.name = name;
	}
	
	public void notify(String message) {
		System.out.println("Subscriber - " + this.name + " | Message - " + message);
	}
}









interface INotificationType {
	public void send(Subscriber subscriber, String message);
}

class Alert implements INotificationType {
	public void send(Subscriber subscriber, String message) {
		System.out.print("Notification Type - Alert | ");
		subscriber.notify(message);
	}
}

class Promotional implements INotificationType {
	public void send(Subscriber subscriber, String message) {
		System.out.print("Notification Type - Promotional | ");
		subscriber.notify(message);
	}
}

class Transactional implements INotificationType {
	public void send(Subscriber subscriber, String message) {
		System.out.print("Notification Type - Transactional | ");
		subscriber.notify(message);
	}
}









abstract class NotificationChannel {
	INotificationType notificationType;		// Bridge
	
	public NotificationChannel(INotificationType notificationType) {
		this.notificationType = notificationType;
	}
	
	abstract public void sendNotification(String message);
}










class Email extends NotificationChannel {
	List<Subscriber> subscribers;
	
	public Email(INotificationType notificationType) {
		super(notificationType);
		subscribers = new ArrayList<Subscriber>();
	}
	
	public void addSubscriber(Subscriber subscriber) {
		this.subscribers.add(subscriber);
	}
	
	public void removeSubscriber(Subscriber subscriber) {
		this.subscribers.remove(subscriber);
	}
	
	public void sendNotification(String message) {
		for(Subscriber s: subscribers) {
			System.out.print("Notification Channel - Email | ");
			notificationType.send(s, message);
		}
	}
}


class Sms extends NotificationChannel {
	List<Subscriber> subscribers;
	
	public Sms(INotificationType notificationType) {
		super(notificationType);
		subscribers = new ArrayList<Subscriber>();
	}
	
	public void addSubscriber(Subscriber subscriber) {
		this.subscribers.add(subscriber);
	}
	
	public void removeSubscriber(Subscriber subscriber) {
		this.subscribers.remove(subscriber);
	}
	
	public void sendNotification(String message) {
		for(Subscriber s: subscribers) {
			System.out.print("Notification Channel - Sms | ");
			notificationType.send(s, message);
		}
	}
}


class Whatsapp extends NotificationChannel {
	List<Subscriber> subscribers;
	
	public Whatsapp(INotificationType notificationType) {
		super(notificationType);
		subscribers = new ArrayList<Subscriber>();
	}
	
	public void addSubscriber(Subscriber subscriber) {
		this.subscribers.add(subscriber);
	}
	
	public void removeSubscriber(Subscriber subscriber) {
		this.subscribers.remove(subscriber);
	}
	
	public void sendNotification(String message) {
		for(Subscriber s: subscribers) {
			System.out.print("Notification Channel - Whatsapp | ");
			notificationType.send(s, message);
		}
	}
}










public class NotificationSender_Bridge {
    public static void main(String[] args) {
        // --- Subscribers ---
        Subscriber vishal = new Subscriber("Vishal");
        Subscriber ananya = new Subscriber("Ananya");
        Subscriber rahul  = new Subscriber("Rahul");

        // --- Channels bound to a Notification Type (Bridge) ---
        // Email channels
        Email emailAlert           = new Email(new Alert());
        Email emailPromotional     = new Email(new Promotional());
        Email emailTransactional   = new Email(new Transactional());

        // SMS channels
        Sms smsAlert               = new Sms(new Alert());
        Sms smsTransactional       = new Sms(new Transactional());

        // WhatsApp channels
        Whatsapp whatsappAlert     = new Whatsapp(new Alert());
        Whatsapp whatsappPromo     = new Whatsapp(new Promotional());

        // --- Subscriptions (Observer) ---
        // Vishal wants all alerts across channels + transactional via Email & SMS
        emailAlert.addSubscriber(vishal);
        smsAlert.addSubscriber(vishal);
        whatsappAlert.addSubscriber(vishal);
        emailTransactional.addSubscriber(vishal);
        smsTransactional.addSubscriber(vishal);

        // Ananya wants promos on Email/WhatsApp and alerts via WhatsApp
        emailPromotional.addSubscriber(ananya);
        whatsappPromo.addSubscriber(ananya);
        whatsappAlert.addSubscriber(ananya);

        // Rahul only wants critical alerts via SMS and transactional via Email
        smsAlert.addSubscriber(rahul);
        emailTransactional.addSubscriber(rahul);

        // --- Simulate Events ---
        // ORDER_PLACED → send Transactional on Email/SMS + Alert on WhatsApp
        System.out.println("\n=== EVENT: ORDER_PLACED ===");
        emailTransactional.sendNotification("Your order has been placed successfully.");
        smsTransactional.sendNotification("Order placed. Payment confirmed.");
        whatsappAlert.sendNotification("Alert: New order placed.");

        // ORDER_CANCELLED → send Alert on Email/SMS/WhatsApp
        System.out.println("\n=== EVENT: ORDER_CANCELLED ===");
        emailAlert.sendNotification("Alert: Your order was cancelled.");
        smsAlert.sendNotification("Alert: An order has been cancelled.");
        whatsappAlert.sendNotification("Alert: Order cancelled. Check details.");

        // PAYMENT_FAILED → send Alert on SMS + Transactional on Email
        System.out.println("\n=== EVENT: PAYMENT_FAILED ===");
        smsAlert.sendNotification("Payment failed. Please retry.");
        emailTransactional.sendNotification("Transaction failed. Use another payment method.");

        // --- Unsubscribe demo ---
        System.out.println("\n=== ACTION: Ananya unsubscribes from WhatsApp Alerts ===");
        whatsappAlert.removeSubscriber(ananya);

        System.out.println("\n=== EVENT: ORDER_PLACED (again, after unsubscribe) ===");
        emailTransactional.sendNotification("Your order has been placed successfully.");
        smsTransactional.sendNotification("Order placed. Payment confirmed.");
        whatsappAlert.sendNotification("Alert: New order placed.");
    }
}
