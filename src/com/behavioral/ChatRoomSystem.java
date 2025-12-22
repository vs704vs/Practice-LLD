/*
 * 8) Chat Room System

Requirement:
Users send messages to each other via a chat room. Users do not communicate directly.

Implement:

ChatMediator
User objects communicate via mediator

Follow-ups:

Mediator vs Observer
Scaling mediator
Avoiding god-object mediator
 */

package com.behavioral;
import java.util.*;

class User {
	String name;
	ChatRoomMediator mediator;
	
	User(ChatRoomMediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	public void sendMessage(String message) {
		mediator.sendMessage(this, message);
	}
	
	public void receiveMessage(String message) {
		System.out.println(this.name + " has received message - " + message);
	}
}




interface ChatRoomMediator {
	void addMember(User u);
	void sendMessage(User u, String message);
}

class ChatRoom implements ChatRoomMediator{
	List<User> members;
	
	ChatRoom() {
		members = new ArrayList<>();
	}
	
	public void addMember(User u) {
		members.add(u);
	}
	
	public void sendMessage(User u, String message) {
		for(User m: members) {
			if(!m.name.equals(u.name)) {
				m.receiveMessage(message);
			}
		}
	}
}

public class ChatRoomSystem {
	public static void main(String[] args) {
		ChatRoomMediator chatRoom = new ChatRoom();

        User u1 = new User(chatRoom, "Alice");
        User u2 = new User(chatRoom, "Bob");
        User u3 = new User(chatRoom, "Charlie");

        chatRoom.addMember(u1);
        chatRoom.addMember(u2);
        chatRoom.addMember(u3);

        u1.sendMessage("Hello everyone!");
        u2.sendMessage("Hi Alice!");
        u3.sendMessage("Hey folks, good to see you here.");
	}
}
