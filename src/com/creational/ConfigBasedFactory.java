package com.creational;

/*
 * Config-based Factory (from interview to code)
Requirement: App reads a config (JSON) with "storage": "s3" or "storage":"local". Create Storage instances (S3Storage, LocalStorage) using a factory that uses config at runtime.
Implement: Storage interface, concrete classes, StorageFactory.getFromConfig(json).
Acceptance: Swap config to switch backend at runtime without changing client code.
Follow-ups: How to test with mocks, abstract factory vs factory method if multiple families (e.g., storage + cache), error handling when config missing. 
GeeksforGeeks
 */

enum StorageType {
	S3, LOCAL;
}

interface IStorage {
	
}

class S3Storage implements IStorage {
	
}

class LocalStorage implements IStorage {
	
}

class ConfigFactory {
	public static IStorage getConfig(StorageType type) {
		if(type == StorageType.S3) {
			return new S3Storage();
		}
		else if(type == StorageType.LOCAL) {
			return new LocalStorage();
		}
		else {
			throw new IllegalArgumentException("illegal argument");
		}
	}
}

public class ConfigBasedFactory {
	public static void main(String[] args) {
		IStorage storage = ConfigFactory.getConfig(StorageType.LOCAL);
		System.out.println(storage);
	}
}
