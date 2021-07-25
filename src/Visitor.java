//package assignment1PDP;


public class Visitor extends Thread  {
	Classroom c;
	public int count = 0;
	
	public Visitor(Classroom c) {
		this.c = c;
	}
	
	
	@Override
	public void run() {
		while(true) {
			c.visEnter();
			c.sitDown();
			try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.visitorLeave();
			c = Monitor.getNextClass(c);
		}
	}
	


}

