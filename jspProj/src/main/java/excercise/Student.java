package excercise;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	String sname;
	int kor, eng, math, sum, avg, rank;
	
	public Student(String sname, String kor, String eng, String math) {
		super();
		this.sname = sname;
		this.kor = Integer.parseInt(kor);
		this.eng = Integer.parseInt(eng);
		this.math = Integer.parseInt(math);
		calc();
	}
	
	void calc() {
		sum = kor + eng + math;
		avg = sum / 3;
	}
	
	void calcRank(ArrayList<Student> stuList) {
		rank= 1;
		for (Student stu : stuList) {
			if(avg < stu.avg) {
				rank++;
			}
		}
	}

	@Override
	public String toString() {
		return "Stud [sname=" + sname + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum=" + sum + ", avg=" + avg
				+ ", rank=" + rank + "]";
	}
}
