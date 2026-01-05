/*
 * Scheduler System (Command + Singleton + Observer)
Asked in: Amazon (scheduler services)

Requirement
Schedule jobs
Execute later
Notify listeners on completion/failure

Patterns Used
Command → encapsulated jobs
Singleton → scheduler
Observer → job listeners
 */

package com.behavioral;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Job {
	String name;

	public Job(String name) {
		this.name = name;
	}
}

class Listener {
	String name;
	
	public Listener(String name) {
		this.name = name;
	}
}



// command 
interface IJobStatus {
	void execute(Job job);
}

class Pending implements IJobStatus {
	JobExecutor jobExecutor;

	public Pending(JobExecutor jobExecutor) {
		this.jobExecutor = jobExecutor;
	}

	public void execute(Job job) {
		jobExecutor.executeJob(this, job);
	}
	
	public String toString() {
		return "Pending";
	}
}

class InProgress implements IJobStatus {
	JobExecutor jobExecutor;

	public InProgress(JobExecutor jobExecutor) {
		this.jobExecutor = jobExecutor;
	}

	public void execute(Job job) {
		jobExecutor.executeJob(this, job);
	}
	
	public String toString() {
		return "In Progress";
	}
}

class Done implements IJobStatus {
	JobExecutor jobExecutor;

	public Done(JobExecutor jobExecutor) {
		this.jobExecutor = jobExecutor;
	}
	
	public void execute(Job job) {
		jobExecutor.executeJob(this, job);
	}
	
	public String toString() {
		return "Done";
	}
}

class JobExecutor {
	List<Listener> listners;
	
	public JobExecutor() {
		listners = new ArrayList<Listener>();
	}
	
	public void executeJob(IJobStatus jobStatus, Job job) {
		System.out.println(job.name + " is in " + jobStatus + " state");
		for(Listener l: listners) {
			System.out.println("Informing this to " + l.name);
		}
	}
	
	public void addListener(Listener listener) {
		listners.add(listener);
	}
	
	public void removeListeners(Listener listener) {
		listners.remove(listener);
	}
}






// singleton
class JobQueueSingleton {
	Queue<Job> jobQueue;
	
	private JobQueueSingleton() {
		jobQueue = new LinkedList<Job>();
	}
	
	private static class JobQueueSingletonInner {
		private static final JobQueueSingleton INSTACE = new JobQueueSingleton();
	}
	
	public static JobQueueSingleton getInstance() {
		return JobQueueSingletonInner.INSTACE;
	}
	
	public void addJob(Job job) {
		jobQueue.add(job);
	}
	
	public void removeJob(Job job) {
		jobQueue.remove();
	}
	
	public Queue<Job> getJobQueue() {
		return jobQueue;
	}
}



public class SchedulerSystem_CommandSingletonObserver {
	public static void main(String[] args) {
		Job job1 = new Job("job1");
		Job job2 = new Job("job2");
		Job job3 = new Job("job3");
		
		JobQueueSingleton jobQueueSingleton = JobQueueSingleton.getInstance();
		
		jobQueueSingleton.addJob(job1);
		jobQueueSingleton.addJob(job2);
		jobQueueSingleton.addJob(job3);	
		
		
		
		executeAllJobs();
	}
	
	private static void executeAllJobs() {
		JobQueueSingleton jobQueueSingleton = JobQueueSingleton.getInstance();
		Queue<Job> jobs = jobQueueSingleton.getJobQueue();
		
		JobExecutor jobExecutor = new JobExecutor();
		IJobStatus[] jobStatus = {new Pending(jobExecutor), new InProgress(jobExecutor), new Done(jobExecutor)};
		
		Listener listener1 = new Listener("hema");
		Listener listener2 = new Listener("rekha");
		
		jobExecutor.addListener(listener1);
		jobExecutor.addListener(listener2);
		
		for(Job j: jobs) {
			for(IJobStatus js: jobStatus) {
				js.execute(j);
			}
		}
	}
}
