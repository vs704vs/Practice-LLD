/*
 * 12. Email Campaign System (Builder + Factory + Singleton combo)
Requirement: Build an email Campaign object (complex), use Builder for creation. Use a Factory to pick Transport (SMTP, SendGrid) and a Singleton CampaignScheduler to schedule sends. Demonstrate how these patterns combine.
Acceptance: Show code for Campaign.Builder, TransportFactory.get("smtp"), and CampaignScheduler.getInstance().schedule(campaign).
Follow-ups: Threading of scheduler, persistence, retry strategy, and how you'd swap singleton for injected scheduler.
 */

package com.creational;

import java.util.*;


class EmailCampaign {
	private String subject, body, sender;

	EmailCampaign(EmailCampaignBuilder builder) {
		this.subject = builder.getSubject();
		this.body = builder.getBody();
		this.sender = builder.getSender();
	}
	
	@Override
	public String toString() {
		return "EmailCampaign [subject=" + subject + ", body=" + body + ", sender=" + sender + "]";
	}
}

class EmailCampaignBuilder {
	private String subject, body, sender;

	public EmailCampaignBuilder() {
		this.subject = "default-subject";
		this.body = "default-body";
		this.sender = "default-sender";
	}

	public EmailCampaignBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public EmailCampaignBuilder setBody(String body) {
		this.body = body;
		return this;
	}

	public EmailCampaignBuilder setSender(String sender) {
		this.sender = sender;
		return this;
	}
	
	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public String getSender() {
		return sender;
	}
	
	public EmailCampaign build() {
		return new EmailCampaign(this);
	}

	@Override
	public String toString() {
		return "EmailCampaignBuilder [subject=" + subject + ", body=" + body + ", sender=" + sender + "]";
	}
}







interface EmailTransport {
	void mailTransport();
}

class Smtp implements EmailTransport {
	public void mailTransport() {
		System.out.println("Email transport is SMTP");
	}
}

class SendGrid implements EmailTransport {
	public void mailTransport() {
		System.out.println("Email transport is Send Grid");
	}
}

enum TransportType {
	SMTP, SENDGRID;
}

class EmailTransportFactory {
	public EmailTransport getTransport(TransportType type) {
		switch(type) {
			case SMTP:
				return new Smtp();
			case SENDGRID:
				return new SendGrid();
			default:
				throw new IllegalArgumentException("invalid input");
		}
	}
}








class CampaignScheduler {
	
	List<String> receiverList;
	
	private CampaignScheduler() {
		receiverList = new ArrayList<>();
	}
	
	private static class CampaignSchedulerInner {
		private static final CampaignScheduler INSTANCE = new CampaignScheduler();
	}
	
	static CampaignScheduler getInstance() {
		return CampaignSchedulerInner.INSTANCE;
	}
	
	public void addReceiver(String receiver) {
		receiverList.add(receiver);
	}
	
	public List<String> getReceiverLst() {
		return receiverList;
	}
	
	public void schedule(EmailCampaign campaign, EmailTransport transport) {
	    System.out.println("Scheduling campaign: " + campaign);
	    transport.mailTransport();
	    System.out.println("Sending to receivers: " + receiverList);
	}
}






public class EmailCampaignSystem {
	public static void main(String[] args) {

	    // Builder
	    EmailCampaign campaign = new EmailCampaignBuilder()
	            .setSubject("Sale Alert")
	            .setBody("Big discounts available!")
	            .setSender("marketing@company.com")
	            .build();

	    // Factory
	    EmailTransport transport =
	            new EmailTransportFactory().getTransport(TransportType.SMTP);

	    // Singleton
	    CampaignScheduler scheduler = CampaignScheduler.getInstance();
	    scheduler.addReceiver("hema");
	    scheduler.addReceiver("rekha");

	    scheduler.schedule(campaign, transport);
	}
}
