/*
 * 5) Custom Collection Traversal
Requirement: You have a custom data structure (Playlist, OrderHistory, Cache). Provide traversal without exposing internal representation.

Implement:

Custom Iterator
Multiple traversal strategies (forward, reverse)
Follow-ups:

Iterator vs exposing list
Fail-fast vs fail-safe iterators
External vs internal iterator
 */

package com.behavioral;

import java.util.ArrayList;
import java.util.List;

interface CustomCollection {
	boolean hasNext();
	void next();
}

class Playlist implements CustomCollection {
	List<String> customList;
	int index;
	
	Playlist(List<String> list) {
		this.customList = new ArrayList<>(list);
		index = 0;
	}
	
	public boolean hasNext() {
		if(index < customList.size()) return true;
		return false;
	}
	
	public void next() {
		System.out.println("Now printing - " + customList.get(index++));
	}
}

class OrderHistory implements CustomCollection {
	List<String> customList;
	int index;
	
	OrderHistory(List<String> list) {
		this.customList = new ArrayList<>(list);
		index = customList.size() - 1;
	}
	
	public boolean hasNext() {
		if(index >= 0) return true;
		return false;
	}
	
	public void next() {
		System.out.println("Now printing - " + customList.get(index--));
	}
}

class Cache implements CustomCollection {
	List<String> customList;
	int index;
	
	Cache(List<String> list) {
		this.customList = new ArrayList<>(list);
		index = customList.size() - 1;
	}
	
	public boolean hasNext() {
		if(index >= 0) return true;
		return false;
	}
	
	public void next() {
		System.out.println("Now printing - " + customList.get(index--));
	}
}




enum CollectionType {
	PLAYLIST, ORDERHISTORY, CACHE;
}

class CollectionIterator {
	private List<String> customCollection;
	
	CollectionIterator() {
		this.customCollection = new ArrayList<>(); 
	}
 	
	protected CustomCollection getIterator(CollectionType ct) {
		switch(ct) {
			case PLAYLIST:
				return new Playlist(customCollection);
			case ORDERHISTORY:
				return new OrderHistory(customCollection);
			case CACHE:
				return new Cache(customCollection);
			default:
				throw new IllegalArgumentException("invalid argument");
		}
	}
	
	protected void addItem(String item) {
		this.customCollection.add(item);
	}
	
	protected void removeItem(String item) {
		this.customCollection.remove(item);
	}
}

public class CustomCollectionTraversal {
	public static void main(String[] args) {
		CollectionIterator ci = new CollectionIterator();
        ci.addItem("Song A");
        ci.addItem("Song B");
        ci.addItem("Song C");

        CustomCollection forward = ci.getIterator(CollectionType.PLAYLIST);
        while (forward.hasNext()) {
            forward.next();
        }

        CustomCollection reverse = ci.getIterator(CollectionType.ORDERHISTORY);
        while (reverse.hasNext()) {
            reverse.next();
        }

	}
}
