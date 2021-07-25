package assignment1PDP;

public class Student extends Thread  {
	Classroom c;
	
	public Student(Classroom c) {
		this.c = c;

	}
	
	@Override
	public void run() {
		while(true) {
			c.stuEnter();
			c.sitDown();
			c.studentLeave();
			c = Monitor.getNextClass(c);
		}				
	}

}
