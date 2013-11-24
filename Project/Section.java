import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class Section {
    private static AtomicInteger nextId = new AtomicInteger();
    final int id;
	private int sectionNum, studentLimit=30, time=0, day=0;
	private String timeRepresentation = null, dayRepresentation = null;
	private Course course = null;
	private Lecturer lecturer = null;
	private Venue venue = null;

	public Section(
			Course course,
			Lecturer lecturer,
			int sectionNum
		){
		setCourse(course);
		setLecturer(lecturer);
		setSectionNum(sectionNum);
		id = nextId.incrementAndGet();
	}

	public void setDay(int arg){ day = arg; setDayRepresentation(arg); }
	public void setTime(int arg){ time = arg; setTimeRepresentation(arg); }
	public void setSectionNum(int arg){ sectionNum = arg; }
	public void setStudentLimit(int arg){ studentLimit = arg; }
	public void setCourse(Course arg){ course = arg; }
	public void setLecturer(Lecturer arg){ lecturer = arg; }
	public void setVenue(Venue arg){ venue = arg; }
	public void setTimeRepresentation(int arg){
		switch(arg){
			case 0: timeRepresentation = "08:30 - 09:50"; break;
			case 1: timeRepresentation = "10:00 - 11:20"; break;
			case 2: timeRepresentation = "11:30 - 12:50"; break;
			case 3: timeRepresentation = "14:00 - 15:20"; break;
			case 4: timeRepresentation = "15:30 - 16:50"; break;
			case 5: timeRepresentation = "17:00 - 18:20"; break;
			default: timeRepresentation = null;
		}
	}
	public void setDayRepresentation(int arg){
		switch(arg) {
			case 0: dayRepresentation = "Monday/Wednesday"; break;
			case 1: dayRepresentation = "Tuesday/Thursday"; break;
			default: dayRepresentation = null;
		}
	}

	public int getDay(){ return day; }
	public int getTime(){ return time; }
	public int getStudentLimit(){ return studentLimit; }
	public String getSectionNum(){ return Integer.toString(sectionNum); }
	public String getDayRepresentation(){ return dayRepresentation; }
	public String getTimeRepresentation(){ return timeRepresentation; }
	public Course getCourse(){ return course; }
	public Lecturer getLecturer(){ return lecturer; }
	public Venue getVenue(){ return venue; }

	public void setVenue(ArrayList<Venue> venues){
		String code = course.getCode();
		ArrayList<String> courses = new ArrayList<String>();
		for (Venue v : venues) {
			courses = v.getCourses();
			if (courses.contains(code))
				venue = v;
		}
	}

	public void generateSchedule(ArrayList<Section> sections){
		int tempDay=0, tempTime=0, max=0;
		boolean inC=true, inL=true, inV=true;

		if (!(timeRepresentation == null) && !(dayRepresentation == null)) {
			if (getDay()==0) {
				if(getTime()<3) tempTime = getTime();
				else tempTime = getTime() + 3;
				System.out.println(getTime());
			}
			else{
				if(getTime()<3) tempTime = getTime()+3;
				else tempTime = getTime() + 6;
				System.out.println(getTime());
			}
			course.addAvailability(new Integer(tempTime));
			lecturer.addAvailability(new Integer(tempTime));
			venue.addAvailability(new Integer(tempTime));
		}

		for (Section s : sections) {
			if(s.venue.equals(venue))
				max++;
			if(max==1) max = 3;
		}

		do{
			tempTime = (int) (Math.random() * max);
			inC = course.getAvailability().contains(tempTime);
			inL = lecturer.getAvailability().contains(tempTime);
			inV = venue.getAvailability().contains(tempTime);
			// System.out.println(tempTime + " " + inC + " " + inL + " " + inV + " " + (!inC || !inL || !inV));
		}while(!inC || !inL || !inV);

		course.getAvailability().remove(new Integer(tempTime));
		lecturer.getAvailability().remove(new Integer(tempTime));
		venue.getAvailability().remove(new Integer(tempTime));

		if(tempTime>5&&tempTime<9){
			tempTime-=3;
		}
		else if((tempTime>2&&tempTime<6)||(tempTime>8)){
			tempDay=1;
			if(tempTime<6) tempTime-=3;
			if(tempTime>8) tempTime-=6;
		}

		setDay(tempDay);
		setTime(tempTime);
	}

	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Section))
	        return false;

	    Section that = (Section) other;

	    return this.sectionNum == that.sectionNum
	    	&& this.course.equals(that.course)
	    	&& this.lecturer.equals(that.lecturer);
	}

	public String toString(){
		String rep = getSectionNum() + ", " + getCourse() + ", " + getLecturer() + ", " + getVenue() + ", " + getDayRepresentation() + ", " + getTimeRepresentation();
		return rep;
	}
}