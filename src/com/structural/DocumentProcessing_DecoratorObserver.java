/*
 * Document Processing System
(Facade + Decorator + Observer)

Asked in: Adobe, DocuSign

Problem Statement
Design a document processing pipeline.

Functional Requirements

Upload document
Apply:
Virus scan
Watermark

Each processing step is optional

Completion or failure should notify users

Follow-ups
Decorator vs Strategy for processing steps?
How to handle large files?
Parallel processing?
Retry logic?
 */


package com.structural;

import java.util.ArrayList;
import java.util.List;

interface IDocumentProcessor {
	void process(String doc);
}


class BaseDocument implements IDocumentProcessor {
	@Override
	public void process(String doc) {
		System.out.println(doc + " is uploaded ");
	}
}




abstract class DocumentProcessDecorator implements IDocumentProcessor {
	IDocumentProcessor documentProcessor;
	DocumentProcessDecorator(IDocumentProcessor documentProcessor) {
		this.documentProcessor = documentProcessor;
	}
	
	abstract public void process(String doc);
}




class VirusScan extends DocumentProcessDecorator {
	public VirusScan(IDocumentProcessor documentProcessor) {
		super(documentProcessor);
	}
	
	@Override
	public void process(String doc) {
		documentProcessor.process(doc);
		System.out.println(doc + " Virus scan done");
	}
}

class Watermark extends DocumentProcessDecorator {
	public Watermark(IDocumentProcessor documentProcessor) {
		super(documentProcessor);
	}
	
	@Override
	public void process(String doc) {
		documentProcessor.process(doc);
		System.out.println(doc + " Watermark added");
	}
}






class User {
	String name;
	public User(String name) {
		this.name = name;
	}
	
	public void notifyUser(String message) {
		System.out.println(this.name + " has been notified with the file " + message);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}








class DocumentProcessFacade {
	IDocumentProcessor documentProcessor;
	List<User> users;
	public DocumentProcessFacade(IDocumentProcessor documentProcessor) {
		this.documentProcessor = documentProcessor;
		users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user) {
		users.remove(user);
	}
	
	public void notifyAllUsers(String doc) {
		for(User u: users) {
			u.notifyUser(doc);
		}
	}
	
	public void processDocument(String doc) {
		documentProcessor.process(doc);
		notifyAllUsers(doc);
	}
}




public class DocumentProcessing_DecoratorObserver {
	public static void main(String[] args) {
		IDocumentProcessor baseDocument1 = new BaseDocument();
		IDocumentProcessor baseDocument2 = new BaseDocument();
		
		IDocumentProcessor virusScanDocument1 = new VirusScan(baseDocument1);
		IDocumentProcessor watermarkDocument1 = new Watermark(virusScanDocument1);
		
		IDocumentProcessor watermarkDocument2 = new Watermark(baseDocument2);
		
		
		User user1 = new User("hema");
		User user2 = new User("rekha");
		User user3 = new User("jaya");
		
		DocumentProcessFacade document1 = new DocumentProcessFacade(watermarkDocument1);
		DocumentProcessFacade document2 = new DocumentProcessFacade(watermarkDocument2);
		
		document1.addUser(user1);
		document1.addUser(user2);
		document1.addUser(user3);
		
		document2.addUser(user1);
		document2.addUser(user3);
		
		document1.processDocument("resume.pdf");
		document2.processDocument("jd.pdf");
		
	}
}
