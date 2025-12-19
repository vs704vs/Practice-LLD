/*
 * 2) Pricing / Discount Strategy Engine
Requirement: Different users get different pricing rules:

Regular → no discount
Premium → flat 10%
Festival → dynamic discount
Pricing logic must be configurable and runtime-switchable.

Follow-ups:

Strategy vs if-else chain
Combining strategies (stacked discounts)
Strategy + Decorator discussion
 */

package com.behavioral;

interface Customer {
	void getDiscount();
}

class Regular implements Customer {
	public void getDiscount() {
		System.out.println("no discount");
	}
}

class Premium implements Customer {
	public void getDiscount() {
		System.out.println("10 percent discount");
	}
}

class Festival implements Customer {
	public void getDiscount() {
		System.out.println("dynamic discount");
	}
}

class DiscountStrategy {
	private Customer customer;
	
	DiscountStrategy(Customer c) {
		this.customer = c;
	}
	
	public void giveDiscount() {
		customer.getDiscount();
	}
	
	public void setCustomerType(Customer c) {
		this.customer = c;
	}
}

public class PricingDiscountStrategyEngine {
	public static void main(String[] args) {
		DiscountStrategy discountStarategy = new DiscountStrategy(new Regular());
		discountStarategy.giveDiscount();
		
		discountStarategy.setCustomerType(new Premium());
		discountStarategy.giveDiscount();
		
		discountStarategy.setCustomerType(new Festival());
		discountStarategy.giveDiscount();
	}
}
