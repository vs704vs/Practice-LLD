package com.creational;

/*
 * Database + Connection Factory (family of related objects)
Requirement: Create families: for Postgres produce PostgresConnection + PostgresQueryBuilder; for Mongo produce MongoConnection + MongoQueryBuilder. Use Abstract Factory so client code can switch DB family.
Implement: Abstract factory + concrete factories returning pairs of related objects.
Acceptance: Switching factory changes both connection and query builder behavior consistently.
Follow-ups: How migration/new DB affects factories, configuration-driven selection, tests, and how Abstract Factory can help plugin architectures.
 */

interface Connection {
	
}

interface QueryBuilder {
	
}

class MongoConnection implements Connection {
	
}

class MongoQueryBuilder implements QueryBuilder {
	
}

class PostgresConnection implements Connection {
	
}

class PostgresQueryBuilder implements QueryBuilder {
	
}

//class ConnectionFactory {
//	public Connection getConnection(String type) {
//		if(type == "M") {
//			return new MongoConnection();
//		}
//		else {
//			return new PostgresConnection();
//		}
//	}
//	
//	public QueryBuilder getQueryBuilder(String type) {
//		if(type == "M") {
//			return new MongoQueryBuilder();
//		}
//		else {
//			return new PostgresQueryBuilder();
//		}
//	}
//}
 
abstract class ConnectionFactory {
	abstract Connection getConnection();
	abstract QueryBuilder getQueryBuilder();
}

class MongoFactory extends ConnectionFactory {
	Connection getConnection() {
		return new MongoConnection();
	}
	QueryBuilder getQueryBuilder() {
		return new MongoQueryBuilder();
	}
}

class PostgresFactory extends ConnectionFactory {
	Connection getConnection() {
		return new PostgresConnection();
	}
	QueryBuilder getQueryBuilder() {
		return new PostgresQueryBuilder();
	}
}

public class DatabaseConnectionFactory {
	public static void main(String[] args) {
		
		
	}
}
