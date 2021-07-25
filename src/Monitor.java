//package assignment1PDP;

public class Monitor {

	public static Classroom[] lectureHalls;

	public static void main(String[] args) {
		
		//creating 4 classrooms
		Classroom c1 = new Classroom("W201 ",60);
		
		Classroom c2 = new Classroom("W202 ",60);
		
		Classroom c3 = new Classroom("W101 ",20);
		
		Classroom c4 = new Classroom("JS101",30);
		
		lectureHalls = new Classroom[]{c1, c2, c3, c4};
		
		//thread for students
		Thread students[] = new Thread[c1.capacity];
		
		//threads for lectures
		Lecture osama = new Lecture("Osama ",c1);
		
		Lecture barry = new Lecture("Barry ", c2);
		
		Lecture faheem = new Lecture("Faheem", c3);
		
		Lecture alex = new Lecture("Alex  ", c4);
		
		Lecture aqeel = new Lecture("Aqeel ", c1);
		
		Lecture waseem = new Lecture("Waseem", c3);

		
		//to call each lectures back to back
		Lecture[] lectures = {osama, barry, faheem, alex, aqeel,waseem};
		
		//starting the student threads for each classrooms
		for(int i = 0; i<students.length;i++) {
			
			new Student(c1).start();
			
			new Student(c2).start();
			
			new Student(c3).start();
			
			new Student(c4).start();
			
			try {
				Thread.sleep((int)(Math.random()*100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		//starting the visitor threads for each classrooms
		for (int i = 0; i < 5; i++) {
			new Visitor(c1).start();
			
			new Visitor(c2).start();
			
			new Visitor(c3).start();
			
			new Visitor(c4).start();
			
			try {
				Thread.sleep((int)(Math.random()*100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//starting the lectures thread from the array
		for(int i = 0; i<lectures.length; i++){
			lectures[i].start();	
		}
	
		
		
		
		//display the stats every 2 seconds
		System.out.println("----------------------------------------------------------------");
		
		System.out.println("Class       Lecture     InSession     Students   Visitors");
		
		System.out.println("----------------------------------------------------------------");
		
		for(int i = 0; i < students.length; i++) {
			System.out.println(c1.name+"       "+c1.lectureName+"        "+c1.inSession+"             "  +c1.studentCount+"        "+c1.visitorCount );
			
			System.out.println(c2.name+"       "+c2.lectureName+"        "+c2.inSession+"             "  +c2.studentCount+"        "+c2.visitorCount );
			
			System.out.println(c3.name+"       "+c3.lectureName+"        "+c3.inSession+"             "  +c3.studentCount+"        "+c3.visitorCount );
			
			System.out.println(c4.name+"       "+c4.lectureName+"        "+c4.inSession+"             "  +c4.studentCount+"        "+c4.visitorCount );
			
			System.out.println("----------------------------------------------------------------");
			
			
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	
		
	}
	
	
	//loops through lecture halls
	public static Classroom getNextClass(Classroom room) {
		int i = 0;
		while(i < lectureHalls.length) {
			if(i==lectureHalls.length) {
				i = 0;
			}
			if(lectureHalls[i].name.equals(room.name)) { 
				break;
			}
			i++;
		}
		if(i == lectureHalls.length-1) {
			i = 0;
		}else{
			++i;
		}
		return lectureHalls[i];
	}

}
