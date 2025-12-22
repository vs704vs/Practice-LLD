/*
 * 12) Data Import Pipeline
Requirement: Data import follows steps:

Read data
Validate
Transform
Save

Different data sources override only certain steps.

Implement:
Abstract class with template method
Hooks for optional steps

Follow-ups:
Template vs Strategy
Hook methods
When inheritance becomes limiting

 */

package com.behavioral;

abstract class DataImportTemplate {
	public void importData() {
		readData();
		
		if(isValidationRequired()) {
			validate();
		}

		transform();
		save();
	}
	
	public void readData() {
		System.out.println("data read");
	}
	
	abstract boolean isValidationRequired();
	
	public void validate() {
		System.out.println("Data validated");
	}
	
	abstract public void transform();
	
	abstract public void save();
}





class Fourier extends DataImportTemplate {
	@Override
	public boolean isValidationRequired() {
		return false;
	}
	
	@Override
	public void transform() {
		System.out.println("Fourier transform done on data");
	}
	
	@Override
	public void save() {
		System.out.println("Fourier data saved");
	}
}

class Laplace extends DataImportTemplate {
	@Override
	public boolean isValidationRequired() {
		return true;
	}
	
	@Override
	public void transform() {
		System.out.println("Laplace transform done on data");
	}
	
	@Override
	public void save() {
		System.out.println("Laplace data saved");
	}
}






public class DataImportPipelineTemplatePattern {
	public static void main(String[] args) {
		Fourier fourier = new Fourier();
		Laplace laplace = new Laplace();
		
		fourier.importData();
		System.out.println("-------------------------------");
		laplace.importData();		
	}
}
