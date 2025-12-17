/*
 * 8. Document Template System (clone + modify)
Requirement: Maintain templates (letter, invoice). When user picks a template, clone it and apply user-specific fields. Support partial override and fallback to template defaults.
Implement: Template with cloneAndCustomize(map).
 */

package com.creational;

import java.util.HashMap;

class Template {
	private String letter, invoice;
	
	Template(String letter, String invoice) {
		this.letter = letter;
		this.invoice = invoice;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	public Template clone() {
		return new Template(this.letter, this.invoice);
	}
	
	public Template cloneAndCustomize(HashMap<String, String> map) {
		return new Template(map.getOrDefault("letter", this.letter), map.getOrDefault("invoice", this.invoice));
	}
	
	@Override
	public String toString() {
		return this.letter + " " + this.invoice;
	}
}

public class DocumentTemplateSystem {
	public static void main(String[] args) {
		Template clone1 = new Template("formal", "bill");
		Template clone2 = clone1.clone();
		
		clone2.setInvoice("no bill");
		
		System.out.println(clone1);
		System.out.println(clone2);
	}
}
