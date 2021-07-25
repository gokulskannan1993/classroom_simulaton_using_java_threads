package assignment1PDP;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CountDownLatch;

public class Classroom {
	
	String name, lectureName,inSession;
	
	int capacity, toSit, studentCount, visitorCount;
	
	Semaphore lectureSem, studentSem;
	
	ReentrantLock lock = new ReentrantLock();
	
	CountDownLatch controlLatch = new CountDownLatch(1);
	CountDownLatch satDownLatch = new CountDownLatch(1);



	//constructor
	public Classroom(String name, int capacity) {
		this.name = name;
		
		this.lectureName = "none  ";
		
		inSession = "No ";
		
		studentCount = visitorCount = toSit = 0;
		
		lectureSem = new Semaphore(1);
		
		this.capacity = capacity;
		
		studentSem = new Semaphore(capacity);

	}

	//lecture enters
	public void enterLecture(Lecture l) {
		lock.lock();
		try {
			//tries to acquire semaphore. Only one lecture can enter
			lectureSem.acquire();
			this.lectureName = l.name;
			satDownLatch = new CountDownLatch(toSit);
			controlLatch.countDown();
		} catch (InterruptedException e) {}
	}
	
	
	
	
	//lecture starts
	public void startLecture() {
		try {
			satDownLatch.await();
			inSession = "Yes";
		} catch(InterruptedException ex) {
		}
	}
	
	
	
	
	//lecture exits the class
	public void leaveLecture() {
		try {
			inSession = "No ";
			this.lectureName = "none  ";
			toSit = 0;
			controlLatch = new CountDownLatch(1);
			lectureSem.release();
		} finally {
			lock.unlock();
		}
	}

	
	
	//student enters
	public void stuEnter() {
		try {
			studentSem.acquire();
			lock.lock();
			studentCount++;
			toSit++;
		} catch (InterruptedException e) {}
			finally {
				lock.unlock();
			}
	}
	
	
	

	//visitor enters
	public void visEnter() {
		try {
			studentSem.acquire();
			lock.lock();
			visitorCount++;
		} catch (InterruptedException e) {}
		finally {
			lock.unlock();
		}
	}
	
	//sitdown
	public void sitDown() {
		try {
			controlLatch.await();
			satDownLatch.countDown();
		} catch(InterruptedException ex) {
		}

	}

	
	//student leaves
	public void studentLeave() {
		lock.lock();
		try {
			studentSem.release();
			studentCount--;
		} finally {
			lock.unlock();
		}
	}
	
	
	
	
	//visitor leaves
	public void visitorLeave() {
		lock.lock();
		try {
			studentSem.release();
			visitorCount--;
		} finally {
			lock.unlock();
		}
	}




}
