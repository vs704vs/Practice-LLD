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

public class PaymentGateway_Adapter {
	public static void main(String[] args) {
		
	}
}
