import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    private static AtomicInteger nextId = new AtomicInteger();
    final int id;
	private String code, title;
	private int credit;
	
	public Course(String code, String title, int credit){
		setCode(code);
		setTitle(title);
		setCredit(credit);
		id = nextId.incrementAndGet();
	}
	
	// setter methods
	public void setCode(String arg)	{ code = arg; }
	public void setTitle(String arg){ title = arg; }
	public void setCredit(int arg)	{ credit = arg; }

	// getter methods
	public String getCode()	{ return code; }
	public String getTitle(){ return title; }
	public int getCredit()	{ return credit; }

	public String toString(){
		String course = "Code  : " + code +
					  "\nTitle : " + title +
					  "\nCredit: " + credit + "\n";
		return course;
	}
}