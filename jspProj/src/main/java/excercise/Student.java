package excercise;

import java.util.ArrayList;
import java.util.List;

public class Stud {
	
	String sname;
	int kor, eng, math, sum, avg, rank;
	
	public Stud(String sname, String kor, String eng, String math) {
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
	
	void calcRank(ArrayList<Stud> stuList) {
		rank= 1;
		for (Stud stu : stuList) {
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
