/*
 * 15) Tax Calculation on Different Products
Requirement: Different product types (Food, Electronics, Luxury) require different tax calculations.

Implement:
Visitor for tax logic
Product hierarchy

Follow-ups:
Visitor vs double dispatch
Adding new operations vs new elements
Why Visitor is rarely used but powerful

 */

package com.behavioral;

interface IProduct {
	public void accept(ITaxCalculator visitor);
}

class Food implements IProduct {
	int amount;
	
	Food(int amt) {
		this.amount = amt;
	}
	
	@Override
	public void accept(ITaxCalculator visitor) {
		visitor.visit(this);
	}
}

class Electronics implements IProduct {
	int amount;
	
	Electronics(int amt) {
		this.amount = amt;
	}
	
	@Override
	public void accept(ITaxCalculator visitor) {
		visitor.visit(this);
	}
}

class Luxury implements IProduct {
	int amount;
	
	Luxury(int amt) {
		this.amount = amt;
	}
	
	@Override
	public void accept(ITaxCalculator visitor) {
		visitor.visit(this);
	}
}










interface ITaxCalculator {
	public void visit(Food food);
	public void visit(Electronics electronics);
	public void visit(Luxury luxury);
}

class MallTaxCalculator implements ITaxCalculator{
	int foodTax, electronicsTax, luxuryTax;
	
	MallTaxCalculator(int ft, int et, int lt) {
		this.foodTax = ft;
		this.electronicsTax = et;
		this.luxuryTax = lt;
	}
	
	@Override
	public void visit(Food food) {
		System.out.println("food amount - " + food.amount + " tax amount - " + this.foodTax);
	}
	
	@Override
	public void visit(Electronics electronics) {
		System.out.println("electronics amount - " + electronics.amount + " tax amount - " + this.electronicsTax);
	}
	
	@Override
	public void visit(Luxury luxury) {
		System.out.println("luxury amount - " + luxury.amount + " tax amount - " + this.luxuryTax);
	}
}

class LocalTaxCalcultor implements ITaxCalculator {
	int foodTax, electronicsTax, luxuryTax;
	
	LocalTaxCalcultor(int ft, int et, int lt) {
		this.foodTax = ft;
		this.electronicsTax = et;
		this.luxuryTax = lt;
	}
	
	@Override
	public void visit(Food food) {
		System.out.println("food amount - " + food.amount + " tax amount - " + this.foodTax);
	}
	
	@Override
	public void visit(Electronics electronics) {
		System.out.println("electronics amount - " + electronics.amount + " tax amount - " + this.electronicsTax);
	}
	
	@Override
	public void visit(Luxury luxury) {
		System.out.println("luxury amount - " + luxury.amount + " tax amount - " + this.luxuryTax);
	}
}






public class TaxCalculationVisistor {
	public static void main(String[] args) {
		MallTaxCalculator mallTaxCalculator = new MallTaxCalculator(30, 40, 50);
		LocalTaxCalcultor localTaxCalcultor = new LocalTaxCalcultor(10, 20, 30);
		
		IProduct[] products = {new Food(400), new Electronics(1000), new Luxury(2000) };
		
		for(IProduct p: products) {
			p.accept(mallTaxCalculator);
			p.accept(localTaxCalcultor);
		}
	}
}
