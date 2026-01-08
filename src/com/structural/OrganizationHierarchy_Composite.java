/*
 * Organization Hierarchy
Asked in: HR / ERP systems

Requirement: Employee → Manager → Director Each can calculate total salary cost under them.

Follow-ups:
Lazy vs eager computation
Performance for deep trees
Composite + Iterator

 */


package com.structural;

import java.util.ArrayList;
import java.util.List;



interface Role {
	int getSalary();
}

class Employee implements Role {
	public int getSalary() {
		return 100;
	}
}

class Manager implements Role {
	public int getSalary() {
		return 500;
	}
}

class Director implements Role {
	public int getSalary() {
		return 1000;
	}
}



class CompositeRole implements Role {
	List<Role> roles;
	
	public CompositeRole() {
		this.roles = new ArrayList<>();
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void removeRole(Role role) {
		this.roles.remove(role);
	}
	
	
	@Override
	public int getSalary() {
		int sum = 0;
		
		for(Role r: roles) {
			sum += r.getSalary();
		}
		
		return sum;
	}
}


public class OrganizationHierarchy_Composite {
	public static void main(String[] args) {	
		// Employees under Manager
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        
        CompositeRole manager = new CompositeRole();
        manager.addRole(new Manager());   // the manager himself
        manager.addRole(e1);              // employee under manager
        manager.addRole(e2);              // employee under manager
        
        // Director with Manager under him
        CompositeRole director = new CompositeRole();
        director.addRole(new Director()); // the director himself
        director.addRole(manager);        // manager (with employees)
        
        // Print salaries
        System.out.println("Employee e1 Salary: " + e1.getSalary());
        System.out.println("Employee e2 Salary: " + e2.getSalary());
        System.out.println("Manager Total Salary (incl. employees): " + manager.getSalary());
        System.out.println("Director Total Salary (incl. manager + employees): " + director.getSalary());
	}
}
