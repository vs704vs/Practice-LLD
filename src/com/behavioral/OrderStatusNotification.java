/*
 * 3) Order Status Notification System (classic but modern)
Requirement: When an order status changes (PLACED, SHIPPED, DELIVERED), notify:

Email service
SMS service
Push notification service
Observers can be added/removed dynamically.

Implement:

Order as Subject
NotificationObserver interface
Concrete observers
Acceptance:

Adding a new notification channel does NOT change Order logic
Observers are notified automatically on status change
Follow-ups:

Observer vs Pub-Sub
Synchronous vs asynchronous observers
Failure handling (one observer fails)
Memory leaks (dangling observers)
 */

package com.behavioral;

import java.util.ArrayList;
import java.util.List;


interface NotificationObserver {
    void update(OrderStatus status);
}

class EmailService implements NotificationObserver {
    private final String name;

    public EmailService(String name) {
        this.name = name;
    }

    @Override
    public void update(OrderStatus status) {
        System.out.println("EmailService [" + name + "] notified: Order is " + status);
    }
}

class SmsService implements NotificationObserver {
    private final String name;

    public SmsService(String name) {
        this.name = name;
    }

    @Override
    public void update(OrderStatus status) {
        System.out.println("SmsService [" + name + "] notified: Order is " + status);
    }
}

class PushService implements NotificationObserver {
    private final String name;

    public PushService(String name) {
        this.name = name;
    }

    @Override
    public void update(OrderStatus status) {
        System.out.println("PushService [" + name + "] notified: Order is " + status);
    }
}




enum OrderStatus {
    PLACED, SHIPPED, DELIVERED
}

class Order {
    private final List<NotificationObserver> observers = new ArrayList<>();
    private OrderStatus status;

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void changeStatus(OrderStatus newStatus) {
        if (newStatus != this.status) {
            this.status = newStatus;
            notifyObservers();
        }
    }

    private void notifyObservers() {
        for (NotificationObserver observer : observers) {
            try {
                observer.update(status);
            } catch (Exception e) {
                System.err.println("Observer failed: " + e.getMessage());
            }
        }
    }
}

public class OrderStatusNotification {
    public static void main(String[] args) {
        Order order = new Order();

        order.addObserver(new EmailService("hema"));
        order.addObserver(new SmsService("rekha"));
        order.addObserver(new PushService("jaya"));
        order.addObserver(new EmailService("sushma"));

        order.changeStatus(OrderStatus.PLACED);
        order.changeStatus(OrderStatus.SHIPPED);
        order.changeStatus(OrderStatus.DELIVERED);
        order.changeStatus(OrderStatus.DELIVERED);
    }
}