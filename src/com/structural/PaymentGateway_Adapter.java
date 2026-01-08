/*
 * Third-Party Payment Gateway Adapter
Asked in: Amazon, Razorpay, Paytm

Requirement: Your system expects a PaymentProcessor.pay(amount) interface. You integrate third-party gateways:

Razorpay (makePayment)
Stripe (charge)
PayPal (sendMoney)
You cannot modify third-party code.

Implement:

Adapter classes wrapping each gateway
Client depends only on PaymentProcessor
Acceptance Criteria:

Switching gateway does not affect checkout logic
New gateways can be added without touching client code
Follow-ups:

Adapter vs Facade
Object adapter vs class adapter
Error mapping & retries
When NOT to use adapter
 */

package com.structural;


class Razorpay {
	public void makePayment(int amount) {
		System.out.println("paying amount " + amount + " through " + this);
	}
	
	@Override
	public String toString() {
		return "razorpay";
	}
}

class Stripe {
	public void charge(int amount) {
		System.out.println("paying amount " + amount + " through " + this);
	}
	
	@Override
	public String toString() {
		return "stripe";
	}
}

class Paypal {
	public void sendMoney(int amount) {
		System.out.println("paying amount " + amount + " through " + this);
	}
	
	@Override
	public String toString() {
		return "paypal";
	}
}






interface IPaymentProcessor {
	public void pay(int amount);
}






class RazorpayAdapter implements IPaymentProcessor {
	Razorpay razorpay;
	
	public RazorpayAdapter(Razorpay razorpay) {
		this.razorpay = razorpay;
	}
	

	@Override
	public void pay(int amount) {
		razorpay.makePayment(amount);
	}
}

class StripeAdapter implements IPaymentProcessor {
	Stripe stripe;
	
	public StripeAdapter(Stripe stripe) {
		this.stripe = stripe;
	}
	
	@Override
	public void pay(int amount) {
		stripe.charge(amount);
	}
}

class PaypalAdapter implements IPaymentProcessor {
	Paypal paypal;
	
	public PaypalAdapter(Paypal paypal) {
		this.paypal = paypal;
	}
	

	@Override
	public void pay(int amount) {
		paypal.sendMoney(amount);
	}
}







class PaymentProcessor implements IPaymentProcessor {
	IPaymentProcessor paymentProcessor;
	
	public PaymentProcessor(IPaymentProcessor paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}
	
	public void setPaymentProcessor(IPaymentProcessor paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}
	
	@Override
	public void pay(int amount) {
		paymentProcessor.pay(amount);
	}
}







public class PaymentGateway_Adapter {
    public static void main(String[] args) {
        // --- Third-party gateways (cannot be modified) ---
        Razorpay razorpay = new Razorpay();
        Stripe stripe = new Stripe();
        Paypal paypal = new Paypal();

        // --- Adapters implementing the unified contract ---
        IPaymentProcessor razorpayAdapter = new RazorpayAdapter(razorpay);
        IPaymentProcessor stripeAdapter = new StripeAdapter(stripe);
        IPaymentProcessor paypalAdapter = new PaypalAdapter(paypal);

        // --- Client depends only on IPaymentProcessor via PaymentProcessor wrapper ---
        PaymentProcessor checkout = new PaymentProcessor(razorpayAdapter);

        int orderAmount = 999; // assuming int per your current interface

        System.out.println("=== Checkout with Razorpay ===");
        checkout.pay(orderAmount);

        // Switch gateway without touching checkout logic
        System.out.println("\n=== Switching to Stripe ===");
        checkout.setPaymentProcessor(stripeAdapter);
        checkout.pay(orderAmount);

        System.out.println("\n=== Switching to PayPal ===");
        checkout.setPaymentProcessor(paypalAdapter);
        checkout.pay(orderAmount);

        // Optional: show multiple orders with different amounts
        System.out.println("\n=== Multiple orders demo ===");
        int[] cartAmounts = {199, 499, 1299};
        checkout.setPaymentProcessor(razorpayAdapter); // switch back to Razorpay
        for (int amt : cartAmounts) {
            checkout.pay(amt);
        }

        System.out.println("\n=== Demo complete ===");
    }
}
