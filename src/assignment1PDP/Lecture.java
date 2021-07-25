package assignment1PDP;

public class Lecture extends Thread {
	String name;
	Classroom c;
	
	public Lecture(String name, Classroom c) {
		this.name = name;
		this.c = c;
	}
	
	@Override
	public void run() {
		while(true) {
			c.enterLecture(this);
			c.startLecture();
			try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.leaveLecture();
			c = Monitor.getNextClass(c);
		}
	}
}

