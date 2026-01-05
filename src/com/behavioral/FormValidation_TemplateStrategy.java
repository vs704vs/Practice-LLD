/*
 * Form Validation Engine (Template + Strategy)
Asked in: Google, Frontend-heavy LLD rounds

Requirement
Form submission steps:

Sanitize
Validate
Notify
Validation rules differ per form.

Patterns Used
Template Method → workflow
Strategy → validation logic
 */

package com.behavioral;

abstract class FormValidation {
	IValidationType validationType;
	
	public void setValidationType(IValidationType validationType) {
		this.validationType = validationType;
	}
	
	public void validateForm() {
		sanitise();
		validate();
		doNotify();
	}
	
	private void sanitise() {
		System.out.println("data sanitised");
	}
	
	private void validate() {
		validationType.validate();
	}
	
	abstract public void doNotify();
}


class NotifySms extends FormValidation {
	public void doNotify() {
		System.out.println("sms notification done");
	}
}

class NotifyEmail extends FormValidation {
	public void doNotify() {
		System.out.println("email notification done");
	}
}






interface IValidationType {
	void validate();
}

class RegionValidation implements IValidationType {
	public void validate() {
		System.out.println("region validated");
	}
}

class NameValidation implements IValidationType {
	public void validate() {
		System.out.println("Name validated");
	}
}


public class FormValidation_TemplateStrategy {
	public static void main(String[] args) {
		NotifyEmail notifyEmail = new NotifyEmail();
		NotifySms notifySms = new NotifySms();
		
		RegionValidation regionValidation = new RegionValidation();
		NameValidation nameValidation = new NameValidation();
		
		
		notifyEmail.setValidationType(nameValidation);
		notifySms.setValidationType(regionValidation);
		
		notifyEmail.validateForm();
		notifySms.validateForm();
	}
}
