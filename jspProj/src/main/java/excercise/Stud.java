package basic_p;

import java.util.ArrayList;

public class Stud {
	
	
	
	String name;
	int kor, eng, mat, tot, avg, rank;
	public Stud(String name, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		
		calc();
	}
	
	public Stud(String name, String kor, String eng, String mat) {
		super();
		this.name = name;
		this.kor = Integer.parseInt(kor);
		this.eng = Integer.parseInt(eng);
		this.mat = Integer.parseInt(mat);
		
		calc();
	}
	
	void calc() {
		tot = kor + eng + mat;
		avg = tot / 3;
	}
	
	void calcRank(ArrayList<Stud> arr) {
		rank= 1;
		for (Stud you : arr) {
			if(avg<you.avg) {
				rank++;
			}
		}
	}

	@Override
	public String toString() {
		return "Stud [name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot=" + tot + ", avg=" + avg
				+ ", rank=" + rank + "]";
	}



}
