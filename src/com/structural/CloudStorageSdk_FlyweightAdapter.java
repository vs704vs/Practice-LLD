/*
 * Cloud Storage SDK
(Adapter + Proxy + Flyweight)

Asked in: AWS teams, Google Cloud, Microsoft Azure

Problem Statement
Design a unified cloud storage SDK that supports multiple cloud providers.

Functional Requirements

Support cloud providers:
AWS S3
Google Cloud Storage
Azure Blob Storage

Expose a single interface for upload

Add:
Authentication
Caching

Metadata objects should be shared to reduce memory usage

Follow-ups
Adapter vs Facade for cloud SDK?
What goes into Proxy vs Adapter?
Intrinsic vs extrinsic state in Flyweight?
How to handle token expiration?
Thread safety concerns?
 */

package com.structural;

class AwsS3 {
	public void awsUpload(String file) {
		System.out.println(file + " has been uploaded to AWS S3");
	}
}

class GoogleCloudStorage {
	public void googleCloudUpload(String file) {
		System.out.println(file + " has been uploaded to Google Cloud");
	}
}

class AzureBlobStorage {
	public void azureUpload(String file) {
		System.out.println(file + " has been uploaded to Azure blob");
	}
}







interface ICloudStorageType {
	void upload(String file);
}

class AwsS3Adapter implements ICloudStorageType {
	AwsS3 awsS3;
	public AwsS3Adapter(AwsS3 awsS3) {
		this.awsS3 = awsS3;
	}
	
	@Override
	public void upload(String file) {
		awsS3.awsUpload(file);
	}
}

class GoogleCloudAdapter implements ICloudStorageType {
	GoogleCloudStorage googleCloudStorage;
	public GoogleCloudAdapter(GoogleCloudStorage googleCloudStorage) {
		this.googleCloudStorage = googleCloudStorage;
	}
	
	@Override
	public void upload(String file) {
		googleCloudStorage.googleCloudUpload(file);
	}
}

class AzureBlobAdapter implements ICloudStorageType {
	AzureBlobStorage azureBlobStorage;
	public AzureBlobAdapter(AzureBlobStorage azureBlobStorage) {
		this.azureBlobStorage = azureBlobStorage;
	}
	
	@Override
	public void upload(String file) {
		azureBlobStorage.azureUpload(file);
	}
}






class CloudStorageFlyweight {
	ICloudStorageType cloudStorageType;
	boolean authentication;
	boolean caching;
	
	public CloudStorageFlyweight(ICloudStorageType cloudStorageType) {
		this.cloudStorageType = cloudStorageType;
	}
	
	public void addAuthentication() {
		this.authentication = true;
	}
	
	public void addCaching() {
		this.caching = true;
	}
}









public class CloudStorageSdk_FlyweightAdapter {
    public static void main(String[] args) {

        // Step 1: Create adapters for each provider
        ICloudStorageType awsAdapter = new AwsS3Adapter(new AwsS3());
        ICloudStorageType gcpAdapter = new GoogleCloudAdapter(new GoogleCloudStorage());
        ICloudStorageType azureAdapter = new AzureBlobAdapter(new AzureBlobStorage());

        // Step 2: Wrap adapters with Flyweight (shared metadata objects)
        CloudStorageFlyweight awsFlyweight = new CloudStorageFlyweight(awsAdapter);
        CloudStorageFlyweight gcpFlyweight = new CloudStorageFlyweight(gcpAdapter);
        CloudStorageFlyweight azureFlyweight = new CloudStorageFlyweight(azureAdapter);

        // Step 3: Enable authentication and caching features
        awsFlyweight.addAuthentication();
        awsFlyweight.addCaching();

        gcpFlyweight.addAuthentication(); // only auth
        // no caching for GCP in this demo

        azureFlyweight.addCaching(); // only caching
        // no auth for Azure in this demo

        // Step 4: Perform uploads via unified interface
        System.out.println("=== Uploading files via unified SDK ===");
        awsAdapter.upload("resume.pdf");
        gcpAdapter.upload("project.zip");
        azureAdapter.upload("image.png");

        // Step 5: Show metadata state (intrinsic vs extrinsic Flyweight)
        System.out.println("\n=== Metadata state ===");
        System.out.println("AWS Flyweight -> Auth: " + awsFlyweight.authentication + ", Cache: " + awsFlyweight.caching);
        System.out.println("GCP Flyweight -> Auth: " + gcpFlyweight.authentication + ", Cache: " + gcpFlyweight.caching);
        System.out.println("Azure Flyweight -> Auth: " + azureFlyweight.authentication + ", Cache: " + azureFlyweight.caching);
    }
}
