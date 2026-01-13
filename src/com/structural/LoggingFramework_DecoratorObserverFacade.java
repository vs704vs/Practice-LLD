/*
 * Logging Framework

Asked in: Oracle, SAP, Atlassian

Problem Statement
Design a logging framework similar to Log4j with extensible features.

Functional Requirements

Support log levels:
INFO
DEBUG
ERROR

Logs can be:
Formatted
Enriched with metadata

Logs can be sent to:
File
Database

ERROR logs should trigger alerts

Follow-ups
Decorator vs Chain of Responsibility
How to avoid performance bottlenecks?
How to make logging async?
How to prevent recursive logging failures?
 */

package com.structural;

interface ILogType {
	String printLog(String message);
}



class InfoLog implements ILogType {
	@Override
	public String printLog(String message) {
		return "Info log - " + message + "\n";
	}
}

class DebugLog implements ILogType {
	@Override
	public String printLog(String message) {
		return "Debug log - " + message + "\n";
	}
}

class ErrorLog implements ILogType {
	@Override
	public String printLog(String message) {
		return "Error log - " + message + "\n";
	}
}




class FormatLog implements ILogType {
	ILogType logType;
	
	public FormatLog(ILogType logType) {
		this.logType = logType;
	}
	
	@Override
	public String printLog(String message) {
		return this.logType.printLog(message) + " Decorated with formatting \n\n";
	}
}

class MetaDataLog implements ILogType {
	ILogType logType;
	
	public MetaDataLog(ILogType logType) {
		this.logType = logType;
	}
	
	@Override
	public String printLog(String message) {
		return this.logType.printLog(message) + " Decorated with metadata \\n\\n";
	}
}






class FileSingleton {
	String logFile;
	
	private FileSingleton() {
		logFile = "";
	}
	
	private static class innerFileSingleton {
		private static final FileSingleton INSTANCE = new FileSingleton();
	}
	
	public static FileSingleton getInstance() {
		return innerFileSingleton.INSTANCE;
	}
}

class DbSingleton {
	String logFile;
	
	private DbSingleton() {
		logFile = "";
	}
	
	private static class innerDbSingleton {
		private static final DbSingleton INSTANCE = new DbSingleton();
	}
	
	public static DbSingleton getInstance() {
		return innerDbSingleton.INSTANCE;
	}
}






public class LoggingFramework_DecoratorObserverFacade {
    public static void main(String[] args) {
        // Create basic log types
        ILogType infoLog = new InfoLog();
        ILogType debugLog = new DebugLog();
        ILogType errorLog = new ErrorLog();

        // Decorate logs with formatting and metadata
        ILogType formattedInfoLog = new FormatLog(new MetaDataLog(infoLog));
        ILogType formattedDebugLog = new FormatLog(new MetaDataLog(debugLog));
        ILogType formattedErrorLog = new FormatLog(new MetaDataLog(errorLog));

        // Print logs
        System.out.println(formattedInfoLog.printLog("Application started"));
        System.out.println(formattedDebugLog.printLog("Debugging user session"));
        System.out.println(formattedErrorLog.printLog("Null pointer exception occurred"));

        // Simulate writing logs to File
        FileSingleton fileLogger = FileSingleton.getInstance();
        fileLogger.logFile += formattedInfoLog.printLog("Application started");
        fileLogger.logFile += formattedDebugLog.printLog("Debugging user session");
        fileLogger.logFile += formattedErrorLog.printLog("Null pointer exception occurred");

        System.out.println("Logs written to File:");
        System.out.println(fileLogger.logFile);

        // Simulate writing logs to DB
        DbSingleton dbLogger = DbSingleton.getInstance();
        dbLogger.logFile += formattedErrorLog.printLog("Critical error in DB connection");

        System.out.println("Logs written to DB:");
        System.out.println(dbLogger.logFile);
    }
}

